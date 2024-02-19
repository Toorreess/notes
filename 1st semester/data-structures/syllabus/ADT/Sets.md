A **set** is a data structure that can store certain values, without any particular order, and no repeated values.

## Signature for Sets
```Haskell
data Set a 
-- returns an empty set 
empty :: Set a 
-- tests for empty set 
isEmpty :: Set a -> Bool 
-- inserts element in set 
insert :: (Eq a) => a -> Set a -> Set a 
-- tests if element is in set 
isElem :: (Eq a) => a -> Set a -> Bool 
-- removes element from set 
delete :: (Eq a) => a -> Set a -> Set a
```

## Axioms for Sets
```Haskell
ax1 = True ==> isEmpty empty 
ax2 x s = True ==> not (isEmpty (insert x s)) 
ax3 x = True ==> not (isElem x empty ) 
ax4 x y s = True ==> isElem x (insert y s) == (x==y) || isElem x s 
ax5 x = True ==> delete x empty == empty
ax6 x y s = x==y ==> delete x (insert y s) == delete x s
ax7 x y s = x/=y ==> delete x (insert y s) == insert y (delete x s)
```