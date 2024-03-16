Linear regression is an important and well-known technique and it will be useful to present some fundamental concepts in learning.
# Problem Definition
## Example
![[Pasted image 20240316200311.png]]

# Dataset
A dataset is a finite sequence $$\{(x_1, y_1),\ \dots,\ (x_N, y_N)\}$$ where: 
- Each $\mathbf{x}_j$ is a vector $(x_{j,1},\ \dots,\ x_{j,n})$ of $n$ real components (*inputs*).
- Each $y_j$ is a real number (*output*).

In the example, $N = 47,\ n=1$ and, for instance, $x_3 = (x_{3,1}) = (690),\ y_3 = 385)$.

## Hypothesis space
The *hypothesis space* $\mathcal{H}$, from where the solution $h$ is selected, is the set of all linear (or affine) functions on $n$ variables:
$$h_\mathbf{w}(\mathbf{x}_j) = w_0 + w_1x_{j,1} + \dots + w_nx_{j,n} = w_0 + \sum_iw_ix_{j,i}$$
Graphically, 
- If $n=1$: lines.
- If $n=2$: planes.
- If $n>2$: hyperplanes.

$\mathbf{w}$ is the vector of weights.

the term $w_0$, the *intercept*, stands out as different from the others. We can fix that by inventing a dummy input attribute $x_0$, which is always $x_0 = 1$.

Then $h$ is simply the dot product of the weights and the input vector (or equivalently, the matrix product of the transpose of the weights and the input vector):
$$h_\mathbf{w}(x_j) = \mathbf{w} \cdot \mathbf{x}_j = \mathbf{w}^\intercal\mathbf{x}_j = \sum_iw_ix_{j,i}$$

# Loss & Cost Functions
## Quadratic error
Let $x$ be an input, $y$ the output $y=f(x)$ and $\hat{y}$ the predicted value $\hat{y} = h(x)$.  The *loss* $L(y, \hat{y})$ is the amount of utility lost by doing this prediction. 

For regression, a suitable loss function is quadratic or squared-error loss: $$L_2(y, \hat{y}) = (y-\hat{y})^2$$
## Empirical/Generalization Loss
We would like to consider the set $\varepsilon$ of all possible points, taking into account their probabilities. But instead, we consider just the available dataset $E$ and compute the $Empirical\ Loss$:
$$EmpLoss_E(h) = \frac{1}{|E|}\sum_{(x,y)\in E} L(y, h(x))$$
![[Pasted image 20240316203949.png]]

## Loss function
Notice that the hypothesis h is completely determined by the vector of weights $(w_0, \dots,\ w_n)$. So, given a dataset $E$, the loss is a function of the $n+1$ variables $w_0, \dots,\ w_n$. And the idea is to find the vector of weights $(w^*_0,\dots,\ w^*_n)$ such that the loss is minimum for the given dataset $E$:
$$\mathbf{w^*} =argmin_\mathbf{w}Loss(h_\mathbf{w})$$
![[Pasted image 20240316204440.png]]

## Cost function
When selecting an hypothesis $h$, perhaps minimizing the loss is not the only goal. Perhaps we also want that $h$ be simple, i. e., that its *complexity* be low. So we define the *cost* as the weighted sum of loss and complexity, and minimize cost:
$$
\begin{align}
Cost(h)&=EmpLoss(h) + \lambda Complexity(h) \\
\hat{h}^*&=\mathop{\arg\min}_{h\in H}\ Cost(h) \\ \\
(\lambda > 0)
\end{align}$$
## Regularization
Penalizing complex hypotheses is called *regularization*: We want the solution to be "regular". In this setting we must define two different functions:
- Loss function
- **Regularization function**
For each hypothesis space we must define a suitable regularization function.

For linear functions, complexity is a *function of weights*. The smaller the weights, the more regular the function. Therefore functions are defined in this way:
$$Complexity(h_\mathbf{w}) = L_q(\mathbf{w}) = \sum_i |w_i|^q,\ \ \text{where}\ q \text{ is a natural number.}$$
If some weight $w_i = 0$, we say that the hypothesis is *sparse*; some variables initially considered have been discarded. Sparse hypothesis are usually preferred as they are easier to understand. 

When applying $L_1$ regularization, we frequently obtain a sparse hypothesis. For this reason, $L_1$ regularization is usually used in linear regression.

![[Pasted image 20240316205545.png]]

# Gradient descent
Each $\mathbf{w}$ represents biunivocally a hypothesis. Therefore, choosing a hypothesis and choosing $\mathbf{w}$ is the same thing. We should select the vector of weights that minimizes the empirical loss function, or the cost function, if applying regularization.

