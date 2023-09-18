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


