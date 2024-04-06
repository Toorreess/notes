Los retículos son una estructura algebraica que se caracterizan por ser un tipo particular de conjuntos parcialmente ordenados.
# 1. Retículos
## Conjuntos parcialmente ordenados
### Definición
Una *relación de orden parcial* $\preceq$ en un conjunto $A$ es una relación binaria que verifica las siguientes propiedades, $\forall a,b,c \in A$:
- *Reflexiva*: $a \leq a$
- *Antisimétrica*: $a \leq b$ y $b \leq a \Rightarrow a = b$ 
- *Transitiva*: $a\leq b$ y $b \leq c \Rightarrow a \leq c$

Diremos que el par $(A, \preceq)$ es un *conjunto parcialmente ordenado*.

### Definición
Dados dos elementos $a$ y $b$ de un conjunto parcialmente ordenado $A$, diremos que $b$ es un *inmediato sucesor* de $a$ o que *$b$ cubre a $a$* si $a \leq b$ y $\nexists c < b\ : a\leq c$.

Una manera de representar un conjunto parcialmente ordenado finito $(A, \preceq)$ es mediante los diagramas de *Hasse*:
1. Dibujamos un vértice por cada elemento de $A$.
2. Dibujamos una arista $a \rightarrow b$ si $b$ es un inmediato sucesor de $a$.
### Ejemplos
1. Divisores de un número.
2. Partes de un conjunto.
3. Conjunto $\mathbb{B}^n$: Conjunto de palabras de longitud $n$.

## Elementos destacables
Sea $(A, \preceq)$ y $B\subseteq A$ :
- *Cota superior (CS)*. Un elemento de $A$ que supera a todos los elementos de $B$. $\forall x\in B, x\preceq c,\ c \in A$ 
- *Supremo de B (sup). La menor cota superior.
- *Máximo*. El mayor elemento de $B$.
- *Elemento maximal*. Un elemento de $B$ no superado por otro de $B$.

- *Cota inferior (CI)*. Un elemento de $A$ que es anterior a todos los elementos de $B$
- *Ínfimo de B (inf)*. La mayor cota inferior.
- *Mínimo*. El menor elemento de $B$.
- *Elemento minimal*. Un elemento de $B$ anterior a todos los elementos de $B$.

## Retículos
>¿Podemos definir una operación en un conjunto ordenado?
> $(a,b) \mapsto c$
> Si tenemos $B=\Set{a,b}$, ¿calcular el supremo de B, o el máximo de B, son operaciones?

### Ejemplo. Calcular algunos supremos en $D_{180}$ 
![[Pasted image 20240318161138.png]]
$\sup\Set{3,5} = 15$
$\sup\Set{6,15} = 30$
$CS = \text{múltiplos comunes = Con 2, 3, 5} = \text{múltiplos de } 30 = \Set{30, 60, 90, 180}$ 
$6 = 2\cdot 3$
$15 = 3\cdot 5$

### Ejemplo. $A = (\Set{2,4,5,10,12,20}, |)$
![[Pasted image 20240318161815.png]]
$sup\Set{12,20}$ no existe

### Definición
Un *retículo* es un conjunto ordenado $(L, \preceq)$ tal que para cualesquiera $a,b \in A$ existen supremo e ínfimo:
- $a \vee b = \sup\Set{a,b}$ 
- $a\wedge b = \inf\Set{a,b}$

### Ejemplos
#### a) $A = \mathcal{P}(\Set{1,2,3,\dots,n})$ con la relación de inclusión $(A, \subseteq)$. ¿Es un retículo?
Sea $X, Y \subseteq \Set{1,2,3,\dots,n}$, con $n=10$, $X=\Set{2,4,6,8}$ y $Y=\Set{3,6,9}$
- Cotas superiores de $\Set{X,Y}$
	- $\Set{1,2,3,\dots,9}$
	- $X\cup Y = \Set{2,3,4,6,8,9} \leftarrow$ esta es la cota superior más pequeña. 
	$\therefore \sup\Set{X,Y} = X\cup Y$ 

$\inf\Set{X,Y} = X\cap Y$

Por tanto, $(A, \subseteq)$ es un retículo.
#### b) $\mathbb{B}^3 = \set{000, 100, 010, 001, 101, 110, 011, 111}$
- Orden: dígito a dígito:
	$100 \leq 101$ porque $0\leq1$
	$100 \nleq 011$ porque $1 \nleq 0$

