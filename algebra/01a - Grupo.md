## Grupo
### Definición
Un **grupo** es una [[01 - Grupos, anillos y cuerpos. Introducción|estructura algebraica]] con una operación, que cumple las propiedades de:
- **Asociatividad**
- **Elemento neutro**
- **Elemento inverso**

Si el grupo cumple, además, la propiedad **conmutativa**, el grupo es **abeliano**.

### TEOREMA
> Dado un grupo $(G, \star)$:
> 1. El elemento neutro es único.
> 2. El inverso de cada elemento es único.
> 3. Se cumplen las propiedades cancelativas: $\forall a,b,c \in G:$ 
$$
\begin{align}
a \star b = a \star c \Rightarrow b = c \\
b\star a = c \star a \Rightarrow b = c
\end{align}
$$
> 4. $\forall a,b \in G, (a \star b)^{-1} = b^{-1} \star \ a^{-1}$ 
### Potencias
Sea $(G,\star)$ un grupo y $g \in G$. Definimos las potencias de $G$ del siguiente modo:
1. $g^0 = e$
2. $g^{n+1} = g^n \star g, \forall n \in \mathbb{N}$ 
3. $g^{-n} \rightarrow \text{inverso de } g^n$
## Estructuras de grupo en $\mathbb{Z}_n$
$$\mathbb{Z}_n = \{0, 1, \dots, n-1\}, \ n \in \mathbb{N}$$
#### Ejemplo
En $\mathbb{Z}_5 = \{0, 1, 2, 3, 4\}$
- $[3] + [4] = [7] = [2]$
- $[3] \cdot [4] = [12] = [2]$

En $\mathbb{Z}_n$ estamos identificando números entero $[x] = [y]$ si $x$ e $y$ tienen el mismo resto al dividirlos entre $n$.

#### Corolario
- $(\mathbb{Z}_n,\ +)$ es un grupo abeliano.
- $(\mathbb{Z}^*_n,\ \cdot)$ es grupo abeliano si **n es primo**. 

## Grupo simétrico $(S_n, \circ)$
### Definición
El grupo simétrico de orden $n$, $S_n$ está formado por todas las posibles ordenaciones (permutaciones) de $\mathbb{N}_n$. Una ordenación es una aplicación biyectiva. Es decir:

$$S_n = \{f: \mathbb{N}_n \rightarrow \mathbb{N}_n \ | \ f \text{ es biyectiva} \}$$
La interpretación de las permutaciones como funciones permite definir
una operación en $\mathbb{N}_n$: la composición. El par $(S_n, \circ)$ es un grupo, el *grupo*
*simétrico de orden $n$*.

> **Función inyectiva**: 2 elementos no tienen la misma imagen.
> **Función sobreyectiva**: Todos los elementos del conjunto final es imagen

#### Ejemplo. $n = 3$, $\mathbb{N}_3 = \{1, 2, 3\}$

![[Pasted image 20240225215047.png]]


### Composición de funciones
$\forall \alpha, \beta \in S_n,\ \text{podemos definir la composición de funciones } \alpha \circ \beta$.
Si ambas funciones son biyectivas, el resultado de la composición también lo será.
#### Ejemplo. Si $\alpha = [3, 1, 2]$ y $\beta = [3, 2, 1]$
$$\alpha \circ \beta = [2,1,3] $$
$$
\begin{align}
(\alpha \circ \beta)\ (1) = \alpha(\beta(1)) = \alpha(3) = 2 \\
(\alpha \circ \beta)\ (2) = \alpha(\beta(2)) = \alpha(2) = 1\\
(\alpha \circ \beta)\ (3) = \alpha(\beta(3)) = \alpha(1) = 3
\end{align}
$$

### TEOREMA
> $(S_n, \circ)$ es un grupo no necesariamente conmutativo

### Aplicación identidad

![[Pasted image 20240225220453.png]]

### Aplicación inversa: $\alpha^{-1} \circ \alpha$

![[Pasted image 20240225220747.png]]

## Subgrupos
### Teorema
> Sea $(G, \star)$ un grupo y $H \subseteq G$. $(H, \star)$ es subgrupo de $(G, \star)$ si:
> 1. Es cerrado para la operación $\star$: $a, b \in H \Rightarrow a \star b \in H$
> 2. Es cerrado para inversos: $a \in H \Rightarrow a^{-1} \in H$

⚠️ Si $H$ es finito, $(H, \star)$ es subgrupo $\Leftrightarrow$ verifica que es cerrado para $\star$
⚠️ Si $(G, \star)$ es abeliano, $(H, \star)$ también lo será.


## Grupos cíclicos
#### Reflexión
1. Toma una permutación $\alpha$ de S4.
2. Calcula todas potencias de $\alpha$. El conjunto formado por tales potencias, ¿es un subgrupo de S4? ¿Por qué?

