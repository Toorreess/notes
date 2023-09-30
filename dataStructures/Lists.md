# Lists
A list is a **homogeneous** sequence of data.
- **Data Structure**: one list holds several values.
- **Homogeneous**: all values in same list must have equal types

## Syntax
```Haskell
    []                              -- empty list
    [7, 2, 5]                       -- has type [Int]
    [True, False]                   -- has type [Bool]k
    [('a', True), ('a', False)]     -- has type [(Char, Bool)]
    [7, 'a', 5]                     -- !WRONG expression. Elements cannot have different types
```
## Basic Operations on Lists
- First element: 
```Haskell
head :: [a] -> a    -- Undefined for empty lists

Prelude> head [10,7,3,5]
10
```

- List without first element:
```Haskell
tail :: [a] -> [a]  -- Undefined for empty lists

Prelude> tail [10,7,3,5]
[7,3,5]
```

- Is list empty?
```Haskell
null :: [a] -> Bool

Prelude> null []
True
Prelude> null [10,7,3,5]
False
```

- Appending one element at the beginning  
The original list is not modified, a **new** list with one more element is obtained.
```Haskell
(:) :: a -> [a] -> [a]

Prelude> 1 : []
[1]
Prelude> 1 : [3,4,5]
[1,3,4,5]
Prelude> 1 : (2 : (3 : []))
[1,2,3]
Prelude> 1 : 2 : 3 : []     -- (:) associates to the right
[1,2,3]
```

## Strings
List of characters: `type String = [Char]`. In addition to the usual syntax for lists, lists of characters can be written in double quotes (").

## Defining Functions on Lists. Patterns
**Patterns** can be used to define different cases for a function depending on argument structure.

Patterns are inverse to constructors:
- Constructors build/compose data.
- Patterns decompose data.

Posible patterns for lists are:
- `[], [x], [x,y], ...` the argument must be a list with exactly 0, 1, 2, ... elements.
- `(x:xs), (x:y:xs), ...` the argument must be a list with at least 1,2, ... elements.
- *Wild-card*: Used to match against a value we care nothing about, read as "anything". `(x:_), (_:xs)`

### Sorted Lists
Checking if a list is sorted in ascending order:
```Haskell
sorted :: (Ord a) => [a] -> Bool
sorted [] = True
sorted [_] = True
sorted (x:y:xs) = (x<=y) && sorted (y:xs)
```

## List Concatenation
we use the operator `(++)` to concatenate two lists.

#### Example
```Haskell
Prelude> [3,4] ++ [1,2,3]
[3,4,1,2,3]
Prelude> [] ++ [3,4,5]
[3,4,5]
Prelude> [1,2,3] ++ []
[1,2,3]
```
#### Properties
- `[]` is the identity element for `(++)`.
- `(++)` is associative.


### Reversing a List
#### Slow solution
This solution takes $O(n²)$ steps.
```Haskell
reverse :: [a] -> [a]
reverse [] = []
reverse (x:xs) = reverse xs ++ [x]
```
#### Fast solution
For a list of $n$ elements, reverse takes $O(n)$ steps.
```Haskell
reverse :: [a] -> [a]
reverse xs = revOn xs []
    where
      revOn [] ys = ys
      revOn (x:xs) ys = revOn xs (x:ys)
```

## Accumulator Parameters
Technique used in the fast solution of reversing a list. Resembles *loops* in imperative programming.

## Sublists. *take* & *drop*
```Haskell
take :: Int -> [a] -> [a]
-- Returns the prefix of xs of length n, or xs if n > length xs

drop :: Int -> [a] -> [a]
{-Returns the suffix of xs after the first n elements,
  or [] if n > length xs-}
```

## Higher Order Functions
Function taking another function as argument or returning a function as a result.
It's a key feature in functional programming to provide *code reuse*.

### Applying a Function on a List
`map` applies the same function to all elements in a list, returning a list of results:
```Haskell
map :: (a -> b) -> [a] -> [b]
map f [] = []
map f (x:xs) = f x : map f xs

-- Examples
Main> map even [1,2,3,4]
[False,True,False,True]

Main> map square [1,2,3]
[1,4,9]
```

### Selecting Elements from a List
`filter` takes a predicate (*condition*) and a list, and returns a list with the elements in the original list for which the predicate returns `True`:
```Haskell
filter :: (a -> Bool) -> [a] -> [a]
filter p [] = []
filter p (x:xs)
    | p x       = x : filter p xs
    | otherwise = filter p xs

-- Examples
Main> filter even [1,2,0,3]
[2,0]

Main> filter isDigit "p0ep1e"
"01"
```

### Folding Lists

### Lambda Functions
We can pass a function as argument without even naming it. This is called an **anonymous function** or a **λ-function**
```Haskell
Main> map (\x -> x*x) [1,2,3]
[1,4,9]
Main> map (\x -> x+10) [1,2,3,4]
[11,12,13,14]
Main> filter (\x -> mod x 2 == 0) [1,2,3,4]
[2,4]
Main> filter (\x -> x > 2) [10,2,3,1]
[10,3]
```
