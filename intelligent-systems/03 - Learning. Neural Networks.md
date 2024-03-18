# Linear Classification: Threshold & Logistic Units
## Definition of the problem
![[Pasted image 20240316224258.png]]

It looks entirely analogous to the figure shown for Linear Regression, but now both horizontal and vertical axis represent the **input x**. The output $y$ is given by the color of each point $x=(x_1,x_2)$. In this case it is green for a class (class 0) and orange for another class (class 1).

If we don’t use colors, we need another geometrical dimension to represent the binary output $y$:
![[Pasted image 20240316224506.png]]

A *dataset* is a finite sequence $\{(x_1,y_1),\dots,\ (x_N, y_N)\}$ where:
- Each $\mathbf{x}_j$ is a vector $(x_{j,1},\ \dots,\ x_{j,n})$ of $n$ real components (*inputs*).
- **Each $y_j$ is either $0$ or $1$**.

In the example, $N = 54$ and $n = 2$. For instance, $x_3 = (x_{3,1}, x_{3,2}) = (4.8, 4.5),\ y_3 = 1$

### Definition of the problem: Linearity
We will consider only linear binary classification with two classes $C_0,\ C_1$. Class is then represented by a goal attribute $y$ with two posible values: 0 and 1. A plane (or line) $R$ in $n$ dimensions is defined by the equation:

$$w_0 + w_1x_1 + \dots + w_nx_n = 0$$
The plane partitions the space in *two half-spaces*, $P_0,\ P_1$ that can be identified with the two classes $C_0,\ C_1$: 
$$
\begin{align}
	P_0 &= \{\mathbf{x}\ |\ w_0 + w_1x_1 + \dots + w_nx_n < 0\} \\
	P_1 &= \{\mathbf{x}\ |\ w_0 + w_1x_1 + \dots + w_nx_n \geq 0\}
\end{align}$$

### Definition of the problem: Threshold Hypotheses
Since $y=0$ for $x\in C_0$ and $y=1$ for $x\in C_1$
$$
h(\mathbf{x}) = 
\begin{cases}
0 \text{ if } w_0 + w_1x_1 + \dots + w_nx_n < 0 \\
1 \text{ if } w_0 + w_1x_1 + \dots + w_nx_n \geq 0 \\
\end{cases}
$$
Let $z = w_0 + w_1x_1 + \dots + w_nx_n$ and a function (notice that $Threshold(z)$ is discontinuous at $z=0$):
$$
Threshold(z) = 
\begin{cases}
0 \text{ if } z<0 \\
1 \text{ if } z\geq 0
\end{cases}
$$

Then a hypothesis is $h(\mathbf{x}) = Threshold(z)$.
![[Pasted image 20240316230244.png]]

## Threshold Hypotheses
$\mathbf{select }$ $j$ and an example $(\mathbf{x}, y)$ in the dataset.
$\mathbf{update }$ weights in parallel according to the following rule:
$\mathbf{if }$ $y=f(x)$, $\mathbf{ then}$ the weights are not changed
$\mathbf{if }$ $y=1$ and $h(\mathbf{x}) = 0$, $\mathbf{then}$ 
	$\mathbf{for }$ every $i$, $0\leq i \leq n$ $\mathbf{ do}$ 
		$w_i \leftarrow w_i + \alpha x_i$ 
$\mathbf{if }$ $y=0$ and $h(\mathbf{x}) = 1$, $\mathbf{then}$ 
	$\mathbf{for }$ every $i$, $0\leq i \leq n$ $\mathbf{ do}$ 
		$w_i \leftarrow w_i - \alpha x_i$ 


There are limitations of threshold units:
- Even if convergence in the limit is guaranteed, learning with the perceptron rule a very unpredictable adventure, especially for nonseparable problems, i. e., problems for which a perfect linear classifier does not exists.
- Furthermore, the linear classifier always announces a completely confident prediction of 1 or 0, even for examples that are very close to the boundary; it would be better if it could classify some examples as a clear 0 or 1, and others as unclear borderline cases.
- For this reasons, threshold classifiers are not used. But they are important from a historical point of view, and very easy for doing computations by hand.

