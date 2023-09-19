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
- Wild-card: Used to match against a value we care nothing about, read as "anything". `(x:_), (_:xs)`

## List Concatenation
we use the operator `(++)` to concatenate two lists.

##### Example
```Haskell
Prelude> [3,4] ++ [1,2,3]
[3,4,1,2,3]
Prelude> [] ++ [3,4,5]
[3,4,5]
Prelude> [1,2,3] ++ []
[1,2,3]
```
##### Properties
- `[]` is the identity element for `(++)`.
- `(++)` is associative.
