# 4.1 Optimization & Local Search
## What is optimization?
- *State space* $S$: the points, or allowable values.
- *Goal function*: a function $f$ on the state space that the agent want to optimize.
- *Solution*: the state $s$ in the state space such that the $f(x)$ is optimal.

## Continuous optimization
Suppose the state space is a subset of $\mathbb{R}^n$. The *gradient* of $f$ is a vector of partial derivatives: $$\nabla f(\overrightarrow{x}) = (\frac{\partial f}{\partial x_1}, \dots, \frac{\partial f}{\partial x_n})$$
A necessary condition for $f$ having a max or min at $x$ is that its gradient be $0$ at $x$: 
$$
\begin{align}
\nabla f(\overrightarrow{x}) = \overrightarrow{0} \\ \\
\forall i,\ \frac{\partial f}{\partial x_1} = 0
\end{align}
$$
This system of equations can sometimes be solved. Alternatively, an iterative procedure can be followed:
$$\overrightarrow{x}_{t+1} = \overrightarrow{x}_{t} + \alpha[\nabla f(\overrightarrow{x})]_{\overrightarrow{x} = \overrightarrow{x}_{t}}$$
- If $\alpha < 0$, it is a *minimization* procedure called *gradient descent* or *steepest descent*.
- If $\alpha > 0$, it is a *maximization* procedure called *gradient ascent* or *steepest ascent*.

## Optimization as local search

### Problem formulation
- Discrete States.
- State transitions (steps) $\Rightarrow$ neighbor nodes
- Goal test (check whether a state is a goal state, i.e. if the objective function has an optimal value).

### Solution
*Transition* sequence that lead from *initial state* to a *goal state*. However, we can forget the sequence; only the present state is important (*local search*).

### Use
Finding solutions for NP-hard problems.

### Types
#### Single-state algorithms
- Hill-climbing (steepest, stochastic)
- Simulated annealing
#### Population algorithms
- Local beam search
- Genetic algorithms (GAs)

## Hill-climbing: Steepest ascent
The idea is to make the *most improving move*.

![[Pasted image 20240321112132.png]]
### Example
![[Pasted image 20240321112153.png]]

![[Pasted image 20240321112203.png]]

## Hill-climbing: first choice
The idea is to make *any improving move*
![[Pasted image 20240321112420.png]]

## The state-space landscape
![[Pasted image 20240321112447.png]]

## Simulated annealing
The idea is to escape local maxima by allowing *some bad moves* but *gradually decrease their size and frequency*.

![[Pasted image 20240321113407.png]]

![[Pasted image 20240321113445.png]]


**Theoretical property:** If the cooling schedule $T(t)$ is “slow enough”, then a global maximum is reached with probability 1.

## Genetic Algorithms
- There is a **population** of states (*individuals*).
- The individuals are rated by the objective function, or (in GA terminology) the **fitness function**.
- The algorithm produces generations of individuals iteratively.
- The probability that the **selection** process chooses an individual for reproducing is directly proportional to its fitness.
- The reproduction is carried out by two mechanisms:
	- *Crossover* (combination of two individuals).
	- *Mutation* (modification of a single individual).

![[Pasted image 20240505175918.png]]


# 4.2 Graph Search: A* Algorithm
## Problem Formulation
**Problem formulation:**
- States
- Initial state
- State transitions (*steps*) => neighbor nodes
- Goal test => check whether a state is a goal state
- Cost of each step => a positive number

**Solution:** *Transition* sequence that leads from the *initial state* to a *goal state*.
	**Optimal:** Has the lowest *path cost* among all solutions.
## States vs. search nodes
- A **state** is a physical configuration. States don't have parents, children, depth or path cost.
- A **node** is a data structure constituting part of a search tree. Includes the *state, parent, children, depth* & the *path cost $g(x)$*.

![[Pasted image 20240505180519.png]]


## Data structures for the algorithm
- **Nodes**
- **Search tree**
	- *Root:* a node containing the initial state.
	- Every node must contain a different state.
- **Open & Closed lists**
	- *Open:* states in leaf nodes.
	- *Closed:* states in internal nodes.

## Tree Expansion
A step of the algorithm: *Expand the search tree.* Expansion:
1. *Select* an open state (node).
2. Move it to *closed* list.
3. Generate all their *successors*.
4. Modify suitably the *tree*.

## Heuristic(s)
- A simple procedure that helps find adequate, though often imperfect, answers to difficult questions.
- An estimation of the cost of a solution.
- If it satisfies certain properties, it can help in finding perfect, optimal solutions.

## Node selection
- $h(n)=$ estimated cost of the cheapest path from the state at node $n$ to a goal state. A heuristic $h$ is an estimation of how good a *state* is. 
 
**Evaluation function $f(n)$**
- It is applied to a *node* $n$.
- It ranks the nodes so that the algorithm selects from the open list the *best* one according to $f$. 
- It can be defined in many ways.

**Greedy search:** $f(n) = h(n)$. Not a sensible option if looking for the *best* path! (The algorithm ignores the cost from the root to $n$)

The evaluation function for A* is computed as follows:
1. $g(n) =$ cost of the cheapest path found from the initial state to node $n$
2. $f(n) = g(n) + h(n)$: 
	- $g(n)$: Real path cost from the initial state to the state at node $n$.
	- $h(n)$: Estimated path cost from the state at node $n$ to a goal state.
