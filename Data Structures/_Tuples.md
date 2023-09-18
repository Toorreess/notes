**Heterogeneous** data structure
- Collection of values, called **_components_**.
- Each component may have a different type → **POLYMORPHIC**.
### Syntax
Components separated by commas & grouped with parentheses:
``` Haskell
(True, 2) {-Has type-} (Bool, Int)
(10, 2, 7) {-Has type-} (Int, Int, Int)
```
##### Example
```haskell
succPred :: Int -> (Int, Int) -- Input is a number, output a tuple with 2 integers
succPred x = (x+1, x-1)

-- Execution
succPred 10 
=> {- succPred definition -}
	(10+1, 10-1)
=> {- arithmetic -}
	(11, 9)
```

##### Polymorphic Types
Two predefined functions for 2 components tuples:
``` Haskell
-- a & b are type variables. These functions take tuples with 2 components of any type.
fst :: (a, b) -> a
fst (x, y) = x

snd :: (a, b) -> b
snd (x, y) = y

-- Execution
fst (1, True)
=> {- fst definition -}
	1

snd (1, True)
=> {- snd definition -}
	True
```
