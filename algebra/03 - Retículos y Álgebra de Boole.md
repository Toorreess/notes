Los retículos son una estructura algebraica que se caracterizan por ser un tipo particular de conjuntos parcialmente ordenados.
# 1. Retículos
## 1.1 Conjuntos parcialmente ordenados
Una *relación de orden parcial* $\preceq$ en un conjunto $A$ es una relación binaria que verifica las siguientes propiedades, $\forall a,b,c \in A$:
- *Reflexiva*: $a \leq a$
- *Antisimétrica*: $a \leq b$ y $b \leq a \Rightarrow a = b$ 
- *Transitiva*: $a\leq b$ y $b \leq c \Rightarrow a \leq c$

Diremos que el par $(A, \preceq)$ es un *conjunto parcialmente ordenado*.

### 1.1.1 Definición
Dados dos elementos $a$ y $b$ de un conjunto parcialmente ordenado $A$, diremos que $b$ es un *inmediato sucesor* de $a$ o que *$b$ cubre a $a$* si $a \leq b$ y $\nexists c < b\ : a\leq c$.

Una manera de representar un conjunto parcialmente ordenado finito $(A, \preceq)$ es mediante los diagramas de *Hasse*:
1. Dibujamos un vértice por cada elemento de $A$.
2. Dibujamos una arista $a \rightarrow b$ si $b$ es un inmediato sucesor de $a$.
#### Ejemplos
1. Divisores de un número.
2. Partes de un conjunto.
3. Conjunto $\mathbb{B}^n$: Conjunto de palabras de longitud $n$.

## 1.2 Elementos destacables
Sea $(A, \preceq)$ y $B\subseteq A$ :
- *Cota superior (CS)*. Un elemento de $A$ que supera a todos los elementos de $B$. $\forall x\in B, x\preceq c,\ c \in A$ 
- *Supremo de B (sup). La menor cota superior.
- *Máximo*. El mayor elemento de $B$.
- *Elemento maximal*. Un elemento de $B$ no superado por otro de $B$.

- *Cota inferior (CI)*. Un elemento de $A$ que es anterior a todos los elementos de $B$
- *Ínfimo de B (inf)*. La mayor cota inferior.
- *Mínimo*. El menor elemento de $B$.
- *Elemento minimal*. Un elemento de $B$ anterior a todos los elementos de $B$.

## 1.3 Retículos
>¿Podemos definir una operación en un conjunto ordenado?
> $(a,b) \mapsto c$
> Si tenemos $B=\Set{a,b}$, ¿calcular el supremo de B, o el máximo de B, son operaciones?

### 1.3.1 Ejemplo. Calcular algunos supremos en $D_{180}$ 
![[Pasted image 20240318161138.png]]
$\sup\Set{3,5} = 15$
$\sup\Set{6,15} = 30$
$CS = \text{múltiplos comunes = Con 2, 3, 5} = \text{múltiplos de } 30 = \Set{30, 60, 90, 180}$ 
$6 = 2\cdot 3$
$15 = 3\cdot 5$

### 1.3.2 Ejemplo. $A = (\Set{2,4,5,10,12,20}, |)$
![[Pasted image 20240318161815.png]]
$sup\Set{12,20}$ no existe

### 1.3.3 Definición
Un *retículo* es un conjunto ordenado $(L, \preceq)$ tal que para cualesquiera $a,b \in A$ existen supremo e ínfimo:
- $a \vee b = \sup\Set{a,b}$ 
- $a\wedge b = \inf\Set{a,b}$

#### Ejemplos
##### a) $A = \mathcal{P}(\Set{1,2,3,\dots,n})$ con la relación de inclusión $(A, \subseteq)$. ¿Es un retículo?
Sea $X, Y \subseteq \Set{1,2,3,\dots,n}$, con $n=10$, $X=\Set{2,4,6,8}$ y $Y=\Set{3,6,9}$
- Cotas superiores de $\Set{X,Y}$
	- $\Set{1,2,3,\dots,9}$
	- $X\cup Y = \Set{2,3,4,6,8,9} \leftarrow$ esta es la cota superior más pequeña. 
	$\therefore \sup\Set{X,Y} = X\cup Y$ 

$\inf\Set{X,Y} = X\cap Y$

