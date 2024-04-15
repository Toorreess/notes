# 1. Matrices
Sea $K$ un cuerpo, y $m, n \in \mathbb{N} - \{0\}$. Consideramos los conjuntos $M = \set{1,2,\dots,m}$ y $N=\set{1,2,\dots,n}$. Una matriz de tamaño $m\times n$ sobre $K$ (o con coeficientes de $K$) es una aplicación $A: M \times N \rightarrow K$. Al elemento $A(i,j)$ lo representaremos como $a_{ij}$.

Vamos a denotar por $M_{m\times n}(K)$ al conjunto de todas las matrices de tamaño $m \times n$ con coeficientes en $K$.

**Ejemplo**
Sea $A: \set{1,2} \times \set{1,2} \rightarrow \mathbb{R}$ dada por $A(1, 1) = 3,\ A(2, 1) = −5,\ A(1, 2) = 0,\ A(2, 2) = 2$. Entonces $A$ es una matriz $2 \times 2$ sobre $\mathbb{R}$, es decir, $A \in M_{2\times 2}(\mathbb{R})$. Normalmente, daremos la matriz como sigue:

Sea $A = \begin{pmatrix}3 & 0 \\ -5 & 2\end{pmatrix} \in M_{2\times 2}(\mathbb{R})$ 

![[Pasted image 20240411160916.png]]

## 1.1 Tipos y elementos de matrices
- **Matriz cuadrada:** $m = n$ 
- **Diagonal principal:** $a_{11}, a_{22},\dots,a_{mm}$ 
- **Triangular superior:** ![[Pasted image 20240411161054.png]]
- **Triangular inferior:** ![[Pasted image 20240411161113.png]]
- **Diagonal:**![[Pasted image 20240411161132.png]]
- **Escalar:** ![[Pasted image 20240411161154.png]]
- **Identidad:** ![[Pasted image 20240411161207.png]]

## 1.2 Transformaciones elementales. Forma normal de Hermite
- *Pivote de la fila $i$*: el primer elemento no nulo de la fila $i$.
- *Pivote de la columna $j$*: el primer elemento no nulo de la fila $j$.