## Logistic Hypotheses
More interesting hypothesis space.
- The image of $h(x)$ is $[0,1]$ and gives the probability of $x \in C_1$.
- $h(x)$ is continuous and differentiable everywhere.
- $h(x)$ is defined from a linear combination of $x$:
	- first define $z = w_0 + w_1x_1 + \dots + w_nx_n$
	- then apply a certain function $f$ to $z$
	- $h(x) = f(z) = f(w_0 + w_1x_1 + \dots + w_nx_n)$

The function $f$ will be the $Logistic$ function:
$$Logistic(z) = \frac{1}{1 + e^{-z}}$$
![[Pasted image 20240316233210.png]]


The process of fitting the weights of this model is called *logistic regression* **It is a classification task. The name can be misleading**.

The most satisfactory answer to which magnitude must be optimized by the weights is the *likelihood of the hypothesis given the dataset*. **It is also called cross-entropy, but no real relation with entropy**.

There is no easy closed-form solution to find the optimal weights, but the GD and SGD can be used. **R&N maximizes quadratic loss also here**.

### Maximum likelihood
The likelihood $V$ of a hypothesis $h_w$, given a dataset $\{(x_j, y_j)\}$, is the probability of the hypothesis to generate those data.

For one example in the dataset,
$$
\begin{align}
\mathsf{Pr}(Y=1|\mathbf{x}) &= h_w(\mathbf{x}) \\
\mathsf{Pr}(Y=0|\mathbf{x}) &= 1- h_w(\mathbf{x}) \\
\mathsf{Pr}(Y=y_j|\mathbf{x}_j) &= h_w(\mathbf{x})^{y_j}(1-h_w(\mathbf{x}))^{1-y_j}
\end{align}
$$
and for the whole dataset
$$V = \prod_{j=1}^{j=N}h_w(\mathbf{x})^{y_j}(1-h_w(\mathbf{x}))^{1-y_j}$$
Taking logarithms, the place of extreme values don’t change, so we consider instead
$$\log V = \sum_{j=1}^{j=N} y_j\log h_w(\mathbf{x}_j)+(1-y_j)\log(1-h_w(\mathbf{x}))$$
Changing the sign (we will minimize) and normalizing by the size of the data set, we get of loss function:
$$\mathcal{L} = -\frac{1}{N}\sum_{j=1}^{j=N} y_j\log h_w(\mathbf{x}_j)+(1-y_j)\log(1-h_w(\mathbf{x}))$$
whose partial derivatives are
$$\frac{\partial\mathcal{L}}{w_i} = -\frac{1}{N}\sum_{j=1}^{j=N}(y_j-h_w(\mathbf{x}_j))x_{j,i}$$

![[Pasted image 20240316234653.png]]

![[Pasted image 20240316234813.png]]


![[Pasted image 20240316234827.png]]


![[Pasted image 20240316234847.png]]

# Networks of Units
![[Pasted image 20240317191921.png]]
$g$ is the *activation function*, and these elements are *units* (or *neurons*).

If we connect these units in a way that the output of one is the input for another, we get a *Neural Network*. Some inputs are "real inputs" to the network ($x_1,\ x_2$), and the output of one unit is the "real output" of the network ($\hat{y}$).
![[Pasted image 20240317192028.png]]
## Feedforward networks
A *feedforward network* has connections only in one direction. It forms a directed acyclic graph with designated input and *output* nodes. 

Non output nodes nodes are *hidden nodes* organized in layers. Each node computes a function of its inputs and passes the result to its successors in the network. Information flows through the network from the input nodes to the output nodes, and there are no loops.

There are many possible topologies for a feedforward Neural Network: shallow/deep; fully connected layers/convolution layers...

![[Pasted image 20240318121455.png]]

### Example
This is a feedforward ully connected neural network with 2 inputs and one hidden layer with 2 logistic units (weights $w_{03},w_{0,4},w_{0,5}$ are not shown).

![[Pasted image 20240318121611.png]]


![[Pasted image 20240318121830.png]]
