-------------------------------------------------------------------------------
-- Data Structures. ETSI Informática. UMA
--
-- Degree: Grado en Ingeniería del Software.
-- Student: TORRES POSTIGO, JOSE
-- Date: 12 | January | 2024
--
-- Exercises 2.
--
-------------------------------------------------------------------------------
import Test.QuickCheck
import Data.List

-- Excercise 1
allDifferent :: Eq a => [a] -> Bool
allDifferent [] = True
allDifferent (x:xs) = not (elem x xs) && allDifferent xs 

-- Excercise 2
    -- a) replicate'
replicate' :: Integer -> a -> [a]
replicate' 0 _ = []
replicate' 1 x = [x]
replicate' n x = [x] ++ replicate' (n-1) x

-- Excercise 3
    -- divisors
divisors :: Integer -> [Integer]
-- divisors 0 = error "0 doesn't have divisors"
divisors n = [x | x <- [1..n], mod n x == 0]

    -- divisors'
divisors' :: Integer -> [Integer]
divisors' n = [x | x <- [-n..n], x/=0 && mod n x == 0]

-- Excercise 4
gcdiv :: Integer -> Integer -> Integer
    -- a) gcdiv
gcdiv x y = maximum [z | z <- divisors' (abs x), z `elem` divisors' (abs y), x/=0 && y/=0]

    -- b) Property Quickcheck
p_gcdiv :: Integer -> Integer -> Integer -> Property
p_gcdiv x y z = x>0 && y>0 && z>0 ==> gcdiv (z*x) (z*y) == z * (gcdiv x y)

    -- c) lcmul
lcmul :: Integer -> Integer -> Integer
lcmul x y = (x*y) `div` (gcdiv x y)

-- Excercise 5
    -- a) isPrime
isPrime :: Integer -> Bool
isPrime 1 = False
isPrime n = divisors n == [1, n]

    -- b) primesUpTo, using list comprenhension
primesUpTo :: Integer -> [Integer]
primesUpTo n = [x | x <- [1..n], isPrime x]

    -- c) primesUpTo', using filter
primesUpTo' :: Integer -> [Integer]
primesUpTo' n = filter isPrime [1..n]
    
    -- d) Property QuickCheck
p1_primes :: Integer -> Property
p1_primes x = True ==> primesUpTo x == primesUpTo' x

-- Excercise 6
    -- a) take'
take' :: Integer -> [a] -> [a]
take' n xs = [ x | (p,x) <- zip [0..(n-1)] xs ]

    -- b) drop'
drop' :: Integer -> [a] -> [a]
drop' n xs = [ x | (p,x) <- zip [0..] xs, p>=n ]

    -- c) Property QuickCheck
p1_takeAndDrop :: (Eq a) => Integer -> [a] -> Property
p1_takeAndDrop n xs = n>= 0 ==> (take' n xs) ++ (drop' n xs) == xs

-- Excercise 7
    -- a) concat', using foldr
concat' :: [[a]] -> [a]
concat' = foldr (++) []

    -- b) concat'', using list comprenhension
concat'' :: [[a]] -> [a]
concat'' xss = [x | xs <- xss, x <- xs]

-- Excercise 8
unknown :: (Ord a) => [a] -> Bool
unknown xs = and [ x<=y | (x,y) <- zip xs (tail xs) ]

-- Excercise 9
    -- a) insert
insert :: (Ord a) => a -> [a] -> [a]
insert x xs = (takeWhile (<x) xs) ++ [x] ++ (dropWhile (<x) xs)

    -- b) Property QuickCheck
p1_insert :: (Ord a) => a -> [a] -> Property
p1_insert x xs = unknown xs ==> unknown (Main.insert x xs)

    -- d) isort
isort :: (Ord a) => [a] -> [a]
isort xs = foldr Main.insert [] xs

    -- e) Property QuickCheck
p1_isort xs = unknown (isort xs)

-- Excercise 10
    -- a) geometric
geometric :: Integer -> Integer -> [Integer]
geometric x r = iterate (*r) x

    -- b) Property QuickCheck
p1_geometric x r = x>0 && r>0 ==>
                    and [ div z y == r | (y,z) <- zip xs (tail xs) ]
                        where xs = take 100 (geometric x r)

    -- c) multiplesOf
multiplesOf :: Integer -> [Integer]
multiplesOf 0 = error "0 is not a valid argument"
multiplesOf x = iterate (+x) x

    -- d) powersOf
powersOf :: Integer -> [Integer]
powersOf x = iterate (*x) x

-- Excercise 11
    -- a) p_rightUnit
p_rightUnit :: [Integer] -> [Integer] -> Property
p_rightUnit xs ys = ys == [] ==> xs ++ ys == xs

-- Excercise 13
    -- a) nub'
nub' :: (Eq a) => [a] -> [a]
nub' [] = []
nub' (x:xs) = x : nub' (filter (/=x) xs)
    
    -- b) Property QuickCheck
p_nub' xs = nub xs == nub' xs

    -- d) p_noRepetition
allIn :: (Eq a) => [a] -> [a] -> Bool
allIn ys xs = all (`elem` xs) ys

p_noRepetition :: (Eq a) => [a] -> Property
p_noRepetition xs = True ==> allDifferent (nub' xs) == True

-- Excercise 14
bin :: Integer -> [String]
bin n
    | n < 0 = error ""
    | n == 0 = [""]
    | otherwise = [ x:xs | x <- "01", xs <- bin (n-1) ]

-- Excercise 15
    -- a) varRep
varRep :: Int -> String -> [String]
varRep 0 _ = [""]
varRep m xs = [y:ys | y<-xs, ys <- varRep (m-1) xs]

    -- b) p_varRep
p_varRep m xs = m>=0 && m<=5 && n<=5 && allDifferent xs ==>
                    len vss == n^m
                    && allDifferent vss
                    && all (`allIn` xs) vss
        where
        vss = varRep m xs
        n = len xs
        len :: [a] -> Integer
        len xs = fromIntegral (length xs)

-- Excercise 16
facts :: [Integer]
facts = aux 1 1
    where
        aux accMultiplier accResult = accResult : aux (accMultiplier+1) (accResult*accMultiplier)