# 1. Introduction
Logic provides a formal language (**syntax**) to express knowledge explicitly, which can correspond to states of the world (**semantics**). To deduce implicit knowledge from explicit knowledge, procedures and automated reasoning are offered.

A knowledge base (**KB**) of a logical agent is a set of sentences that includes all the domain knowledge (*rules and facts it perceives*). Boolean logic was the first step of logic and is used in circuits, AI...

# 2. Syntax & Semantics
## 2.1 Syntax
Atomic sentences or atoms are single proposition symbols. Two special ones are True and False.

|  Connectives  |     Meaning     |
| :-----------: | :-------------: |
|    $\neg$     |       NOT       |
|   $\wedge$    |       AND       |
|    $\vee$     |       OR        |
| $\Rightarrow$ |     IMPLIES     |
|    $\iff$     | EQUIVALENCE/IFF |

## 2.2 Semantics
Semantics defines the rules for determining the truth of a sentence. *A model gives a truth value (true or false) for every atomic sentence*. The truth value of the atomic sentences is freely specified by the mode. In Propositional Logic all questions about satisfiability, validity and entailment can be solved by direct enumeration of the $2^n$ models.

##### Truth table
![[Pasted image 20240604103808.png]]

### 2.2.1 Truth of complex sentences
- $\neg P$ is true iff $P$ is false in $m$. 
- $P\wedge Q$ is true iff $P$ and $Q$ are both true in $m$.
- $P\vee Q$ is true iff either $P$ or $Q$ is true in $m$.
- $P\Rightarrow Q$ is true unless $P$ is true and $Q$ is false in $m$.
- $P\iff Q$ is true iff $P$ and $Q$ are both true or both false in $m$.

### 2.2.2 Satisfaction & validity
- If a set of sentences $A$ is true in a model $m$, we say that $m$ **satisfies** $A$.
- A set of sentences $A$ is **satisfiable** if it is true in some model.
- A sentence is **valid** if it is true in all models; then we also say that it is a **tautology**.
- **$A$ is valid (a tautology) iff $\neg A$ is unsatisfiable**.

## 2.3 Entailment
We say that the set $A$ **entails** the sentence $\beta$ iff in every model that satisfies $A$, $\beta$ is also true. If the set $A$ is finite, we also say that the **argument** “A, hence $\beta$ ” is **valid**. We note this as $A\models \beta$. Notice that: $$A\models \beta \iff A \cup \neg B \text{ is unsatisfiable}$$ 
# 3. Automated Reasoning

Suppose $\mathcal{M}$ is a method to answer: *KB entail $\beta$?*. There are two desirables properties for such a method:
1. **Soundness:** If $\mathcal{M}$ answers "yes", then it is true that $\textbf{KB} \models \beta$ 
2. **Completeness:** If it is true that $\textbf{KB} \models \beta$, then $\mathcal{M}$ always answers "yes".

The table method is, however, too computationally complex. Propositional satisfiability is an NP complete problem. However, more efficient or faster algorithms can be found. Possibilities to explore are model checking using: truth tables, DPLL, local search (not complete); or inference, by resolution.

## 3.1 Conjunctive Normal Form (CNF)
The way for the algorithm is to convert the sentences into conjunctive normal form (CNF).

- A **literal** is an atomic proposition $P$ or its negation $\neg P$.
- A **clause** is the disjunction of any number of literals.
- A sentence in **CNF** is the conjunction of one or more clauses. For example, each of the following formulae is in CNF: 
$$
\begin{align}
&\neg R \\
&\neg P \vee Q \\
&R\wedge\neg Q \\
&(\neg P \vee Q) \wedge (R \vee \neg Q)
\end{align}
$$
> **Theorem**
> For every sentence $\alpha$ there is a sentence $\beta$ such that is logically equivalent to $\alpha$ and $\beta$ is in $CNF$. We will note it as $CNF(\alpha)$. 
> 
> To get $CNF(\alpha)$, we replace pieces in $\alpha$ with equivalent formulae.

Strategy:
1. Eliminate every equivalence: $$\alpha \iff \beta \equiv (\alpha\Rightarrow\beta)\wedge(\beta\Rightarrow\alpha)$$
2. Eliminate every implies: $$\alpha\Rightarrow\beta \equiv\neg\alpha\vee\beta$$
3. Simplification:
	1. $\neg\neg\alpha\equiv\alpha$
	2. Morgan's Law:
		1. $\neg(\alpha\vee\beta)\equiv\neg\alpha\wedge\neg\beta$
		2. $\neg(\alpha\wedge\beta)\equiv\neg\alpha\vee\neg\beta$

4. Distribution:
	1. $\alpha\vee(\beta\wedge\delta)\equiv(\alpha\vee\beta)\wedge(\alpha\vee\delta)$ 
	2. $\alpha\wedge(\beta\vee\delta)\equiv(\alpha\wedge\beta)\vee(\alpha\wedge\delta)$ 
 
5. Simplification by repetition or redundancy.

## 3.2 Resolution rule
Two clauses are taken such that it has a literal where one is negated and the rest of the literals are taken, resulting in a new clause. It produces a clause with all the literals of the two original clauses except the two complementary literals. Any repeated copy of a literal is also removed from the resulting clause.
- In the case of having two clauses with the same literals negated in pairs, the result is True.
- On the other hand, a clause of the type $P$ and $\neg P$ results in $\square$. To test the satisfiability of a set of clauses, the resolution method will be applied until:
	- $\square$ appears. Thus, $A$ is unsatisfiable.
	- Cannot be applied anymore. Thus, $A$ is satisfiable.
