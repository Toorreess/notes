### Estructura algebraica

Una **estructura algebraica** es un conjunto de elementos con una o varias operaciones que cumplen ciertas propiedades.

### Definición 1. 
> Una *operación interna* en $\mathbf{X}$ es una aplicación $\rho: \mathbf{X} \times \mathbf{X} \rightarrow \mathbf{X}$.
> 
> El *calificativo interna* se refiere a que el resultado de la operación está
dentro del conjunto inicial X.
### Conjunto X
- Estructura en X con una operación $\rightarrow$ **[[01a1 - Grupo]]**
- Estructura en X con dos operaciones:
	- **[[01a2 - Anillos]]**
	- **[[Retículos]]** (álgebra de Boole)
- Estructura con operación externa $\rightarrow$ **[[Espacios vectoriales]]**

### Propiedades ($\mathbf{X}$, $\star$)
- **Asociativa**: $(a \star b) \star c = a \star (b \star c)$
- **Elemento neutro**: $\exists e \in \mathbf{X} : e \star x = x \star e = x$ 
- **Elemento inverso**: $\forall a, \exists b \ |\ a,b \in \mathbf{X}: a \star b = b \star a = e$
- **Conmutativa**: $x \star y = y \star x$
- **Distributiva de $\star$ respecto $\oplus$**: $x \star (y \oplus z) = (x\star y) \oplus (x\star z)$

# 1. Grupo
## 1.1 Grupo
Un **grupo** es una [[01 - Grupos, anillos y cuerpos. Introducción|estructura algebraica]] con una operación, que cumple las propiedades de:
- **Asociatividad**
- **Elemento neutro**
- **Elemento inverso**

Si el grupo cumple, además, la propiedad **conmutativa**, el grupo es **abeliano**.

**TEOREMA**
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
### 1.1.1 Potencias
Sea $(G,\star)$ un grupo y $g \in G$. Definimos las potencias de $G$ del siguiente modo:
1. $g^0 = e$
2. $g^{n+1} = g^n \star g, \forall n \in \mathbb{N}$ 
3. $g^{-n} \rightarrow \text{inverso de } g^n$
## 1.2 Estructuras de grupo en $\mathbb{Z}_n$
$$\mathbb{Z}_n = \{0, 1, \dots, n-1\}, \ n \in \mathbb{N}$$
#### Ejemplo
En $\mathbb{Z}_5 = \{0, 1, 2, 3, 4\}$
- $[3] + [4] = [7] = [2]$
- $[3] \cdot [4] = [12] = [2]$

En $\mathbb{Z}_n$ estamos identificando números entero $[x] = [y]$ si $x$ e $y$ tienen el mismo resto al dividirlos entre $n$.
#### Corolario
- $(\mathbb{Z}_n,\ +)$ es un grupo abeliano.
- $(\mathbb{Z}^*_n,\ \cdot)$ es grupo abeliano si **n es primo**. 

## 1.3 Grupo simétrico $(S_n, \circ)$
El grupo simétrico de orden $n$, $S_n$ está formado por todas las posibles ordenaciones (permutaciones) de $\mathbb{N}_n$. Una ordenación es una aplicación biyectiva. Es decir:

$$S_n = \{f: \mathbb{N}_n \rightarrow \mathbb{N}_n \ | \ f \text{ es biyectiva} \}$$
La interpretación de las permutaciones como funciones permite definir
una operación en $\mathbb{N}_n$: la composición. El par $(S_n, \circ)$ es un grupo, el *grupo*
*simétrico de orden $n$*.

> **Función inyectiva**: 2 elementos no tienen la misma imagen.
> **Función sobreyectiva**: Todos los elementos del conjunto final es imagen

#### Ejemplo. $n = 3$, $\mathbb{N}_3 = \{1, 2, 3\}$

![[Pasted image 20240225215047.png]]


### 1.3.1 Composición de funciones
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

**TEOREMA**
> $(S_n, \circ)$ es un grupo no necesariamente conmutativo

### 1.3.2 Aplicación identidad

![[Pasted image 20240225220453.png]]

### 1.3.3 Aplicación inversa: $\alpha^{-1} \circ \alpha$

![[Pasted image 20240225220747.png]]