Por tanto, $(A, \subseteq)$ es un retículo.
##### b) $\mathbb{B}^3 = \set{000, 100, 010, 001, 101, 110, 011, 111}$
- Orden: dígito a dígito:
	$100 \leq 101$ porque $0\leq1$
	$100 \nleq 011$ porque $1 \nleq 0$

>El supremo de dos palabras está formado por el supremo de los elementos en cada posición. $\sup\set{x_1x_2x_3, y_1y_2y_3} = z_1z_2z_3$, donde $z_n = \sup\set{x_n, y_n}$
>El ínfimo de dos palabras está formado por el ínfimo de los elementos en cada posición. $\inf\set{x_1x_2x_3, y_1y_2y_3} = z_1z_2z_3$, donde $z_n = \inf\set{x_n, y_n}$.

##### c) $(D_{180}, |)$ con relación de divisibilidad, $a=12, b=9$
$\sup\set{12,9}$
$12 = 2^2 \cdot 3$ y $9 = 3^2$ 
$CS = \set{36,180} \rightarrow$ Múltiplos comunes
$\sup\set{9,12} = mcm(9,12)$

$\inf\set{9,12} = mcd(9,12)$

**Por tanto** $(D_n, |)$ con relación de divisibilidad **es un retículo**. 

Sea $a,b\in D_n$:
- $\sup\set{a,b}=mcm(a,b)$
- $\inf\set{a,b} = MCD(a,b)$

### 1.3.4 Propiedades
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

**TEOREMA**
>Un retículo se puede determinar de dos maneras distintas:
>1. Un conjunto $L$ con una relación de orden $\leq$ tal que para todo par de elementos de $L$, $a$ y $b$, el conjunto $\{a, b\}$ tiene supremo e ínfimo.
>2. Un conjunto $L$ con dos operaciones internas, $\vee$ y $\wedge$, que verifican las propiedades asociativa, conmutativas, idempotentes y de absorción.

## 1.4 Subretículos y homomorfismos

Sea $L$ un retículo. Un subretículo de $L$ es un subconjunto $K$ de $L$ que es cerrado para las operaciones $\vee$ y $\wedge$, es decir, si $a, b \in K$ entonces:
$$a \vee b \in K \ \ \ \ \ \ \ \ \ a \wedge b \in K$$

*Nota 1.* Puede ocurrir que un subconjunto $K$ de un retículo $L$ sea un retículo, pero que no sea un subretículo. Es decir, que todo conjunto $\{a, b\}$ tiene supremo en $K$, pero este supremo no es el supremo de $\{a, b\}$ en $L$.

También tenemos la noción de ideal, similar a la noción de ideal en un anillo (en el que la operación $\wedge$ hace el papel del producto).

### 1.4.1 Definición
Un **ideal** de un retículo $L$ es un subretículo $K$ que verifica $x\in L,\ a\in K \Rightarrow x\wedge a \in K$

### 1.4.2 Definición
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

### 2.2.1 Definición
Un elemento $x$ de un retículo $L$ se dice:
1. $\vee\text{-irreducible}$ si no es el supremo de dos elementos distintos a $x$. Un elemento es $\vee\text{-irreducible}$ si solo tiene un predecesor inmediato.
2. $\wedge\text{-irreducible}$ si no es el ínfimo de dos elementos distintos a $x$. Un elemento es $\wedge\text{-irreducible}$ si solo tiene un sucesor inmediato.

Si $L$ es acotado, entonces $x$ se dice:
1. $\text{átomo}$ si es un inmediato sucesor de $O$. Todo átomo es $\vee\text{-irreducible}$.
2. $\text{superátomo}$ si es un inmediato predecesor de $I$. Todo superátomo es $\wedge\text{-irreducible}$.

El proceso que hemos hecho en el ejemplo anterior se resume en el siguiente teorema.

**TEOREMA**
>Sea $L$ un retículo finito y $x$ un elemento de $L$. Entonces:
>1. Existe una familia de elementos $\vee\text{-irreducible}$, $x_1, \dots , x_n$ tales que $x = x_1 \vee \dots \vee x_n$
>2. Existe una familia de elementos $\wedge\text{-irreducible}$, $y_1, \dots , y_n$ tales que $y = y_1 \wedge \dots \wedge y_n$

Ambas descomposiciones puede tomarse irredundantes, esto es, que verifiquen $x_i \nleq x_j,\ \forall i, j$

