# 5.1 CSP Problems
## Definition
A CSP consists of three components:
- A set of *variables*, $X = \set{X_1,\dots,X_n}$
- A set of domains, one for each variable: $D = \set{D_1, \dots,D_n}$
- A set of constraints that specify allowable combinations of values, $C$. Each constraint consists of a pair $\text{<scope, rel>}$ where *scope* is a tuple of variables and *rel* is the specification of the values that tuples can take.

Each state in the process of solvin a CSP is defined by an assignment of values to some or all of the variables. An assignment which does not violate any constraint is called a *consistent* or *legal assignment*.

If we have an assignment in which every variable is assigned, then we call it a *complete assignment*. Otherwise, we call it a *partial assignment*.

A **solution** to a CSP is a consistent, complete assignment.

## Types of domains
Domains can be **finite** (boolean, enumerated,...), **discrete** (integer values) or even **continuous** (real intervals). We will consider only finite domains.

## Types of constraints
- *Unary constraints*: the scope is a singleton. More clearly, the constraint involves a single variable, e.g., $\text{SA} \neq \text{green}$
- *Binary constraints*: involve pairs of variables, e.g., $\text{SA} \neq \text{WA}$.
- Higher-order constraints involve 3 or more variables.
## Node consistency
A variable is node-consistent if all the values in the variable’s domain satisfy the variable’s unary constraints.
- It is always possible to eliminate all unary constraints in a CSP by running node consistency. We will always do this.

# 5.2 Search
## Factored Representations
A state is represented by structure composed of simpler elements, namely, an assignment, An assignment is a set of pairs $\text{variable }X = \text{value }V$.
## Definition of the state space
Final states: complete, consistent assignments.
- *Complete assignment $A$*: for each variable $X$ there is a pair $X=V$ ((otherwise $A$ is a partial assignment).
- *Consistent assignment $A$*: no constraint is violated by $A$.

## Backtracking search: initial state & successors
- Initial state: the empty assignment
- Successor of state S:
	- S inconsistent : no successor
	- Otherwise:
		- Select next variable $X_i$ (according to some rule)
		- Select next value $V_j$ for $X_i$ (according to some rule)
		- add $(X_i , V_j)$ to the partial assignment

## Backtracking search: goal
Goal state: a complete, consistent assignment.
- *Complete assignment $A$:* for each variable $X$ there is a pair $X=V$ (otherwise $A$ is a partial assignment).
- *Consistent assignment $A$:* no constraint is violated by $A$.

## Backtracking search
- Depth is limited by the number $n$ of variables, so the algorithm always halts.
- The branching factor is the size $d$ of the domains.
- Time complexity is $O(n^d)$.
- Finite CSP is a NP-complete problem.

## Backtracking: Variable ordering
- Usually we choose the variable with the fewest legal values. This is called the minimum remaining values (**MRV**) heuristic.
- In order to break ties we can use the degree heuristic (**DEG**), which chooses the variable that is involved in the largest number of constraints with other unassigned variables.
- This way of selecting variables tries to obtain a failure as soon as possible to prune larger parts of the search tree earlier.
- In some cases the least constraining value (**LCV**) heuristic can be useful.
- It prefers the value that removes the fewest choices for the connected.
- This heuristic tries to obtain a solution as soon as possible by choosing the most likely values first.

## Local search
- All states are **complete assignments**.
- During the search we allow **inconsistent assignments** (they don’t satisfy all constraints).
- A move is a change of the value of a variable (**reassignment**).
- Then apply steepest-descent, simulated annealing, or even genetic algorithms.


## 4.3 Constraints & Consistency
## Binary constraints: constraint graph
![[Pasted image 20240505192045.png]]

## Arc consistency
- A variable $X_i$ is arc-consistent if, for every value in the domain of $X_i$ and every binary constraint $(X_i ,X_j)$, we can find at least one value in the domain of $X_j$ which satisfies the constraint.
- A network is arc-consistent if every variable is arcconsistent with every other variable.

## AC-3 algorithm for arc consistency
- It maintains a set of arcs to consider.
- If the set is empty, it returns *true*.
- Initially, the set contains all the arcs in the CSP.
- It extracts an arbitrary arc $(X_i ,X_j)$ from the set and deletes from the domain of $X_i$ those values non supported with respect $X_j$.
	- If this reduces the domain $D_i$, then it adds to the set all arcs $(X_k, X_i)$, where $X_k$ is a neighbor of $X_i$.
- If a domain is reduced to nothing, then the original CSP has no solution, and it returns *false*. Otherwise it extracts another arc and loops.

![[Pasted image 20240505192603.png]]

## AC-3 Outcome
*Simple cases:*
- Empty domain => No solution
- Exactly one value per domain (& all constraints binary) => This is the solution
Thus, in this cases no search is needed.

*Otherwise* => searching is needed in the simplified domains.