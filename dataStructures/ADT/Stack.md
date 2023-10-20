A **stack** is a container data structure that organises its elements in a **LIFO** (Last In, First Out) policy.
## Signature for Stacks
```Haskell
data Stack a 
-- returns an empty stack 
empty :: Stack a 
-- tests for empty stack 
isEmpty :: Stack a -> Bool 
-- inserts element on top of stack 
push :: a -> Stack a -> Stack a 
-- returns element on top of stack 
top :: Stack a -> a 
-- returns stack obtained by deleting element on top 
pop :: Stack a -> Stack a
```

## Axioms for Stacks
```Haskell 
-- an empty stack is empty 
True ==> isEmpty empty 
-- push returns a non-empty stack 
True ==> not (isEmpty (push x s)) 
-- top returns last pushed element 
True ==> top (push x s) == x
-- pop removes last pushed element 
True ==> pop (push x s) == s
```

## Implementing a Stack with Recursive data Type
```Haskell
data Stack a = Empty | Node a (Stack a) deriving show
-- 0 elements Stack
Empty
-- 1 element Stack
Node 1 Empty
-- 2 elements Stack
Node 2 (Node 1 Empty)
-- 3 elements Stack
Node 3 (Node 2 (Node 1 Empty))
-- 
top :: Stack a -> a
top Empty = error "top for empty stack"
top (Node x s) = x
--
size :: Stack a -> Int
size Empty = 0
size (Node x s) = 1 + size s
``` 

## Java: Interface for Stacks
```java 
package dataStructures.stack;

public interface Stack<T>{
	boolean isEmpty();
	void push(T elem);
	T top();
	void pop();
}
```

## Java: Stack with Linked Nodes
For every Node in the Stack, we store the value of the node and a reference to the next node.

## Java: Stack with Arrays
Consumes less memory than linked list implementation, but we need to keep track of position holding next pushed element in array and we have to expand the array in case we run out of memory.
