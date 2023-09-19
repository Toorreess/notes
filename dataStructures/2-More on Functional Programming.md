# U2-More on Functional Programming

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

