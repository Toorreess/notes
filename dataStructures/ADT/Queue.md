A **queue** is a container data structure that organises its elements in a **FIFO** (First In, First Out) policy.

## Signatures for Queues
```Haskell
data Queue a
-- returns an empty queue 
empty :: Queue a 
-- tests for empty queue 
isEmpty :: Queue a -> Bool 
-- inserts element at the end of queue 
enqueue :: a -> Queue a -> Queue a 
-- returns first element on queue 
first :: Queue a -> a 
-- returns queue obtained by deleting first element 
dequeue :: Queue a -> Queue a
```

## Axioms for Queues
```Haskell
-- the empty queue is empty 
True ==> isEmpty empty 
-- enqueue always returns non-empty queues 
True ==> not (isEmpty (enqueue x q)) 
-- an element inserted in an empty queue is placed at the front of the queue
True ==> first (enqueue x empty) == x 
-- inserting an element in a non-empty queue does not alter its front element
not (isEmpty q) ==> first q == first (enqueue x q)
-- inserting and removing an element in an empty queue returns an empty queue 
True ==> dequeue (enqueue x empty) == empty 
-- for non-empty queues, dequeue and enqueue commute
not (isEmpty q) ==> dequeue (enqueue x q) == enqueue x (dequeue q)
```

## Haskell: Linear Implementation
```Haskell
data Queue a = Empty | Node a (Queue a)
-- A 0 elements queue: 
Empty
-- A 1 element queue: 
Node 1 Empty
-- A 2 elements queue: 
Node 1 (Node 2 Empty) 
-- A 3 elements queue: 
Node 1 (Node 2 (Node 3 Empty))
--
empty :: Queue a 
empty = Empty 
--
isEmpty :: Queue a - > Bool 
isEmpty Empty = True 
isEmpty _ = False
--
enqueue :: a -> Queue a -> Queue a 
enqueue x Empty = Node x Empty 
enqueue x (Node y q) = Node y (enqueue x q) 
--
first :: Queue a -> a 
first Empty = error "first on empty queue" 
first (Node x q) = x 
--
dequeue :: Queue a -> Queue a 
dequeue Empty = error "dequeue on empty queue"
dequeue (Node x q) = q
```

## Java: Interface for Queues
```java
package dataStructures.queue; 
public interface Queue { 
boolean isEmpty(); 
void enqueue(T elem);
T first();
void dequeue(); 
}
```

## Java: Queues with Linked Nodes
We'll have 2 references, `first` and `last`, referencing the first and the last nodes of the queue. Every Node must store not only its value, but also a reference to the preceding Node. 

## Java: Queues with Arrays
We need to keep track of first and last nodes, the size occupied, and we have to expand the array in case we run out of memory.
