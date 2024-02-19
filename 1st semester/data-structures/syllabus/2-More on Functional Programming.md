## Test with QuickCheck
***QuickCheck*** is a Haskell library that helps us testing our programs.

- We define properties that our programs should obey.
- QuickCheck generates random test causes & checks our properties for these cases.

```Haskell
import Test.QuickCheck
```

#### Properties in QuickCheck
```Haskell
precondition ==> postcondition
```
- Precondition & postcondition must be boolean expressions.
- QuickCheck checks postconditions for tests cases satisfying the precondition.
- Use True where no precondition is required:

```Haskell
    True ==> Precondition
```

## Currying
- Functions taking multiple arguments can be represented by only using one argument functions.
- A function taking n arguments is represented by a higher order function that takes a single argument and returns another function taking n-1 arguments.
- By iterating this principle, any function taking n arguments can be represented by a chain of n functions, each one taking a single argument.

```Haskell 
f :: Int -> Int -> Int -> Int 
f x y z = x + 2*y + 3*z

-- This can turn into:
f :: Int -> (Int -> (Int -> Int)) 
f = \x -> (\y -> (\z -> x + 2*y + 3*z))
```

## Function Composition
Composed function takes an argument and applies it to the second function. The result is then applied to the first function, yielding the final result for the composition.

`(.)` higher order operator that takes two (one argument) functions as arguments and returns a new (one argument) function (their composition) as a result.

```Haskell
(.) :: (b -> c) -> (a -> b) -> (a -> c)
f . g = \x -> f (g x)
```

## Algebraic Data Types
- We can define new data types using **Algebraic Data Types** definitions (`data`).
- Each data type definition provides one or more *cases* for data values.
- Each case has an associated tag (*Data Constructor*) and zero or more components.

## Type Classes
- A **Type Class** is an abstract set of operations (functions & operators).
- If a data type implement those operations, we say the type is an **instance** of the class.
- Similar to Java interfaces, but may include default definitions.

```Haskell 
class MyClass a where
    myFunction :: a -> Bool
    
instance MyClass int where
    myFunction x = mod x 3 == 0
instance MyClass Bool where
    myFunction x = not x
```

### Example: Direction
```Haskell 
data Direction = North | South | East | West

-- Eq Type Class
instance Eq Direction where
    North == North = True
    South == South = True
    East  == East  = True
    West  == West  = True
    _     == _     = False

-- Ord Type Class
instance Ord Direction where
    North <= _     = True
    
    South <= North = False 
    South <= _     = True 
    
    East  <= North = False
	East  <= South = False
    East  <= _     = True 
    
    West  <= North = False 
    West  <= South = False 
    West  <= East  = False 
    West  <= _     = True

-- Show Type Class
instance Show Direction where
    show North = "North"
    show South = "South" 
    show East = "East" 
    show West = "West"
```

## Derived Instances
Instances are generated *automagically* if we include **deriving** clause in data definition:
```Haskell 
data Direction = North | South | East | West deriving (Show,Eq,Ord)
```

## Union Data Types
Several data constructors with one component:
```Haskell 
data Degrees = Celsius Double | Fahrenheit Double deriving Show
```

```Haskell 
-- Prelude
data Either a b = Left a | Right b
list :: [Either Int Bool] 
list = [ Left 1, Right True, Left 3, Left 4 ]

data Maybe a = Nothing | Just a
Prelude> lookup 2 [(1,"hi"),(2,"good"),(3,"bye")] 
Just "good" 
Prelude> lookup 6 [(1,"hi"),(2,"good"),(3,"bye")] 
Nothing
```

## Product Data Types
- Only one data constructor with several components.
```Haskell 
type Name = String 
type Surname = String 
type Age = Int 
data Person = Pers Name Surname Age deriving Show
```

## $ Operator
- $ applies an argument to a function. It is used to omit parentheses.
```Haskell 
infixr 0 $
($) :: (a -> b) -> a -> b 
f $ x = f x

Prelude> even $ 9+1
True
Prelude> map (*2) $ [1,2]++[3,4] 
[2,4,6,8]
```