![[Pasted image 20240226154339.png]]
Es subgrupo ya que tiene elemento neutro y es cerrado para la operación.

### Definición: Subgrupo generado por a
Sea $G$ un grupo y $a \in G$. Se denomina **subgrupo generado por $a$** al conjunto formado por todas las potencias de $a$. Denotaremos dicho subgrupo por $\langle a \rangle$.
$$\langle a \rangle = \{a | z \in \mathbb{Z}\}$$

### Definición: Grupo cíclico
Un grupo $G$ es **cíclico** si contiene un elemento $a$ tal que $\langle a \rangle = G$. El elemento $a$ se llama *generador* de $G$.

### Definición: Orden de a
Si $G$ es un grupo y $a\in G$, el menor natural $n$ tal que $a^n=e$ se llama **orden de a** y se denota por $o(a)$
1. $o(a)$ coincide con el número de elementos de $\langle a \rangle$.
2. $a^m = a^r$ donde $r$ es el resto de dividir $m$ entre $n$.

### TEOREMA
> 1. El menor de los n ́umeros naturales no nulo tales que $a^n = e$ coincide con el número de elementos del subgrupo $\langle a \rangle$ generado por $a$.
> 2. Si $a^k=e$ entonces $o(a)$ es un divisor de $k$.

## Productos de grupos
$$\mathbb{Z}_2^5 = \mathbb{Z}_2 \times \mathbb{Z}_2 \times \mathbb{Z}_2 \times \mathbb{Z}_2 \times \mathbb{Z}_2 = \{ (x_1, x_2, x_3, x_4, x_5)\ |\ x_i \in \mathbb{Z}_2\}$$
El producto de grupos es una forma de representar conjuntos de bits. Se utiliza el producto cartesiano.

#### Proposición. Dados $(G, \star)$ y $(H, \square)$, definimos $G \times H$ la operación $\circ$ del siguiente modo:
$$(g_1, h_1) \circ (g_2, h_2) = (g_1 \star g_2,\ h_1 \square h_2)$$
Entonces $(G \times H, \circ$) tiene estructura de grupo.

## Homomorfismos de grupo
### Definición
¿Cómo podemos comparar 2 grupos $(G, \star)$ y $(H, \oplus)$?

Con aplicaciones $f: G \rightarrow H$ que respete las dos operaciones de grupo:
$$a, b \in G,\ f(a\star b) = f(a) \oplus f(b)$$
#### Ejemplo:
![[Pasted image 20240226161833.png]]
![[Pasted image 20240226162125.png]]

### TEOREMA
> 1. Si $e$ es la unidad de $G$, entonces $f(e)$ es la unidad de $H$.
> 2. Dado $a \in G, \ f(a)^{-1} = f(a^{-1})$ 
> 3. Dado $a \in G,\ n \in \mathbb{Z},\ f(a^n) = f(a)^n$
> 4. Si $S$ es subgrupo de $G$, $f(S)=\{f(s)\ |\ s\in S\}$ es un subgrupo de H. En particular, $f(G)$ es un subgrupo de $H$, llamado *imagen de $f$*.

### Definición: Isomorfismo
Un **isomorfismo de grupo** es un *homomorfismo de grupo* que es **biyectivo**. 

Los grupos isomorfos son, como estructura de grupo, esencialmente el mismo. Es decir, tienen las mimas propiedades de grupo.

## Clases laterales. Teorema de Lagrange.
### Definición. Clases laterales
Sea $(G, \star)$, $H$ un subgrupo de $G$ y $a$ un elemento de $G$. Se llama **clase lateral a izquierda de $H$ respecto de $a$** al conjunto $$a \star H = \{a \star h\ |\ h \in H\}$$
La **clase lateral a derecha de $h$ respecto de $a$** es el conjunto $$H \star a = \{h \star a\ |\ h \in H$$
### TEOREMA
1. La única clase lateral que es un subgrupo es $e\star H$. Además, $[e] + H = H$.
2. Todas las clases laterales a izquierda tienen el mismo número de elementos que H.
3. La familia de clases laterales a izq respecto de $H$ son una partición de $G$, es decir, si estas clases laterales son $H_1, \dots, \ H_n$, entonces:
	1. $H_i \cap H_j = \emptyset$    
	2. $G = \bigcup_{i=1}^n H_i$ 
4. Si $[b] \in [a] + H \Rightarrow [b] + H = [a] + H$  

### Teorema de Lagrange.
Sea $G$ un grupo y $H$ un subgrupo de $G$. Entonces el orden de $H$ es un divisor del orden de $G$.

#### Ejemplo: ¿Es $\{[0], [1], [2], [3], [4]\}$ subgrupo de $\mathbb{Z}_{12}$?
No, porque por el Tª de Lagrange, 5 (orden de $H$) no divide a 12 (orden de $\mathbb{Z}_{12}$).