### 1.2.1 Matriz escalonada por filas
Se dice que $A$ es escalonada por filas si:
1. Cualquier fila que esté debajo de una fila nula es también nula: Si $a_{i1} = a_{i2} = \dots = a_{in} = 0 \Rightarrow a_{i'1} = a_{i'2} = \dots = a_{i'n} = 0$, para $i' > i$. Esto significa que, caso de haber filas nulas en la matriz $A$, estas están todas al final.
2. El pivote de una fila no nula está a la derecha del pivote de la fila anterior (salvo para la primera fila). Es decir, si $a_{ij}$ es el pivote de la fila $i$-ésima y $a_{i+1j'}$ es el pivote de la fila $i+1$-ésima, entonces $j < j'$.
3. El pivote de cada fila no nula vale $1$.

Una matriz que sea escalonada por filas se dice *escalonada reducida por filas* si en las columnas donde hay algún pivote (de alguna fila), el resto de elementos son nulos.

### 1.2.2 Matriz escalonada por columnas
1. Cualquier columna que esté a la derecha de una columna nula es también nula.
2. El pivote de una columna no nula está por debajo del pivote de la columna anterior.
3. El pivote de cualquier columna no nula vale 1.

Una matriz escalonada por columnas se dice escalonada reducida por columnas si en las filas donde haya algún pivote, el resto de elementos son nulos.

### 1.2.3 Transformaciones elementales por filas 
Dadas dos matrices $A,B \in \mathcal{M}_{m\times n}(K)$, decimos que se ha obtenido a partir de mediante una transformación elemental por filas de:
- *Tipo I*, si $B$ es el resultado de intercambiar dos filas de $A$.
- *Tipo II*, si $B$ es el resultado de sustituir una fila de $A$ por esa misma fila multiplicada por un escalar.
- *Tipo III*, si $B$ es el resultado de sustituir una fila de $A$ por el resultado de sumar a esa fila otra fila multiplicada por un escalar.

### 1.2.4 Transformaciones elementales por columnas
Se definen de forma análoga.

### 1.2.5 Matrices equivalentes por filas
Dos matrices $A, B \in \mathcal{M}_{m\times x}(K)$ se dice que son equivalentes por filas, escrito $A\sim_f B$, si $B$ se puede obtener a partir de $A$ aplicándole una cantidad finita de transformaciones elementales por filas.

### 1.2.6 Matrices equivalentes por columnas
Se definen de forma análoga.

### 1.2.7 Forma normal de Hermite por filas
Toda matriz $A \in \mathcal{M}_{m\times n}(K)$ es equivalente por filas a una *única matriz escalonada reducida por filas* $B$. Para ello, se sigue el siguiente proceso:
1. Intercambiamos las filas de forma que los pivotes de las filas queden escalonados.
2. Hacemos 1 el pivote de la primera fila. Hacemos ceros debajo del pivote de la primera fila.
3. Hacemos 1 el pivote de la segunda fila. Hacemos ceros debajo y encima del pivote de la segunda fila.
4. Repetimos sucesivamente.

### 1.2.8 Formal normal de Hermite por columnas
Toda matriz $A \in \mathcal{M}_{m\times n}(K)$ es equivalente por columnas a una *única matriz escalonada reducida por columnas* $B$. Para ello, se sigue el siguiente proceso:
1. Intercambiamos las columnas de forma que los pivotes de las columnas queden escalonados.
2. Hacemos 1 el pivote de la primera columna. Hacemos ceros a la derecha de éste.
3. Hacemos 1 el pivote de la segunda columna. Hacemos ceros a la derecha y a la izquierda de éste.
4. Repetimos sucesivamente.

### 1.2.9 Rango de una matriz
El rango de una matriz $A \in \mathcal{M}_{m\times n}(K)$ es el número de filas no nulas de la matriz normal de Hermite.

## 1.3 Operaciones con matrices
### 1.3.1 Suma de matrices
Dos matrices $A,B \in \mathcal{M}_{m\times n}(K)$ se suman componente a componente. Deben tener las mismas dimensiones.

### 1.3.2 Producto de matrices
El producto de dos matrices $A\in \mathcal{M}_{m\times n}(K)$ y $B \in \mathcal{M}_{m\times l}(K)$ es (observa que el número de columnas de $A$ es igual al número de filas de $B$ ):

![[Pasted image 20240411164542.png]]

donde $c_{ij}$ es el resultado de multiplicar la fila $i$ de $A$ por la columna $j$ de $B$. Esta multiplicación se realiza del siguiente modo:

![[Pasted image 20240411164627.png]]

### 1.3.3 Propiedades de las operaciones
El conjunto $\mathcal{M}_m(K)$ de matrices cuadradas $m\times m$ sobre el cuerpo $K$ es un **anillo**. Es decir:
1. $\mathcal{M}_m(K)$ es un grupo abeliano con la suma.
2. El producto de $\mathcal{M}_m(K)$ es asociativo y tiene elemento unidad.
3. El producto es distributivo respecto de la suma.

*Nota.* El producto no es en general conmutativo. Además, no toda matriz tiene inversa respecto del producto.

### 1.3.4 Transformaciones por filas y producto de matrices
Sean $A,B \in \mathcal{M}_{m\times n}(K)$ tales que $B$ es equivalente por filas a $A$.

- Apliquemos a $I_m$ las mismas transformaciones por filas que le hemos aplicado a $A$ para obtener $B$. Obtenemos una matriz $P$.
- Entonces, $B = P \cdot A$ 

Una matriz $E$ que resulta de aplicarle a $I_m$ una transformación elemental por filas, se llama matriz elemental. Por tanto, $P$ es producto de matrices elementales.

## 1.4 Matriz traspuesta
La matriz traspuesta de una matriz $A\in \mathcal{M}_{m\times n}(K)$, denotada $A^t$, es la matriz que resulta de poner en las columnas las filas de $A$.

### 1.4.1 Propiedades de la matriz traspuesta
1. $(A+B)^t = A^t + B^t$
2. $(a\cdot A)^t = a \cdot A^t$
3. (A^t)^t = A
4. $(A\cdot B)^t = B^t \cdot A^t$

### 1.4.2 Traspuesta y forma normal de Hermite
La forma normal de Hermite por columnas de $A$ puede obtenerse del siguiente modo:
1. Calculamos la forma normal de Hermite por filas $H$ de $A^t$.
2. La forma normal de Hermite por columnas es $H^t$.

## 1.5 Matrices regulares
Una matriz $A \in \mathcal{M}_m(K)$ se dice regular si tiene inversa para el producto, es decir, $\exists B:\ A\times B = B\times A = I_m$

### 1.5.1 Propiedades de las matrices regulares
1. La inversa de una matriz es única.
2. Una matriz puede tener inversas por la derecha, pero no tener inversa. Por ejemplo, la matriz sobre $\mathbb{Z}_2$.
3. $(A^{-1})^{-1} = A$
4. $(A \cdot B)^{-1} = B^{-1} \cdot A^{-1}$
5. Toda matriz regular es producto de matrices elementales.

### 1.5.2 Matrices regulares y rango
Una matriz $A \in \mathcal{M}_n(K)$ es regular si y solo si tiene rango $n$.

Además, la inversa de $A$ se obtiene aplicando a la matriz identidad las mismas transformaciones que le apliquemos a $A$ para obtener la forma normal de Hermite de $A$ por filas.

## 1.6 Matrices equivalentes
Dos matrices $A,B \in \mathcal{M}_{m\times n}(K)$ se dicen equivalentes si existen matrices regulares $P \in \mathcal{M}_m(K)$ y $Q \in \mathcal{M}_n(K)$ tales que $$B = P \cdot A \cdot Q$$
### 1.6.1 Propiedades de la equivalencia
1. La relación "Matrices equivalentes" es una relación de equivalencia.
2. Si $A$ y $B$ son equivalentes por filas, entonces $A$ y $B$ son equivalentes.

**Propiedad fundamental de la equivalencia de matrices:** Sea $A \in \mathcal{M}_{m\times n}(K)$ de rango $r$. Entonces $A$ es equivalente a una matriz de la forma ![[Pasted image 20240411170442.png]]

## 1.7 Determinantes
Dada una matriz $A \in \mathcal{M}_m(K)$, definimos su determinante $\det(A)$ recursivamente:
1. $m=1,\ \det(A) = a_{11}$ 
2. $m>1, \det(A) = a_{i1} \cdot \alpha_{i1} + a_{i2} \cdot \alpha_{i2} + \dots + a_{in} \cdot \alpha_{in}$, donde 
	- $\alpha_{ij} = (-1)^{i+j} \cdot \det(A_{ij}) \rightarrow$ **Adjunto del elemento $a_{ij}$**. 
	- $A_{ij}$ es la matriz que resulta de quitarle a $A$ la fila $i$ y la columna $j$.

### 1.7.1 Propiedades de los determinantes
1. Si intercambiamos dos filas, el determinante cambia de signo.
2. Si multiplicamos una fila o una columna por un escalar, el determinante queda multiplicado por ese escalar.
3. Si a una fila (columna) le sumamos otra multiplicada por un escalar, el determinante no varía.
4. Si una fila o columna es nula, el determinante es cero.
5. $A$ es regular $\iff \det(A) \neq 0$.
6. $\det(A \cdot B) = \det(A) \cdot \det(B)$
7. $\det(A^t) = \det(A)$
8. $\det(A) = a_{1j} \cdot \alpha_{1j} + a_{2j} \cdot \alpha_{2j} + \dots + a_{nj} \cdot \alpha_{nj}$

### 1.7.2 Inversa y determinantes
Si $A \in \mathcal{M}_m(K)$ es una matriz regular, entonces: $$A^{-1} = \det(A)^{-1} \cdot Adj(A^t)$$
donde $\forall A, Adj(A)$ es la matriz formada por todos los adjuntos de $A$ (*matriz adjunta*).

### 1.7.3 Determinantes y rango
El rango de una matriz $A$ coincide con el orden de la mayor submatriz de $A$ cuyo determinante sea distinto de cero.

# 2. Sistemas de ecuaciones lineales
## 2.1 Sistemas de ecuaciones lineales
Un sistema de ecuaciones lineales es un conjunto de ecuaciones lineales

![[Pasted image 20240415154236.png]]
Una **solución del sistema** es un conjunto de $n$ elementos $(s_1,\dots,s_n)$ que cumplen todas las ecuaciones del sistema.

### 2.1.1 Forma matricial de un sistema
- *Matriz de coeficientes:* ![[Pasted image 20240415154641.png]]
- *Matriz de términos independientes:* ![[Pasted image 20240415154700.png]]

*Matriz incógnita: ![[Pasted image 20240415154728.png]]
*Matriz ampliada:* ![[Pasted image 20240415154748.png]]
- *Forma matricial:* $AX = b$

### 2.1.2 Tipos de sistemas
- **Sistemas incompatibles:** Sin solución.
- **Sistemas compatibles:** Con solución.
	- *Sistemas compatibles determinados:* Con una única solución.
	- *Sistemas compatibles indeterminados:* Con más de una solución.

### 2.1.3 Sistemas equivalentes
Dos sistemas son equivalentes cuando tienen las mismas soluciones.

### 2.1.4 Sistemas y forma normal de Hermite
Si $AX = b$ es un sistema de ecuaciones y $P$ es una matriz regular, entonces $PAX = Pb$ es un sistema equivalente.

Si $C$ es la forma normal de Hermite por filas de la matriz ampliada del sistema, entonces el sistema correspondiente es equivalente.

### 2.1.5 Teorema de Rouché-Frobenious
Sea $Ax = b$ un sistema de $m$ ecuaciones y $n$ incógnitas. Entonces:
- El sistema es compatible $\iff$ $rg(A) = rg(A|b)$.
- Si el sistema es compatible, entonces:
	- Si $rg(A) = n$, es **compatible determinado**.
	- Si $rg(A) < n$, es **compatible indeterminado**.

### 2.1.6 Regla de Cramer
Un sistema de ecuaciones $AX = b$ se dice de Cramer si tiene el mismo número de ecuaciones que de incógnitas y $A$ es regular. En este caso:
![[Pasted image 20240415160411.png]]