>El supremo de dos palabras está formado por el supremo de los elementos en cada posición. $\sup\set{x_1x_2x_3, y_1y_2y_3} = z_1z_2z_3$, donde $z_n = \sup\set{x_n, y_n}$
>El ínfimo de dos palabras está formado por el ínfimo de los elementos en cada posición. $\inf\set{x_1x_2x_3, y_1y_2y_3} = z_1z_2z_3$, donde $z_n = \inf\set{x_n, y_n}$.

#### c) $(D_{180}, |)$ con relación de divisibilidad, $a=12, b=9$
$\sup\set{12,9}$
$12 = 2^2 \cdot 3$ y $9 = 3^2$ 
$CS = \set{36,180} \rightarrow$ Múltiplos comunes
$\sup\set{9,12} = mcm(9,12)$

$\inf\set{9,12} = mcd(9,12)$

**Por tanto** $(D_n, |)$ con relación de divisibilidad **es un retículo**. 

Sea $a,b\in D_n$:
- $\sup\set{a,b}=mcm(a,b)$
- $\inf\set{a,b} = MCD(a,b)$

### Propiedades
Sea $(L, \preceq)$ un retículo. Entonces se cumplen las siguientes propiedades:
1. *Asociativa*. $(a\vee b) \vee c = a \vee (b\vee c)$ y $(a\wedge b) \wedge c = a \wedge (b\wedge c)$
2. *Conmutativa*. $a\vee b = b\vee a$ y $a \wedge b = b \wedge a$
3. *Idempotentes*. $a\vee a = a$ y $a\wedge a =a$
4. *Absorción*. $a \vee (a\wedge b) = a$ y $a \wedge (a\vee b) = a$
5. *Operación interna*.

> Si me dan $(A, \square,\triangle)$ que cumplen todas las propiedades, ¿es esto un retículo?
> ¿Existe en $A$ un orden que origine estas operaciones? 
> 
> ¿Cómo definimos $\leq$ a partir de $\square$ y $\triangle$?
### Ejemplo $(\mathcal{P}(x), \cup, \cap)$
Si $A\subseteq B$ qué ocurre con
- $A\cup B = B$
- $A\cap B = A$

Entonces, $a\leq b \iff a\square b = b$ y $a\triangle b = a$ 

### Teorema
Un retículo se puede determinar de dos maneras distintas:
1. Un conjunto $L$ con una relación de orden $\leq$ tal que para todo par de elementos de $L$, $a$ y $b$, el conjunto $\{a, b\}$ tiene supremo e ínfimo.
2. Un conjunto $L$ con dos operaciones internas, $\vee$ y $\wedge$, que verifican las propiedades asociativa, conmutativas, idempotentes y de absorción.

## Subretículos y homomorfismos

Sea $L$ un retículo. Un subretículo de $L$ es un subconjunto $K$ de $L$ que es cerrado para las operaciones $\vee$ y $\wedge$, es decir, si $a, b \in K$ entonces:
$$a \vee b \in K \ \ \ \ \ \ \ \ \ a \wedge b \in K$$

*Nota 1.* Puede ocurrir que un subconjunto $K$ de un retículo $L$ sea un retículo, pero que no sea un subretículo. Es decir, que todo conjunto $\{a, b\}$ tiene supremo en $K$, pero este supremo no es el supremo de $\{a, b\}$ en $L$.

También tenemos la noción de ideal, similar a la noción de ideal en un anillo (en el que la operación $\wedge$ hace el papel del producto).

### Definición
Un **ideal** de un retículo $L$ es un subretículo $K$ que verifica $x\in L,\ a\in K \Rightarrow x\wedge a \in K$

### Definición
Sea $f: L_1 \rightarrow L_2$ una aplicación entre dos retículos. Diremos que $f$
1. es compatible con el orden si $a\leq b \Rightarrow f(a) \leq f(b)$ 
2. es $\vee$-homomorfismo si $f(a\vee b) = f(a) \vee f(b)$
3. es $\wedge$-homomorfismo si $f(a\wedge b) = f(a) \wedge f(b)$
4. Es homomorfismo de retículos si es $\vee$-homomorfismo y $\wedge$-homomorfismo.

# 2. Tipos de retículos
## 2.1 Retículos acotados
Un *retículo* $L$ se dice *acotado* si tiene máximo y mínimo. Usualmente, el máximo se denota por $I$ y el mínimo por $O$.

