# Optimization & Local Search
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
