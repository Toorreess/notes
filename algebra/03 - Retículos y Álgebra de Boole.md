Los retículos son una estructura algebraica que se caracterizan por ser un tipo particular de conjuntos parcialmente ordenados.
# Retículos
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