## 2.2 Retículos finitos
![[Pasted image 20240318161138.png]] 

### Definición 8
Un elemento $x$ de un retículo $L$ se dice:
1. $\vee\text{-irreducible}$ si no es el supremo de dos elementos distintos a $x$. Un elemento es $\vee\text{-irreducible}$ si solo tiene un predecesor inmediato.
2. $\wedge\text{-irreducible}$ si no es el ínfimo de dos elementos distintos a $x$. Un elemento es $\wedge\text{-irreducible}$ si solo tiene un sucesor inmediato.

Si $L$ es acotado, entonces $x$ se dice:
1. $\text{átomo}$ si es un inmediato sucesor de $O$. Todo átomo es $\vee\text{-irreducible}$.
2. $\text{superátomo}$ si es un inmediato predecesor de $I$. Todo superátomo es $\wedge\text{-irreducible}$.

El proceso que hemos hecho en el ejemplo anterior se resume en el siguiente teorema.

### Teorema 2. 
Sea $L$ un retículo finito y $x$ un elemento de $L$. Entonces:
1. Existe una familia de elementos $\vee\text{-irreducible}$, $x_1, \dots , x_n$ tales que $x = x_1 \vee \dots \vee x_n$
2. Existe una familia de elementos $\wedge\text{-irreducible}$, $y_1, \dots , y_n$ tales que $y = y_1 \wedge \dots \wedge y_n$

Ambas descomposiciones puede tomarse irredundantes, esto es, que verifiquen $x_i \nleq x_j,\ \forall i, j$

## 2.3 Retículos complementados
Un retículo $L$ se denomina complementado si es acotado y todo elemento tiene un complemento.

## 2.4 Retículos distributivos
Un retículo $L$ se denomina distributivo si se cumplen las propiedades distributivas, es decir,
$$a\vee (b\wedge c) = (a\vee b) \wedge (a\vee c)\ \ \ \ \ \ \ \ \ \ \ a\wedge(b\vee c) = (a\wedge b) \vee (a\wedge c)$$

![[Pasted image 20240404161511.png]]

### Teorema 4
Un retículo $L$ es distributivo si y solo si no contiene un subretículo isomorfo al diamante o al pentágono.

*Nota 2.* En el retículo $D_{180}$, el subconjunto $L = \{1, 2, 4, 5, 20\}$ tiene forma de pentágono. Sin embargo, no es un subretículo de $D_{180}$.

### Teorema 5
Sea $L$ un retículo distributivo:
1. Se cumplen las propiedades de cancelación:
$$
\begin{align}
\begin{rcases}
x\vee y = x\vee z \\
x\wedge y = x\wedge z
\end{rcases}
\end{align} \Rightarrow y = z$$

2. Si $x$ tiene dos complementos, $x_1$ y $x_2$, entonces $x_1 = x_2$. Por tanto, todo elemento tiene como mucho un complemento.

3. La descomposición de un elemento como supremo de elementos $\vee$-irreducibles (resp. $\wedge$-irreducibles) es única.

# 3. Álgebra de Boole
Un **álgebra de Boole** es un retículo *acotado*, *distributivo* y *complementado*.

Sea $A$ un álgebra de Boole. Entonces:
1. Involución: $\bar{\bar{x}} = x$
2. Leyes de Morgan:
	- $\overline{a \vee b} = \bar{a} \wedge \bar{b}$ 
	- $\overline{a\wedge b} = \bar{a}\vee\bar{b}$
3. Si $B$ es otro álgebra de Boole y $f: A \rightarrow B$ es un isomorfismo de retículos entonces $f$ conserva los complementos, esto es, $\overline{f(a)} = f(\bar{a})$ 

### Teorema 5
Un álgebra de Boole se puede presentar de dos maneras distintas.
1. Como un retículo $A$ complementado y distributivo.
2. Como un conjunto $A$ dotado de dos operaciones $\vee$ y $\wedge$, dos elementos $O$ e $I$, y una aplicación $A \rightarrow A$ de la forma $a  \mapsto \bar{a}$ que verifican:
	1. Asociativas
	2. Distributivas
	3. Conmutativas
	4. Identidades: $a\wedge I = a,\ a\vee O = a$ 
	5. Complemento: $a\wedge \bar{a} = I,\ a\vee \bar{a} = O$ 

## 3.2 Teorema de estructura
En un álgebra de Boole $B$, todo elemento irreducible es un átomo.