## 2.3 Retículos complementados
Un retículo $L$ se denomina complementado si es acotado y todo elemento tiene un complemento.

## 2.4 Retículos distributivos
Un retículo $L$ se denomina distributivo si se cumplen las propiedades distributivas, es decir,
$$a\vee (b\wedge c) = (a\vee b) \wedge (a\vee c)\ \ \ \ \ \ \ \ \ \ \ a\wedge(b\vee c) = (a\wedge b) \vee (a\wedge c)$$

![[Pasted image 20240404161511.png]]

**TEOREMA**
>Un retículo $L$ es distributivo si y solo si no contiene un subretículo isomorfo al diamante o al pentágono.

*Nota 2.* En el retículo $D_{180}$, el subconjunto $L = \{1, 2, 4, 5, 20\}$ tiene forma de pentágono. Sin embargo, no es un subretículo de $D_{180}$.

**TEOREMA**
>Sea $L$ un retículo distributivo:
>1. Se cumplen las propiedades de cancelación:
$$
\begin{align}
\begin{rcases}
x\vee y = x\vee z \\
x\wedge y = x\wedge z
\end{rcases}
\end{align} \Rightarrow y = z$$
>2. Si $x$ tiene dos complementos, $x_1$ y $x_2$, entonces $x_1 = x_2$. Por tanto, todo elemento tiene como mucho un complemento.
>3. La descomposición de un elemento como supremo de elementos $\vee$-irreducibles (resp. $\wedge$-irreducibles) es única.

# 3. Álgebra de Boole
Un **álgebra de Boole** es un retículo *acotado*, *distributivo* y *complementado*.

Sea $A$ un álgebra de Boole. Entonces:
1. Involución: $\bar{\bar{x}} = x$
2. Leyes de Morgan:
	- $\overline{a \vee b} = \bar{a} \wedge \bar{b}$ 
	- $\overline{a\wedge b} = \bar{a}\vee\bar{b}$
3. Si $B$ es otro álgebra de Boole y $f: A \rightarrow B$ es un isomorfismo de retículos entonces $f$ conserva los complementos, esto es, $\overline{f(a)} = f(\bar{a})$ 

**TEOREMA**
>Un álgebra de Boole se puede presentar de dos maneras distintas.
>1. Como un retículo $A$ complementado y distributivo.
>2. Como un conjunto $A$ dotado de dos operaciones $\vee$ y $\wedge$, dos elementos $O$ e $I$, y una aplicación $A \rightarrow A$ de la forma $a  \mapsto \bar{a}$ que verifican:
	1. Asociativas
	2. Distributivas
	3. Conmutativas
	4. Identidades: $a\wedge I = a,\ a\vee O = a$ 
	5. Complemento: $a\wedge \bar{a} = I,\ a\vee \bar{a} = O$ 

## 3.2 Teorema de estructura
**Lema.** En un álgebra de Boole $B$, todo elemento irreducible es un átomo.

**TEOREMA**
>Sea $B$ un álgebra de Boole finita y $x \in B$. Denotemos por $A_x = \{a_1, \dots , a_n\}$ al conjunto de todos los átomos menores iguales que $x$. Entonces:
>1. $x = a_1 \vee a_2 \vee \dots \vee a_n$
>2. Esta representación es única en el sentido de que si $\mathcal{C}$ es otro conjunto de átomos cuyo supremo es $x$, entonces $\mathcal{A}_x = \mathcal{C}$.

La expresión de un elemento $x$ de un álgebra de Boole $B$ dada en el teorema anterior se denomina *Forma normal disyuntiva de x*.

