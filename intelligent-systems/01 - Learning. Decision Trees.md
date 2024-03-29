# Introduction
## Supervised Learning
An agent is **learning** if after making **observations** about the world it **improves its performance** on **future tasks**.

We call **supervised learning** when the agent observes some examples of input-output pairs and learns a function that maps from input to output.
### Example
![[Pasted image 20240314105800.png]]

![[Pasted image 20240314105835.png]]

---

The agent given a set of N examples of input-output pairs. This set is called **dataset**:
$$(x_1,\ y_1),\ (x_2,\ y_2)\dots (x_N,\ y_N)$$
Each input $x_j$ is a vector $(x_{j,1} , x_{j,2} ,\dots, x_{j,n})$ of $n$ components from an input space.

The values $y_j$ (outputs) are generated by an unknown function $y = f (x)$. Given the dataset, the agent tries to “learn” the function $f$, i. e., hypothesizes a function $h(x)$ that approximates the true function $f$.

### Types of tasks
- **CLASSIFICATION TASK**: When the output belongs to a finite set of values $V= \{v_1,\dots, v_n\}$. We call it like that because every set $C_i = \{x | f(x) = v_i\}$ can be viewed as a *class*. The family of all the sets $C_1, \dots, C_m$ determine a classification in the input space.
	- If the number of classes is $|C| = 2$, we say that it is a **binary classification** problem. Otherwise, it is a **multiclass**.

- **REGRESSION TASK**: When the output belongs to an interval of real numbers.

### Classes & hypotheses
- The estimator function $h$ (*hypothesis*) is chosen from a given set of possible functions (*hypothesis space*).
- Of course, $h$ must approximate well the known values of $f(x)=y$ for the given examples (*dataset*).
- But the true business for $h$ is to *generalize*, i.e. to correctly predict the value of $f(x)$ for novel examples for which this values is not known.
- It is also desired that $h$ be a function as simple as possible. This principle is called *Occam’s razor*.

# Decision Trees & ID3 Algorithm
## Concept
A **decision tree** represents an *hypothesis*, i.e., a function that takes a vector of attribute values $x$ and returns a *decision* $y$ (the output value, also called *goal attribute*).

- Both the inputs (attribute values $x$) and the output (decision $y$) can be either *discrete* or *continuous*. 
- \*We only study the discrete cases, which means that we are solving **classification problems.**

## Structure
A decision tree produces its decision by performing a **sequence of tests**.
- Each *internal node* tests the value of one of the input attributes $A_i$.
- *Branches* from the node are labeled with the possible values of the attribute $v\in Values(A_i)$
- Each *leaf node* specifies a value $y$ to be returned by the function.
- Each *path* leading to a leaf node can be represented as a sentence (rule) of propositional  logic.
### Sample Decision Tree
![[Pasted image 20240314112722.png]]

The tree is to be learned from a dataset, i. e., from a set of $N$ *examples* ${(x_1,\ y_1),\dots,\ (x_N,\ y_N)}$. So, it is a *supervised learning task*.

## ID3 Algorithm
*ID3* has three input arguments: **Examples**, **GoalAttribute** and **Attributes**. ID3 generates and returns a *decision tree*.
### Base cases
- If all the examples have the same goal value, then it returns a single node labeled with that value.
- If *Attributes* is empty, then it returns a single node labeled with the most frequent goal value in *Examples*.

**OTHERWISE**, it finds the attribute $A$ which best classifies *Examples*. The distinguishing feature of ID3 is that it uses *Entropy* and *IG* (information gain) to measure how well $A$ classifies *Examples*.

## Entropy
Entropy $H(Examples)$ is a measure of uncertainty in the set of examples. 
- It gives the number of bits needed to codify the goal values of the examples.
- **Lower entropies** are preferred.
- $H(Examples)=0$ $iff$ all the examples belong to the same class, i.e. *perfect classification*.

$$H(Examples) = - \sum_{v\in GoalAttributes} \frac{|Examples(V)|}{|Examples|}log_2 \ \frac{|Examples(v)|}{|Examples|}$$
If there are just two classes, $H$ is a function of the relative frequency $x$ of, say, Class 1. If all cases belongs to the same class ($x=0$ or $x=1$) then $H=0$ (minimum disorder, minimum uncertainty). On the other hand, if both classes are equally frequent ($x=0.5$) then $H=1$ (maximum disorder, maximum uncertainty).

## Information Gain
*Information gain* $IG(Examples,\ A)$ is the difference in entropy as *Examples* is split on an attribute $A$, i.e. how much uncertainty is reduced in the example set after splitting it on $A$. If we note $T$ the set of all subsets obtained by splittin *Examples* on $A$:
$$IG(Examples,\ A) = H(Examples)- \sum_{t\in T}\frac{|t|}{|Examples|}H(t)$$
The attribute $A$ with the highest $IG$ is chosen. After that, for each $v\in Values(A)$ :
- Add a new branch below the root with label $v$.
- Let $Examples(v)$ be the subset of *Examples* with $A=v$.
- If $Examples(v)$ is empty, then it adds below the branch a leaf node labeled with the most frequent goal value in *Examples*.
- Else, we adds below the branch the new subtree ID3 (*Examples(v)*, *GoalAttribute*, *Attributes-{A}*).

// TODO: INSERT ID3 ALGORITHM TENNIS EXAMPLE

## Drawbacks on ID3 Algorithm
- It carries out a *hill climbing search* over the space of all decision trees, so there is no guarantee of finding the “best” tree (we can get stuck in a local optimum).
- *Inductive bias*: shorter trees are preferred over longer trees (*Occam’s razor principle*).

To solve this problems, *backtracking* can be used to replace hill climbing. Anyways, the problem of constructing optimal binary decision trees is NP-complete, so no feasible algorithm is available.

# Performance Measures
## General classification measures
The **confusion matrix** has size $C\times C$, and its $(i,j)$ element is the number $n_{ij}$  of examples of class $j$ which are predicted to belong to class $i$.
![[Pasted image 20240314121259.png]]
The **accuracy** is the number of correctly predicted examples divided by the number of examples. In term of the confusion matrix, accuracy is given by:
$$Accuracy = \frac{\sum_i n_{ii}}{\sum_i\sum_j n_{ij}}$$
## Binary classification measures
![[Pasted image 20240314121450.png]]
- Class 1 is called the *positive class*, and class 2 is called the *negative class*.
- True positives (TP)= $n_{11}$
- True negatives (TN) = $n_{22}$
- False positives (FP, also called Type I errors) = $n_{12}$
- False negatives (FN, also called Type II errors) = $n_{21}$

- *Accuracy* is the number of correctly predicted examples divided by the number of examples (the **higher the better**).
$$Accuracy = \frac{TP + TN}{TP+TN+FP+FN}$$
- *Precision* is the positive predective value (the **higher the better**).
$$Precision = \frac{TP}{TP+FP}$$
- *Recall* (or *sensitivity*) is the true positive rate (the **higher the better**).
$$Recall = \frac{TP}{TP + FN}$$
- *Fallout* is the false positive rate (the **lower the better**)
$$Fallout = \frac{FP}{TN+FP}$$
- *Specificity* (the **higher the better**)
$$Specificity = 1 - Fallout$$
- *F-measure* integrates precision and recall (the **higher the better**).
$$F-measure = 2\frac{Precision\cdot Recall}{Precision + Recall}$$

### Example
![[Pasted image 20240314122359.png]]

![[Pasted image 20240314122408.png]]

# Training vs. Learning