## 1.4 Subgrupos
**TEOREMA**
> Sea $(G, \star)$ un grupo y $H \subseteq G$. $(H, \star)$ es subgrupo de $(G, \star)$ si:
> 1. Es cerrado para la operación $\star$: $a, b \in H \Rightarrow a \star b \in H$
> 2. Es cerrado para inversos: $a \in H \Rightarrow a^{-1} \in H$

⚠️ Si $H$ es finito, $(H, \star)$ es subgrupo $\Leftrightarrow$ verifica que es cerrado para $\star$
⚠️ Si $(G, \star)$ es abeliano, $(H, \star)$ también lo será.

## 1.5 Grupos cíclicos
#### Reflexión
1. Toma una permutación $\alpha$ de S4.
2. Calcula todas potencias de $\alpha$. El conjunto formado por tales potencias, ¿es un subgrupo de S4? ¿Por qué?

![[Pasted image 20240226154339.png]]
Es subgrupo ya que tiene elemento neutro y es cerrado para la operación.

### 1.5.1 Definición: Subgrupo generado por a
Sea $G$ un grupo y $a \in G$. Se denomina **subgrupo generado por $a$** al conjunto formado por todas las potencias de $a$. Denotaremos dicho subgrupo por $\langle a \rangle$.
$$\langle a \rangle = \{a | z \in \mathbb{Z}\}$$

### 1.5.2 Definición: Grupo cíclico
Un grupo $G$ es **cíclico** si contiene un elemento $a$ tal que $\langle a \rangle = G$. El elemento $a$ se llama *generador* de $G$.

### 1.5.3 Definición: Orden de a
Si $G$ es un grupo y $a\in G$, el menor natural $n$ tal que $a^n=e$ se llama **orden de a** y se denota por $o(a)$
1. $o(a)$ coincide con el número de elementos de $\langle a \rangle$.
2. $a^m = a^r$ donde $r$ es el resto de dividir $m$ entre $n$.

**TEOREMA**
> 1. El menor de los n ́umeros naturales no nulo tales que $a^n = e$ coincide con el número de elementos del subgrupo $\langle a \rangle$ generado por $a$.
> 2. Si $a^k=e$ entonces $o(a)$ es un divisor de $k$.

## 1.6 Productos de grupos
$$\mathbb{Z}_2^5 = \mathbb{Z}_2 \times \mathbb{Z}_2 \times \mathbb{Z}_2 \times \mathbb{Z}_2 \times \mathbb{Z}_2 = \{ (x_1, x_2, x_3, x_4, x_5)\ |\ x_i \in \mathbb{Z}_2\}$$
El producto de grupos es una forma de representar conjuntos de bits. Se utiliza el producto cartesiano.

#### Proposición. Dados $(G, \star)$ y $(H, \square)$, definimos $G \times H$ la operación $\circ$ del siguiente modo:
$$(g_1, h_1) \circ (g_2, h_2) = (g_1 \star g_2,\ h_1 \square h_2)$$
Entonces $(G \times H, \circ$) tiene estructura de grupo.

## 1.7 Homomorfismos de grupo
¿Cómo podemos comparar 2 grupos $(G, \star)$ y $(H, \oplus)$?

Con aplicaciones $f: G \rightarrow H$ que respete las dos operaciones de grupo:
$$a, b \in G,\ f(a\star b) = f(a) \oplus f(b)$$
#### Ejemplo:
![[Pasted image 20240226161833.png]]
![[Pasted image 20240226162125.png]]

**TEOREMA**
> 1. Si $e$ es la unidad de $G$, entonces $f(e)$ es la unidad de $H$.
> 2. Dado $a \in G, \ f(a)^{-1} = f(a^{-1})$ 
> 3. Dado $a \in G,\ n \in \mathbb{Z},\ f(a^n) = f(a)^n$
> 4. Si $S$ es subgrupo de $G$, $f(S)=\{f(s)\ |\ s\in S\}$ es un subgrupo de H. En particular, $f(G)$ es un subgrupo de $H$, llamado *imagen de $f$*.

### 1.7.1 Definición: Isomorfismo
Un **isomorfismo de grupo** es un *homomorfismo de grupo* que es **biyectivo**. 

Los grupos isomorfos son, como estructura de grupo, esencialmente el mismo. Es decir, tienen las mimas propiedades de grupo.

