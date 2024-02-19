-------------------------------------------------------------------------------
-- Linear implementation of Sets with nodes sorted according to values
-- and non-repeated elements
--
-- Data Structures. Grado en Informática. UMA.
--
-- STUDENT'S NAME: TORRES POSTIGO, JOSE
-------------------------------------------------------------------------------
module DataStructures.Set.SortedLinearSet
  ( Set
  , empty
  , isEmpty
  , size
  , insert
  , isElem
  , delete

  , fold

  , union
  , intersection
  , difference
  ) where

import Data.List(intercalate)
import Test.QuickCheck
import Data.Bool (Bool(True, False))

-- Invariants for this data structure:
--  * (INV1) All Nodes store different elements (no repetitions)
--  * (INV2) Nodes are sorted in ascending order with 
--           respect to values of their elements
--
-- An example of a well constructed set:
--   Node 2 (Node 5 (Node 8 Empty))
--
-- Examples of wrong sets:
--   Node 2 (Node 5 (Node 5 (Node 8 Empty))) -- REPETITION OF ELEMENT 5!
--   Node 7 (Node 1 (Node 8 Empty)) -- ELEMENTS NOT IN ASCENDING ORDER!

data Set a  = Empty | Node a (Set a)

empty :: Set a
empty = Empty

isEmpty :: Set a -> Bool
isEmpty Empty = True
isEmpty _     = False

isElem :: (Ord a) => a -> Set a -> Bool
isElem e Empty = False
isElem e (Node x s)
  | e < x = False
  | otherwise = isElem e s || e == x

insert :: (Ord a) => a -> Set a -> Set a
insert e Empty = Node e Empty
insert e (Node x s)
  | e < x     = Node e (Node x s)
  | e == x    = Node x s  -- Removes repetition
  | otherwise = Node x (insert e s)

delete :: (Ord a) => a -> Set a -> Set a
delete e Empty = Empty
delete e (Node x s)
  | e < x     = Node x s
  | e == x    = s
  | otherwise = Node x (delete e s)

size :: Set a -> Int
size Empty = 0
size (Node _ s) = 1 + size s

fold :: (a -> b -> b) -> b -> Set a -> b
fold f z = fun
 where
  fun Empty       = z
  fun (Node x s)  = f x (fun s)

union :: (Ord a) => Set a -> Set a -> Set a
union s s' = fold insert s s'

difference :: (Ord a) => Set a -> Set a -> Set a
difference s s' = fold delete s s'

intersection :: (Ord a) => Set a -> Set a -> Set a
intersection s s' = fold (\x inter -> if isElem x s then insert x inter else inter) empty s'





-- Showing a set
instance (Show a) => Show (Set a) where
  show s  = "SortedLinearSet(" ++ intercalate "," (strings s) ++ ")"
    where
      strings Empty       = []
      strings (Node x s)  = show x : strings s

-- Set equality
instance (Eq a) => Eq (Set a) where
  Empty      == Empty         = True
  (Node x s) == (Node x' s')  = x==x' && s==s'
  _          == _             = False

-- This instance is used by QuickCheck to generate random sets
instance (Ord a, Arbitrary a) => Arbitrary (Set a) where
    arbitrary  = do
      xs <- listOf arbitrary
      return (foldr insert empty xs)

