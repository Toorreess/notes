## Abstract Data Types (ADT)
- Mathematical model for a certain class of data structures that have similar behaviour.
- An *ADT* can be implemented in different ways. Different implementations may be more appropiate for different purposes.
- We will specify an ADT by providing:
	- **A signature**: defines different available operations and types of corresponding arguments and results
	- **A set of axioms**: defines the semantics of operations by means of different properties that any implementation should obey.

## Modules (Intermezzo)
- A Haskell module is a collection of related declarations: functions, data types, type classes…
- Splitting up a program into several modules has many advantages:
	- Functions exported by a module can be used in others programs (*libraries*).
	- Using modules makes more manageable the work of writing complex programs.
	- Different programmers may work concurrently on different modules.

- In Haskell, each module must be written in its own source file. 
- Module names are indentifiers starting with upper case letter. 
- The name of the file must be the same as the name of the module.
- The module file must start with a *module header*:
```Haskell
module ModuleName (Identifier1, Identifier2, … , IdentifierN ) where
-- This is a list of functions, data types & type classes that are provided by this module. Clients importing this module can only use what is provided here.
```

## Java: Interfaces & Implementations
We will separate interfaces for data structures and their implementations.
-  **Interface**: signature describing operations supported by data structure.
- **Implementation**: actual code realizing data structure.
- **Cilent**: a program using the data structure.

## Iterators vs Folds
- An ***iterator*** is a design pattern that abstracts the process of traversing a sequence of elements.
- In Haskell a fold operation is used instead to operate all elements in a data structure.
