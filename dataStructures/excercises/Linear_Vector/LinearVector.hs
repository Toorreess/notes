-- Student's name: ?????
-- Identity number (DNI): ????

module LinearVector(
    Vector
  , vector
  , size
  , get
  , set
  , add

  , main
  ) where

import Data.List(intercalate)
import Test.QuickCheck(listOf, Arbitrary(arbitrary))
import qualified Control.DeepSeq as Deep
import qualified UnitTests as UT


data Vector a = Empty | Node Int a (Vector a)

-- Examples:
-- A Vector with 4 cells (size is 4 and indexes go from 0 to 3)
vector1 :: Vector Integer
vector1 = Node 0 10 (Node 1 20 (Node 2 30 (Node 3 40 Empty)))

-- A Vector with 2 cells (size is 2 and indexes go from 0 to 1)
vector2 :: Vector Integer
vector2 = Node 0 100 (Node 1 200 Empty)

-- This function constructs a vector. 
-- sz is the size (number of cells) of the vector.
-- All cells are initialized to value x.
vector :: Int -> a -> Vector a
vector sz x
  | sz < 0    = error "negative size"
  | otherwise = aux 0
  where 
    aux i
      | i < sz    = Node i x (aux (i+1))
      | otherwise = Empty

-------------------------------------------------------------------------------
-- DON'T WRITE ANY CODE ABOVE THIS LINE.
-- WRITE ALL YOUR CODE BELOW.

size :: Vector a -> Int
size Empty = 0
size (Node _ _ xs) = 1 + size xs

get :: Int -> Vector a -> a
get _ Empty = error "wrong index"
get i (Node index x xs)
    | i > index  = get i xs
    | i == index = x
    | otherwise  = error "wrong index"

set :: Int -> a -> Vector a -> Vector a
set _ _ Empty = error "wrong index"
set i x (Node index y ys)
    | i > index = Node index y (set i x ys)
    | i == index = Node index x ys
    | otherwise = error "wrong index"

add :: (Num a) => Vector a -> Vector a -> Vector a
add v Empty = v
add Empty v = v
add v1 v2
    | (size v1) /= (size v2) = error "vectors have different sizes"
add (Node ix x xs) (Node iy y ys)
    | ix > iy   = Node iy y (add (Node ix x xs) ys)
    | ix == iy  = Node ix (x+y) (add xs ys)
    | otherwise = Node ix x (add xs (Node iy y ys))

-- DON'T WRITE ANY CODE BELOW THIS LINE.
-- WRITE ALL YOUR CODE ABOVE.
-------------------------------------------------------------------------------

instance (Show a) => Show (Vector a) where
  show v = "LinearVector(" ++ intercalate ", " (aux v) ++ ")"
    where
      aux Empty = []
      aux (Node i x v) = (show i ++ ": " ++ show x) : aux v

instance (Eq a) => Eq (Vector a) where
  Empty == Empty           = True
  Node i x v == Node j y u = i==j && x==y && v==u
  _          == _          = False

instance (Arbitrary a) => Arbitrary (Vector a) where
  arbitrary = do
      xs <- listOf arbitrary
      return (aux 0 xs)
      where
        aux i []     = Empty
        aux i (x:xs) = Node i x (aux (i+1) xs)

instance (Deep.NFData a) => Deep.NFData (Vector a) where
  rnf Empty        = ()
  rnf (Node i x v) = i `Deep.deepseq` x `Deep.deepseq` Deep.rnf v


-- Unit tests

sizeTests :: [IO Bool]
sizeTests = 
  [ UT.test "size non-empty" [ size testVectorA
                             , 4
                             ]
  , UT.test "size empty" [ size Empty
                         , 0
                         ]
  ]

getTests :: [IO Bool]
getTests = 
  [ UT.error "get negative" [ get (-1) testVectorA
                            , error "wrong index"
                            ]
  , UT.error "get empty" [ get 0 (Empty :: Vector Int)
                         , error "wrong index"
                         ]
  , UT.test "get first" [ get 0 testVectorA
                        , 10
                        ]
  , UT.test "get second" [ get 1 testVectorA
                         , 20
                         ]
  , UT.test "get third" [ get 2 testVectorA
                        , 30
                        ]
  , UT.test "get last" [ get 3 testVectorA
                       , 40
                       ]
  , UT.error "get too large" [ get 10 testVectorA
                             , error "wrong index"
                             ]
  ]

setTests :: [IO Bool]
setTests = 
  [ UT.error "set negative" [ set (-1) 0 testVectorA
                            , error "wrong index"
                            ]
  , UT.error "set empty" [ set 0 1 (Empty :: Vector Int)
                         , error "wrong index"
                         ]
  , UT.test "set first" [ set 0 100 testVectorA
                        , Node 0 100 (Node 1 20 (Node 2 30 (Node 3 40 Empty)))
                        ]
  , UT.test "set second" [ set 1 200 testVectorA
                         , Node 0  10 (Node 1 200 (Node 2 30 (Node 3 40 Empty)))
                         ]
  , UT.test "set third" [ set 2 300 testVectorA
                        , Node 0 10 (Node 1 20 (Node 2 300 (Node 3 40 Empty)))
                        ]
  , UT.test "set last" [ set 3 400 testVectorA
                       , Node 0 10 (Node 1 20 (Node 2 30 (Node 3 400 Empty)))
                       ]
  , UT.error "set too large" [ set 4 500 testVectorA
                             , error "wrong index"
                             ]
  ]  

addTests :: [IO Bool]
addTests = 
  [ UT.error "add incompatible" [ add testVectorA testVectorB 
                                , error "vectors have different sizes"
                                ]
  , UT.test "add compatible" [ add testVectorA testVectorC
                             , Node 0  11 (Node 1  22 (Node 2  33 (Node 3  44 Empty)))
                             ]                              
  ]

main :: IO ()
main =  
  UT.runTests [ ("size", sizeTests)
              , ("get", getTests)
              , ("set", setTests)
              , ("add", addTests)
              ]

-- Some vectors for tests  
testVectorA, testVectorB, testVectorC :: Vector Int
testVectorA = Node 0  10 (Node 1  20 (Node 2  30 (Node 3  40 Empty)))
testVectorB = Node 0  10 (Node 1  20 (Node 2  30 Empty))
testVectorC = Node 0   1 (Node 1   2 (Node 2   3 (Node 3   4 Empty)))

