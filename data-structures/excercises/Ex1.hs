-------------------------------------------------------------------------------
-- Data Structures. ETSI Informatica. UMA
--
-- Degree: Grado en Ingeniería del Software.
-- Student: Torres, Jose
-- Date: 2023-09-18
--
-------------------------------------------------------------------------------
{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use camelCase" #-}
import Test.QuickCheck ( (==>), Property )

-- Exercise 1.     
    -- a) isTriple
isTriple :: Integer -> Integer -> Integer -> Bool
isTriple x y z  | x*x + y*y == z*z = True
                | otherwise = False

    -- b) triple
triple :: Integer -> Integer -> (Integer, Integer, Integer)
triple x y = (x*x - y*y, 2*x*y, x*x + y*y)

    -- c) QuickCheck
p_triples :: Integer -> Integer -> Property
p_triples x y = x>0 && y>0 && x>y ==> isTriple l1 l2 h
    where
        (l1,l2,h) = triple x y


-- Exercise 2. swap
swap :: (a,b) -> (b,a)
swap (x,y) = (y,x)


 -- Exercise 3.
    -- a) sort2
sort2 :: Ord a => (a,a) -> (a,a)
sort2 (x,y) | x >= y = (x,y)
            | otherwise = (y,x)

    -- b) QuickCheck 
p1_sort2 :: Ord a => a -> a -> Property
p1_sort2 x y = True ==> sorted (sort2 (x,y))
    where sorted (x,y) = x<=y

p2_sort2 :: Ord a => a -> a -> Property
p2_sort2 x y = True ==> sameElements (x,y) (sort2 (x,y))
    where
        sameElements (x,y) (x',y') = (x==x' && y==y') || (x==y' && y==x')

    -- c) sort3
sort3 :: Ord a => (a,a,a) -> (a,a,a)
sort3 (x, y, z)
    | x > y     = sort3 (y, x, z)
    | x > z     = sort3 (y, z, x)
    | y > z     = sort3 (x, z, y)
    | otherwise = (x, y, z)

    -- QuickCheck
p1_sort3 :: Ord a => a -> a -> a -> Property
p1_sort3 x y z = True ==> sorted (sort3 (x,y,z))
    where sorted (x,y,z) = x <= y && y <= z

p2_sort3 :: Ord a => a -> a -> a -> Property
p2_sort3 x y z = True ==> sameElements (x,y,z) (sort3 (x,y,z))
    where
        sameElements (x,y,z) (x',y',z') =
            (x==x' && y==y' && z==z') || (x==x' && y==z' && z==y') ||
            (x==y' && y==x' && z==z') || (x==y' && y==z' && z==x') ||
            (x==z' && y==x' && z==y') || (x==z' && y==y' && z==x')


-- Exercise 4. max2
max2 :: Ord a => a -> a -> a
max2 x y
    | x >= y = x
    | otherwise = y

    -- QuickCheck
p1_max2 :: Ord a => a -> a -> Property
p1_max2 x y = True ==> (x == max2 x y) || (y == max2 x y)

p2_max2 :: Ord a => a -> a -> Property
p2_max2 x y = True ==>(x <= max2 x y) || (y <= max2 x y)

p3_max2 :: Ord a => a -> a -> Property
p3_max2 x y = (x >= y) ==> (max2 x y == x)

p4_max2 :: Ord a => a -> a -> Property
p4_max2 x y = (y >= x) ==> (max2 x y == y)


-- Excercise 5. between
between :: Ord a => a -> (a,a) -> Bool
between x (min, max)
    | x >= min && x <= max  = True
    | otherwise             = False


-- Exercise 6. equals3
equals3 :: Eq a => (a,a,a) -> Bool
equals3 (x,y,z)
    | x==y && y==z   = True
    | otherwise      = False


-- Exercise 7.
    --a) decompose
type Hour = Integer
type Minute = Integer
type Second = Integer

decompose :: Second -> (Hour, Minute, Second)
decompose x = (hh,mm, ss)
    where
        hh = div x 3600
        mm = div (mod x 3600) 60
        ss = mod x 60

    -- b) QuickCheck
