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