## 1.8 Clases laterales. Teorema de Lagrange.
### 1.8.1 Definición. Clases laterales
Sea $(G, \star)$, $H$ un subgrupo de $G$ y $a$ un elemento de $G$. Se llama **clase lateral a izquierda de $H$ respecto de $a$** al conjunto $$a \star H = \{a \star h\ |\ h \in H\}$$
La **clase lateral a derecha de $h$ respecto de $a$** es el conjunto $$H \star a = \{h \star a\ |\ h \in H$$
**TEOREMA**
1. La única clase lateral que es un subgrupo es $e\star H$. Además, $[e] + H = H$.
2. Todas las clases laterales a izquierda tienen el mismo número de elementos que H.
3. La familia de clases laterales a izq respecto de $H$ son una partición de $G$, es decir, si estas clases laterales son $H_1, \dots, \ H_n$, entonces:
	1. $H_i \cap H_j = \emptyset$    
	2. $G = \bigcup_{i=1}^n H_i$ 
4. Si $[b] \in [a] + H \Rightarrow [b] + H = [a] + H$  

### 1.8.2 Teorema de Lagrange.
Sea $G$ un grupo y $H$ un subgrupo de $G$. Entonces el orden de $H$ es un divisor del orden de $G$.

#### Ejemplo: ¿Es $\{[0], [1], [2], [3], [4]\}$ subgrupo de $\mathbb{Z}_{12}$?
No, porque por el Tª de Lagrange, 5 (orden de $H$) no divide a 12 (orden de $\mathbb{Z}_{12}$).

# 2. Teoría de la codificación
El problema que pretende resolver la *Teoría de la codificación* es el de transmitir mensajes a través de un canal que tiene *ruido* que provoca que el mensaje que se recibe no sea exactamente el mensaje que se ha enviado.

Los mensajes con los que trabajaremos serán elementos del conjunto $\mathbb{Z}_2^n$, para algún $n\in \mathbb{N}$.

A cada mensaje, o *palabra*, le añadiremos algunos **dígitos de control** que nos servirán para identificar si el mensaje recibido es el que se ha enviado o tiene algunos errores. La lista formada
por la palabra inicial y los dígitos añadidos se denomina *código*. El esquema general es el siguiente:

![[Pasted image 20240228201435.png]]
### Esquema de codificación $(n, m)$-código 

1. El conjunto de palabras: $W \subseteq \mathbb{Z}_2^m = x_1x_2\dots x_m$.
2. El conjunto de códigos: $C \subseteq \mathbb{Z}_2^n$ con $n > m$. 
3. Función de codificación: $E: W \rightarrow C$. $\ E(w) = c$
4. Recibimos el mensaje $T(c)$ (mensaje corrupto)
5. Corregir $T(c) \rightarrow c$ 
6. Función de decodificación $D: C \rightarrow W$. $\ D(c) = w$

#### Ejemplo. $(9,8)$-código
$$
\begin{align}
E: \mathbb{Z}_2^8 &\rightarrow \mathbb{Z}_2^9 \\
E(x_1x_2x_3\dots x_8) = x_1x_2x_3&\dots x_8(x_1+x_2+\dots+x_9)
\end{align}
$$

## 2.1 Detección y corrección de errores de transmisión
El **proceso de decodificación** se basa en los siguientes pasos:
1. Recibimos una palabra $w$
2. Buscamos la palabra $v$ del código que más se parezca a $w$: $\ D(w) = D(v)$.
Para encontrar estas palabras más parecidas a $w$ utilizamos la *distancia de Hamming*.

### 2.1.1 Definiciones
- El **peso**, $w(x)$, de un elemento $x$ de $\mathbb{Z}_2^n$, es el número de unos que tiene $x$.
- La **distancia de Hamming** entre $x$ e $y$ es el número de coordenadas distintas que tienen $x$ e $y$, $d(x,y) = w(x+y)$.

Para **decodificar** se debe calcular todas las distancias de $w$ a palabras del código.

⚠️ $k$ es la menor distancia entre palabras del código.

**TEOREMA**
> Sea $E: W \rightarrow C$ una función de codificación con $W \subseteq \mathbb{Z}_2^m$ y $C \subseteq \mathbb{Z}_2^n$. Dado $k>0$, y supongamos que el mínimo de las distancias entre elementos de $C$ es mayor o igual que $k$. Entonces:
> 1. El código detecta errores de peso menor o igual que $k-1$.
> 2. Es posible construir una función de decodificación $D$ que corrige los errores de transmisión de peso $\frac{k-1}{2}$.

## 2.2 Códigos de grupo
Un esquema de codificación $(W, C, E)$ se denomia código de grupo si el conjunto $C$ es un subgrupo de $\mathbb{Z}_2^n$.

Como ejemplo de los beneficios de un código de grupo está el cálculo de la menor de las distancias entre los elementos del  código:

**TEOREMA**
> Si $(W,C,E)$ es un código de grupo, entonces el mínimo de las distancias entre elementos de $C$ es $$\text{mín}\{w(x)\ |\ x \in C - \{0\}\}$$

Otra ventaja de los códigos de grupo es que el proceso de decodificación se puede realizar más eficientemente.

`TODO`

# 3. Anillos
Un **anillo** es un *grupo abeliano* $(A, +)$ con otra operación $\cdot$ que verifica:
- El producto es asociativo: $(a\cdot b) \cdot c = a \cdot (b\cdot c)$
- Se verifica la distributiva del producto respecto a la suma: $(b+c) \cdot a = b \cdot a + c \cdot a$

## 3.1 Tipos de anillos
- **Conmutativos**: Si $\cdot$ es conmutativo.
- **Unitario** o *con unidad*: Si $\cdot$ tiene elemento neutro.
- **Cuerpo**: Si $\cdot$ es conmutativo, tiene elemento neutro y tiene inversos

#### Ejemplo
- $(\mathbb{Z}, +, \cdot) \rightarrow$ conmutativo y unitario
- $(M_n(\mathbb{R}, +, \cdot)) \rightarrow$ unitario
- $(\mathbb{R}[x], +, \cdot) \rightarrow$ conmutativo y unitario
- $(\mathbb{Z}_n, +, \cdot) \rightarrow$ conmutativo y unitario
*Cuerpos:*
- $\mathbb{Q, R, C}$ 
- $(\mathbb{Z}_n, +, \cdot)$ con $n$ primo.

## 3.2 Subanillos
Un **subanillo** de un anillo $(R, +, \cdot)$ es un subconjunto $S \subseteq R$ que es un anillo con las operaciones de $R$.

**TEOREMA**
>Sea $(R,+,\cdot)$ un anillo y $S$ un subconjunto suyo. $S$ es un subanillo sii verifica:
>1. Es cerrado para las operaciones.
>2. Respecto a la suma: contiene elemento neutro.
>3. Respecto a la suma: si $s \in S \Rightarrow -s \in S$.

#### Ejemplo. $(\mathbb{Z}, +, \cdot)$
$S = \text{múltiplos de 5}$
1. Es cerrado para la suma y para la multiplicación.
2. Contiene al cero: $0 = 0 \cdot 5 \in S$
3. Tiene inverso respecto a la suma: $-z_1 \cdot 5 = (-z_1)\cdot 5$

### 3.2.1 Anillo ideal
Un anillo ideal es un subanillo $S$ de $R$ en el que se cumple que:
$$r\in R, s\in S \Rightarrow rs \in S$$
## 3.3 Homomorfismo de anillos
Dados dos anillos $(R,+,\cdot)$ y $(S, \oplus, \star)$. Un *homomorfismo de anillos* es una aplicación $f: R \rightarrow S$ que respeta las operaciones de los anillos. Verifican:
1. $f(x+y)=f(x) \oplus f(y),\ \forall x,y \in R$ 
2. $f(x\cdot y)=f(x) \star f(y),\ \forall x,y \in R$

Los homomorfismos de anillos respetan todas los elementos restantes de los anillos. Es decir:
1. $f(0_R) = 0_S$
2. $f(-a) = -f(a)$
3. Si $A$ es un subanillo de $R$, entonces $f(A)$ es un subanillo de $S$
4. Si $R$ y $S$ son conmutativos e $I$ es un ideal de $R$, entonces $I$ es un ideal de $S$.

### 3.3.1 Definición. Isomorfismo
Un homomorfismo de anillos $f : R \rightarrow S$ se llama *isomorfismo* si es biyectivo.