*Corolario*
1. Si el álgebra de Boole $B$ tiene exactamente $n$ átomos, entonces $B$ tiene $2^n$ elementos.
2. Para cualquier $n \in \mathbb{N}$, existe un álgebra de Boole con $2^n$ elementos.
3. Dos álgebras de Boole son isomorfas si y solo si tienen el mismo número de átomos si y solo si tienen el mismo número de elementos.
4. Toda álgebra de Boole con $n$ átomos es isomorfa a $B_n$ y a $\mathcal{P}(\{1, \dots, n\})$.
5. Toda álgebra de Boole con $2^n$ elementos es isomorfa a $B_n$ y a $\mathcal{P}(\{1, \dots, n\}$.

**TEOREMA**
> Sea $B$ un álgebra de Boole finita y $x \in B$. Denotemos por $\mathcal{B}_x = {b_1,\dots, b_n}$ al conjunto de todos los superátomos mayores o iguales que $x$. Entonces:
> 1. $x = b_1 \wedge b_2 \wedge \dots \wedge b_n$ 
> 2. Esta representación es única en el sentido de que si $\mathcal{C}$ es otro conjunto de coátomos cuyo ínfimo es $x$, entonces $\mathcal{B}_x = \mathcal{C}$.

La expresión de un elemento $x$ de un álgebra de Boole $B$ dada en el teorema anterior se denomina *Forma normal conjuntiva de x*.

# 4. Funciones booleanas
## 4.1 Funciones booleanas
Una *función booleana* de $n$ variables es una aplicación $f: \mathbb{B}^n \rightarrow \mathbb{B}$.

![[Pasted image 20240408154908.png]]
En las funciones booleanas: 
- $+ = \vee$: supremo
- $\cdot = \wedge$: ínfimo
### Ejemplo
- Supremo: $f_2 \vee f_8 = f_{10}$
	$CS\set{f_2, f_8} = \set{f_{15}, f_{14}, f_{11}, f_{10}}$
- Ínfimo: $f_{10} \wedge f_4 = f_0$ 
	$CI\set{f_{10}, f_4} = \set{f_0}$
- Máximo: $f_{15}$
- Mínimo: $f_0$

Sea $\mathcal{F}_n$ el conjunto de las funciones booleanas de $n$ variables, queremos analizar si dicho conjunto es álgebra de Boole. Para ello, se debe dar el siguiente Teorema:

**TEOREMA**
> En el conjunto $\mathcal{F}_n$ de las funciones booleanas de $n$ variables definimos la *relación de orden*
> $$f \leq g \iff f(x_1,\dots,x_n) \leq f(x_1,\dots,x_n)\  \forall \ (x_1,\dots,x_n) \in \mathbb{B}^n$$
> Entonces $\mathcal{F}_n$ es un retículo complementado y distributivo y, por tanto, un álgebra de Boole. Además:
> 1. $(f \vee g)(x_1,\dots,x_n) = f(x_1,\dots,x_n) \vee g(x_1,\dots,x_n)$.
> 2. $(f \wedge g)(x_1,\dots,x_n) = f(x_1,\dots,x_n) \wedge g(x_1,\dots,x_n)$.
> 3. $(\overline{f})(x_1,\dots,x_n) = \overline{f(x_1,\dots,x_n)}$.
> 4. $f$ es un átomo si y solo si existe un elemento $x \in \mathbb{B}^n$ tal que $f(x) = 1$ y $f(y) = 0$ si $y \neq x$.
> 5. f es un coátomo si y solo si existe un elemento $x \in \mathbb{B}^n$ tal que $f(x) = 0$ y $f(y)=1$ si $y \neq x$.
> 6. $\mathcal{F}_n$ tiene $2^n$ átomos y $2^{2^n}$ elementos.

## 4.2 Expresiones booleanas
Se aborda el problema de si es posible expresar una función booleana mediante una fórmula.

Una expresión booleana con $n$ variables, $x_1, \dots, x_n$, es una expresión que se construye del siguiente modo:
1. Las expresiones simples son las que están formadas por una variable o por una constante $0, 1$.
2. Si $e$ es una expresión booleana, entonces $\overline{e}$ es una expresión booleana.
3. Si $e_1$ y $e_2$ son expresiones booleanas, entonces $e_1 + e_2$ y $e_1 \cdot e_2$ son expresiones booleanas.

Dos expresiones booleanas son equivalentes si definen la misma función booleana.

*Proposición*
1. Un átomo está dado por una expresión en la que aparecen todas las variables (o los complementos de ellas) multiplicando. Es decir, son de la forma $e_1 \cdot e_2 \dots e_n$, donde $e_i$ es $xi$ o $\overline{x_i}$. Esta expresiones se llaman **minitérminos**.
2. Un átomo está dado por una expresión en la que aparecen todas las variables (o los complementos de ellas) sumando. Es decir, son de la forma $e_1 + e_2 \dots +e_n$, donde $e_i$ es $xi$ o $\overline{x_i}$. Esta expresiones se llaman **maxitérminos**.