p_decompose :: Second -> Property
p_decompose x = x>=0 ==> h*3600 + m*60 + s == x
                            && between m (0,59)
                            && between s (0,59)
    where (h,m,s) = decompose x


-- Exercise 8.
oneEuro :: Double
oneEuro = 166.386

    -- a) pesetasToEuros
pesetasToEuros :: Double -> Double
pesetasToEuros x = x / oneEuro

    -- b) eurosToPesetas
eurosToPesetas :: Double -> Double
eurosToPesetas x = x * oneEuro

    -- c) QuickCheck
p_inverse_bad :: Double -> Bool
p_inverse_bad x = eurosToPesetas (pesetasToEuros x) == x


-- Exercise 9. ~=
infix 4 ~=
(~=) :: Double -> Double -> Bool
x ~= y = abs (x-y) < epsilon
    where epsilon = 1/1000

    -- QuickCheck
p_inverse :: Double -> Bool
p_inverse x = eurosToPesetas (pesetasToEuros x) ~= x


-- Exercise 10.
    -- a) roots
roots :: (Num a, Fractional a, Floating a, Ord a, Ord a, Ord a) => a -> a -> a -> (a,a)
roots x y z =  ((-y+discriminant) / (2*x), (-y-discriminant) / (2*x))
    where
        discriminant = if (y*y - 4*x*z) >= 0 then sqrt (y*y - 4*x*z) else error "Non real roots"

    -- b) QuickCheck
p1_roots :: Double -> Double -> Double -> Property
p1_roots a b c = True ==> isRoot r1 && isRoot r2
    where
        (r1,r2)  = roots a b c
        isRoot r = a*r^2 + b*r + c ~= 0

p2_roots :: Double -> Double -> Double -> Property
p2_roots a b c = a > 0 && (b*b - 4*a*c) >= 0 ==> isRoot r1 && isRoot r2
    where
        (r1,r2)  = roots a b c
        isRoot r = a*r^2 + b*r + c ~= 0


-- Exercise 11. isMultiple
isMultiple :: (Integral a) => a -> a -> Bool
isMultiple x y = mod x y == 0


-- Exercise 12. logical implication operator
infixl 1 ==>>
(==>>) :: Bool -> Bool -> Bool
x ==>> y = not x || y


-- Exercise 13. isLeap
isLeap :: Integral a => a -> Bool
isLeap x = isMultiple x 4 && (isMultiple x 100 ==>> isMultiple x 400)


-- Exercise 14
    -- a) power
power :: (Num a, Integral a, Ord a) => a -> a -> a
power b n
    | n == 0    = 1
    | n > 1     = b * power b (n-1)
    | otherwise = error "n must be a positive integer number"

    -- b) power'
power' :: (Integral a) => a -> a -> a
power' b n
    | n == 0 = 1                                          -- Base case
    | n > 0 && mod n 2 == 1 = b * power' b (n-1)          -- ODD
    | n > 0 && even n = p * p                             -- EVEN
    | otherwise = error "n must be a positive integer number"
    where
        p = power' b (div n 2)

    -- c) QuickCheck
p_power :: Integral a => a -> a -> Property
p_power b n = n>=0 ==> power b n == sol
                        && power' b n == sol
    where sol = b^n


-- Exercise 15. factorial
factorial :: Integral a => a -> a
factorial x
    | x < 2     = 1
    | otherwise = x * factorial (x-1)

-- Excercise 16. 
    -- a) divides
divides :: Integral a => a -> a -> Bool
divides x y 
    | mod y x == 0  = True
    | otherwise     = False

    -- b) QuickCheck
p1_divides :: Integral a => a -> a -> Property
p1_divides x y = y/=0 && y `divides` x ==> div x y * y == x

    -- c) Quickcheck v2
p2_divides :: Integral a => a -> a -> a -> Property
p2_divides x y z = x/=0 && (y+z) /=0 && divides x y && divides x z ==> divides x (y+z)

-- Exercise 17. median
median :: Ord a => (a,a,a,a,a) -> a
median (x,y,z,t,u)
    | x > z = median (z, y, x, t, u)
    | y > z = median (x, z, y, t, u)
    | t < z = median (x, y, t, z, u)
    | u < z = median (x, y, u, t, z)
    | otherwise = z