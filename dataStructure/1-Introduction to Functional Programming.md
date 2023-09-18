Based on computing with mathematical (or pure) functions:
1. Takes some data (*Arguments*)
2. Does some calculation with data (*Expression*)
3. Returns new data (*Result*)

**Nothing is mutated.**
##### Example
```Haskell
factorial :: Integer -> Integer
factorial 0 = 1
factorial x | x > 0 = x * factorial (x-1)
```

## Leibniz's rule
The functional programming follows the Leibniz's rule: 
$$x = y => f(x) = f(y)$$

This property is called ***Referential Transparency***: same expression always denote same value.

## Haskell Properties
- Pure functional.
- Statically typed.
- Lazy evaluation.
- Case sensitive.
- Curried notation.
## Identifiers
#### Functions & argument:
- Must start in lowercase letter.
- Followed by letters, digits, apostrophe and underline.

### Type:
- Must start with uppercase letter.

### Operator:
- Sequence of one or more symbols.
- First cannot be a colon.

## Numeric Types
- **Int**: fixed precision integer numbers.
- **Integer**: unlimited precision integer numbers.
- **Float**: single precision floating point numbers.
- **Double**: double precision floating point numbers.

## Boolean
Two values: ***True*** & ***False***.
#### Operators
- Logical negation (not): 
```Haskell
not :: Bool -> Bool
```
- Logical conjunction (and):
```Haskell
(&&) :: Bool -> Bool -> Bool
```
- Logical disjunction (or):
```Haskell
(||) :: Bool -> Bool -> Bool
```

## Characters
- Includes all characters: letters, digits, symbols... 
- Functions operating on Char are defined in `Data.Char`

## Operators
- Numeric negation is the only **unary** operator in Haskell (*prefix form*)
- Rest of operators are **binary** (*infix form*)

## Precedence levels
| Level |  Predefined Operators  |                    |
|:-----:|:----------------------:|:------------------:|
|  10   | Functions Applications | Highest precedence |
|   9   |          . !!          |                    |
|   8   |        ^ ^^ **         |                    |
|   7   |          * /           |                    |
|   6   |          + -           |                    |
|   5   |          : ++          |                    |
|   4   |    == /= < <= > >=     |                    |
|   3   |           &&           |                    |
|   2   |          \|\|          |                    |
|   1   |       >> >>= =<<       |                    |
|   0   |          $ $!          | Lowest precedence  |

## Asociativity
Used to remove ambiguity on expressions with several uses of the same operator.

|  Associativity  |  Predefined Operators  |
|:---------------:|:----------------------:|
|      Right      | ^ ^^ ** : && \|\| $ $! |
|      Left       |   * / + - >> >>= =<<   |
| Non associative |    == /= < <= >= >     | 

**Note:** When uncertain about associativity, you can use parentheses

## Overloaded Types. Numerics
*Num* is a predefined type class for numeric types (integers, floatings, rationals...).
Some operators and functions are only defined for numeric types:

```Haskell
(+), (-), (*) :: (Num a) => a -> a -> a
```

## Overloaded Types: Integrals
- *Integral* is a predefined class for integral types (integers).
- Some functions are only defined for integral types:

```Haskell
div, mod :: (Integral a) => a -> a -> a
```


## Overloaded Types: Fractionals
- *Fractional* is a predefined class for fractional types (floating numbers, rationals...)
- Some functions are only defined for fractionals:

```Haskell
(/) :: (Fractional a) => a -> a -> a
```


## Overloaded Types: Ordered Types
- *Ord* is a predefined class for ordered types (integers, floating numbers, rationals, characters, booleans...)
- Some operators and functions are only defined for ordered types:
```Haskell
(<), (<=), (>), (>=) :: (Ord a) => a -> a -> Bool

compare :: (Ord a) => a -> a -> Ordering
data Ordering = LT | EQ | GT

max, min :: (Ord a) => a -> a -> a
```


## Overloaded Types. Equals
- *Eq* is a predefined class of types defining equality and inequality (integers, floating numbers, rationals, characters, booleans...)
- Some operators and functions are only defined for types with equality:
```Haskell
(==), (/=) :: (Eq a) => a -> a -> Bool
```

## Conditional Equations
We usually have to consider different cases when defining a function:
```Haskell
sign :: (Eq a, Ord a, Num a) => a -> a
sign x  | x > 0  = 1
		| x < 0  = -1
		| x == 0 = 0
```

This function can also be defined as (***Preferred style***):
```Haskell
sign :: (Eq a, Ord a, Num a) => a -> a
sign x  | x > 0  = 1
		| x < 0  = -1
		| otherwise = 0
```

## Conditional Expressions
Another way of considering different cases:
```Haskell
abs :: (Ord a, Num a) => a -> a
abs x = if x >= 0 then x else -x
```


## Partially Defined Functions
Functions that are undefined for some arguments. Applying function to those arguments raises an exception.
```Haskell
reciprocal :: (Eq a, Num a, Fractional a) => a -> a
reciprocal x | x == 0 = error "reciprocal undefined"
			 | otherwise = 1/x
```


## Local Definitions: where
- *where* clause is used to define functions or variables that are only usable inside another function (local definitions).
- *where* clause is written at the end of a function definition.
```Haskell
circleArea :: Double -> Double
circleArea r = pi * r^2

rectArea :: Double -> Double -> Double
rectArea b h = b*h

circlePerimeter :: Double -> Double
circlePerimeter r = 2*pi*r

cylinderArea :: Double -> Double -> Double
cylinderArea r h = 2*circ + rect
	where                     -- Must be indented with respect to function definition
		circ = circleArea r
		l    = circPerimeter r
		rect = rectArea l h
```


## Operator Definitions
``` Haskell
-- Checks whether two doubles are approximately equal
infix 4 ~= -- Level of precedence (from 0 to 9)
(~=) :: Double -> Double -> Bool
x ~= y = abs (x-y) < epsilon
	where epsilon = 1/1000
```
*infix*  = non associative
*infixl* = left associative
*infixr* = right associative


## Operators vs Functions
- Any binary function can be used as an operator (infix notation) using back quotes (\`)
```Haskell
Prelude> div 10 2
5
Prelude> 10 `div` 2
5
Prelude> 10 div 2
TYPE ERROR ...
Prelude> max 10 (max 3 15)
15
Prelude> 10 `max` 3 `max` 15
15
```

- Any binary operator can be used as a function (prefix notation) using parentheses.
```Haskell
Prelude> 10 + 2
12
Prelude> (+) 10 2
12
Prelude> + 10 2
SYNTAX ERROR
```


## Local Definitions: let in
*let in* expressions can also be used to introduce local definitions:
```Haskell
circArea :: Double -> Double
circArea r = pi*r^2

rectArea :: Double -> Double -> Double
rectArea b h = b*h

circLength :: Double -> Double
circLength r = 2*pi*r

cylinderArea :: Double -> Double -> Double
cylinderArea r h =
	let
		circ = circArea r
		l    = circLength r
		rect = rectArea l h
	in
		2*circ + rect
```