A necessary condition for a function $f$ to reach a minimum at a point $x$ is that its partial derivatives in $\mathbf{x}$ (i.e. $w_0,\dots,\ w_n$) be $0$. That is, for every $i$ it must be at $x$ 
$$\frac{\partial L}{\partial w_i} = 0$$
The *gradient* (at $\mathbf{x}$) is the vector whose components are the partial derivatives (at $\mathbf{x}$). The direction of this vector is the one along which $f(x)$ increases most.
![[Pasted image 20240316210123.png]]
The condition $$\frac{\partial L}{\partial w_i} = 0$$ at $\mathbf{x}$ is necessary, but in general not es sufficient for $L$ to reach a global minimum at $\mathbf{x}$ (v.g, it could be just a local minimum, or a maximum). However, if $L$ is *convex*, the condition is also sufficient. The empirical loss function for linear hypotheses is convex.

In the case of linear regression, analytical computations can be carried out and we can obtain a closed formula for the optimal weights $\mathbf{w}^* = (w_0^*,\dots,\ w_n^*)$. But that is not generally the case and we must apply the *Gradient Descent* method. The method can be described as follows:

$\mathbf{w} \leftarrow$ any point in the parameter space.
$\mathbf{while\ not}$ converged $\mathbf{do}$:
	$\mathbf{for\ each\ } w_i \mathbf{\ in\ w\ do}$ 
		$w_i \leftarrow w_i - \alpha\frac{\partial}{\partial w_i} Loss(\mathbf{w})$

The parameter $\alpha > 0$ (the *step size*) is usually called the *learning rate* when we are trying to minimize loss in a learning problem. It can be a fixed constant, or it can decay over time as the learning process proceeds.

![[Pasted image 20240316220317.png]]


Let $$\delta_j = y_j - (w_0x_{j,0} + w_1x_{j,1} + \dots + w_nx_{j,n})$$
Then $$
\begin{align}
\frac{\partial L}{\partial w_i} &= \frac{\partial}{\partial w_i}\frac{1}{N}\sum_j\delta_j^2 \\
&= \frac{1}{N}\sum_j\frac{\partial}{\partial w_i}\delta_j^2 \\
&= \frac{2}{N}\sum_j\delta_j\frac{\partial\delta_j}{\partial w_i} \\
&= -\frac{2}{N}\sum_j\delta_j x_{j,i} \\
&= -\frac{2}{N}\sum_j (y_j - (w_0x_{j,0} + w_1x_{j,1} + \dots + w_nx_{j,n}))x_{j,i}\\
\end{align}$$
So, for linear regression, weights are updated in parallel according to the following rule:
$$w_i \leftarrow w_i + \alpha\frac{1}{N}\sum_{j=1}^{j=N} (y_j - (w_0x_{j,0} + w_1x_{j,1} + \dots + w_nx_{j,n}))x_{j,i}$$
with $\alpha > 0$, where we call now $\alpha$ what would be $-2\alpha$. Note that $x_{j,0} = 1,\ \forall j$.
![[Pasted image 20240316221939.png]]

![[Pasted image 20240316222001.png]]

## Stochastic Gradient Descent (SGD)
This is a very used variant, one of the most famous techniques in Machine Learning. In SGD, we don't use the true gradient, but an estimation: we compute *the gradient of the loss just for one example*. This is computationally less expensive.

For linear regression weights are updated in parallel according to the following rule:
$$w_i \leftarrow w_i + \alpha (y_j - (w_0x_{j,0} + w_1x_{j,1} + \dots + w_nx_{j,n}))x_{j,i},$$
where $j$ is the index of an example in the dataset. For instance, if examples come one at a time, we can use them for updating weights in the same order they are given, “on the fly”. The algorithm is ($\alpha > 0$):
$\mathbf{w} \leftarrow$ any point in the parameter space.
$\mathbf{while\ not}$ converged $\mathbf{do}$:
	select example $j$
	$\mathbf{for\ each\ } w_i \mathbf{\ in\ w\ do\ in\ parallel}$ 
		$w_i \leftarrow w_i + \alpha (y_j - (w_0x_{j,0} + w_1x_{j,1} + \dots + w_nx_{j,n}))x_{j,i}$ 

![[Pasted image 20240316222646.png]]

![[Pasted image 20240316222655.png]]

## Minibatches
Gradient descent by *Minibatch Learning* is an intermediate variant: in each step of computation a *minibatch* of $m$ examples from the total dataset of $N$ examples is selected. Gradient is updated considering errors in the examples of *one* minibatch. The size $m$ of the minibatch is adjusted to take advantage of the computer architecture. The algorithm is ($\alpha > 0$):

$\mathbf{w} \leftarrow$ any point in the parameter space.
Partition the dataset in $K$ minibatches $MB_1,\dots,\ MB_K$ 
$\mathbf{while\ not}$ converged $\mathbf{do}$:
	for every minibatch $MB_k$ $\mathbf{do}$
		$\mathbf{for\ each\ } w_i \mathbf{\ in\ w\ do\ in\ parallel}$ 
			$w_i \leftarrow w_i + \alpha\frac{1}{m}\sum_{j\in MB_k} (y_j - (w_0x_{j,0} + w_1x_{j,1} + \dots + w_nx_{j,n}))x_{j,i}$

Consideration:
- A sequence of steps that covers all the training examples is called an *epoch*.
- Several epochs will be needed to reach convergence.