1. It estimates the best cost of a path (solution) through $n$.

# 4.3 Properties of A*. Simplified Cases.
## Criteria for Evaluation
- **completeness** - does it always find a solution if one exists?
- **optimality** - does it always find a best solution?
- **time complexity** - number of nodes generated/expanded.
- **space complexity** - maximum number of nodes in memory.
- Complexity is measured as a function of:
	- *b*: maximum branching factor of the search tree.
	- *d*: depth of the best (least-cost) solution.
	- *m*: maximum depth of the state space (may be $\infty$).

## Definitions about trees/graphs
- *Finite tree (graph)*: A tree with a finite number of nodes. 
- *Boundedly branching tree (graph)*: There exists a minimum number $b$ such every node has at most $b$ successors.
- $b$ is the (maximum) branching factor of the tree (graph), or $b$ is or $\infty$ if such a number doesn't exist.
- *Finitely branching tree (graph)*: no state $s$ has an infinite number of successors.
- *Graph with positive costs*:  There exist a real number $\varepsilon>0$ such that for every state $s$ and successor $s', c(s, s') \geq \varepsilon$.

## Definitions about heuristics
- A heuristic function $h(s)$ is *optimistic* iff $h(s)$ is never an overestimation of the optimal cost from $s$ to a goal. 
- A heuristic is *consistent* iff
	- for every $s$ and every successor $s'$ of $s: h(s) \leq h(s') + c(s, s')$ 
	- for every goal state $m$, $h(m) = 0$. 
	- That means that $h$ is at least as accurate for the children of $s$ as it is for $s$.
 
Every consistent heuristic is optimistic. However, the converse is not true.

## Optimality of A*
If $h$ is *consistent*, and the graph is finitely branched and with positive costs, and there exists a solution, then A* returns an *optimal* solution.

## Time/Space complexity of A*
- Any node whose path from the start has length $l > L = \dfrac{C*}{\varepsilon}$ cannot be expanded. Therefore, there can be at most $O(b^L)$ expansions. Hence time complexity is $O(b^L)$.
- Since expanded nodes are not erased from memory (A* needs the closed list), this is also the maximum number of stored nodes and hence space complexity is also $O(b^L)$.

## Properties of Uniform-Cost
- Uniform-Cost is A* with $h(n) = 0$.
- Hence, the same properties as A*.
- This heuristic is consistent.
- Therefore we always get an optimal solution.

## Are all heuristics the same?
$h$ is more informed than $h'$ iff for every non goal node $s$, $h'(s) \leq h(s)$.

# 4.4 Search: Backtracking
## Depth-first
Space is a big problem for A* and related algorithms. So, we can try another approach: 
- Maintain just a path (a stack) from the start to current state. 
- The selected node will always be the last one in the path (if the path is empty, return failure.) 
- If the selected node is a goal, return the path. 
- Otherwise, backtrack, (i. e. discard the last element in the stack) or go forward (i. e., add a new element to the stack). 
- When the algorithm reaches a blind alley or a fixed depth limit, it backtracks; else it goes forward.

## Depth-first & Backtracking
There are two variants of the method: 
1. Expand the selected node fully, i. e., generate *all successors*. It is what is properly called *depth-first search.*
2. Expand the node partially, i. e., generate *just one successor*. Then the algorithm must remember which successors of the node has been already generated. It is what is properly called *backtracking search* 

Additionally, the algorithm can check the generated successor(s) against the states in the path in order to avoid cycles.

## Properties of Depth-first / Backtracking
- Backtracking is not a variant of A*
- Properties are very different.

### Completeness/Optimality
- If there is an infinite path and the algorithm tries it, the algorithm does not terminate even if there is a solution.
- On the other hand, if a depth limit is set and the shallowest solution lies at a deeper level, the algorithm terminates without finding any solution.
- So, Backtracking is not complete nor optimal.

### Time complexity
Let $m$ be the maximum depth of the tree. 
- In the worst case we must generate all the states, i. e., $O(b^m)$
- Let $d$ be the depth of the optimal solution. Notice that Breadth-first generates $O(b^d)$ states, that can be a smaller number (never a greater one).

### Space complexity
We store just one path. The maximum size of the path is m, so we must store $O(m)$ states.

That can be much better than Breadth-First, but admissibility is lost. 

However, there is a smart backtracking algorithm that is always admissible, ever for infinite graphs. It is called iterative deepening: the algorithm sets a limit, initially 0. If a solution is found, returns it; otherwise, increments the limit and does it again

### Properties of Iterative-Deepening
- It is *complete* (and optimal if all costs are equal).
- *Space complexity*: for finite graphs, the maximum size of the path is $m$. So, we must store $O(m)$ states.
- *Time complexity*: it seems that the algorithm wastes a lot of time, generating all states from the start time after time. In fact it generates $O(b^d)$.

# 4.5 Problem-Solving as Search
## Heuristic Search Hypothesis
The solutions to problems are represented as symbol structures. **A physical symbol system exercises its intelligence in problem solving by search**. That is, by generating and progressively modifying symbol structures until it produces a solution structure.

## Problem Formulation
**Problem formulation:**
- States.
- Actions that can be executed (*state transitions*).
- Initial state.
- Goal test (check whether a state is a goal state)
- Cost of each action (step in a path)

**Solution:** *Action* sequence that leads from the *initial state* to a *goal state*.
	- **Optimal:** : Has the lowest *path cost* among all solutions.