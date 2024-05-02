# 1. Espacios Vectoriales
Un **espacio vectorial** sobre $\mathcal{K}$ es un conjunto $\mathcal{V}$ y dos operaciones:
- *Suma* (interna) que verifica que $(\mathcal{V}, +)$ es un grupo abeliano.
- *Producto por escalares*.

Los elementos de $\mathcal{V}$ se denominan **vectores** y los elementos de $\mathcal{K}$, escalares.

# 2. Base y Dimensión de un Espacio Vectorial
## 2.1 Dependencia e Independencia Lineal
### 2.1.1 Combinación Lineal
Una combinación lineal de los vectores $v_1, v_2,\dots,\ v_n$ es un vector $v$ de la forma $v= a_1v_1+a_2v_2+\dots+a_nv_n$  para escalares $a_i \in \mathcal{K}$.

### 2.1.2 Dependencia e independencia lineal
1. Los vectores son **linealmente dependientes** (*LD*) si alguno de ellos es combinación lineal (CL) del resto.
2. Los vectores son **linealmente independientes** (*LI*) si ningún vector es CL del resto.

### 2.1.3 Criterio de dependencia lineal
Los vectores $v_1,v_2,\dots,v_n$ son LD si la única CL de ellas igual a cero, $$a_1v_1 + a_2v_2+\dots+a_nv_n = 0$$
es la que cumple $a_1 = a_2 = \dots = a_n = 0$.

### 2.1.4 Matrices y dependencia lineal
Sea $B = \set{v_1,\dots,v_n}$ y la matriz $A$ en las que las filas son vectores de $B$:
- Si $rg(A) = m \Rightarrow$ Vectores *LI*
- Si $rg(A) < m \Rightarrow$ Vectores *LD*

### 2.1.5 Propiedades
Sea $B = \set{v_1,\dots,v_m}$ un conjunto de vectores de un espacio vectorial $\mathcal{V}$:
1. Si $B$ es LD, y le añadimos algunos vectores para obtener el conjunto $B'$, entonces $B'$ es LD.
2. Si $B$ es LI, y le quitamos algunos vectores para obtener el conjunto $B'$, entonces $B'$ es LI.
3. Si $B$ es LI, y $v$ es un vector que no es CL de $B'$, entonces $\set{v_1,\dots,v_m,v}$ es LI.
4. Si $B$ es LD, podemos encontrar una familia $B' \subset B$ tal que $B'$ es LI.

En el caso de $K^m$:
- Tomamos la matriz $A$.
- Obtenemos una matriz $E$ escalonada por filas y equivalente por filas a $A$.
- Las filas de $A$ que se corresponden con filas no nulas de $E$ son vectores LI.

## 2.2 Sistemas Generadores (SG)
Un conjunto de vectores $\set{v_1, \dots, v_n}$ de $\mathcal{V}$ es un **sistema generador** si todo vector de $\mathcal{V}$ es CL de ellos.

## 2.3 Base y Dimensión
### 2.3.1 Base
Un conjunto de vectores $B$ es una **base** del espacio vectorial $\mathcal{V}$ si es *LI* y un *SG*.

>**TEOREMA PRINCIPAL**
> Sea $\mathcal{V}$ un espacio vectorial. Entonces:
> 1. $\mathcal{V}$ tiene una base.
> 2. Todas las bases de $\mathcal{V}$ tienen el mismo número de vectores.

### 2.3.2 Dimensión
La **dimensión** de un espacio vectorial $\mathcal{V}$ es el número de elementos de cualquier base de $\mathcal{V}$.

### 2.3.3 Caracterización de la base
Supongamos que conocemos la dimensión de $\mathcal{V}: n$. Para comprobar si un conjunto de $n$ vectores es base, basta con que probemos una de las dos propiedades:
1. Si $B$ es LI, entonces es una base.
2. Si $B$ es un SG, entonces es una base.

### 2.3.4 Construcción de bases: reducción de bases
Sea $\mathcal{V}$ un espacio vectorial y $B$ un conjunto de vectores. Si $B$ es un SG, existe un subconjunto $B'$ de $B$ que es una base de $\mathcal{V}$.

### 2.3.5 Resultado fundamental
Sea $\set{v_1, \dots,v_n}$ un SG de $K^m$. Ponemos los vectores en las filas de una matriz $A$. Si aplicamos a $A$ una transformación elemental del tipo $F_i' \leftarrow F_i + aF_j$ con $a \neq 0$, entonces:
1. El rango de la matriz no varía.
2. Las filas de la matriz siguen siendo SG.
3. Sea $\mathcal{F}$ un subconjunto de filas de la matriz que contiene a $F_i$ y $\mathcal{F}'$ el mismo conjunto de filas pero cambiando $F_i$ por $F_i'$. Entonces $\mathcal{F}$ es LI si y solo si $\mathcal{F}'$ es LI.

### 2.3.6 Procedimiento para obtener una base
Reducimos la matriz $A$ a una matriz $E$ escalonada por filas. Entonces:
1. Las filas de la matriz $E$ no nulas forman una base de $V$.
2. Las filas de la matriz $A$ que se han transformado en las filas no nulas de $E$ forman una base de $V$.

### 2.3.7 Construcción de bases: Ampliación
Sea $V$ un espacio vectorial y $B = \set{v_1,\dots,v_n}$ un conjunto de vectores. Si $B$ es LI, existe una base $B'$ que contiene a $B$.

### 2.3.8 Procedimiento para ampliar una base de $K^n$ 
- Construimos la matriz $A$ con los vectores de $K^n$.
- Reducimos $A$ a una matriz escalonada.
- Añadimos vectores a las filas de la matriz para que sean LI.

## 2.4 Coordenadas
### 2.4.1 Propiedad fundamental de la base
Sea $V$ un espacio vectorial con la base $B = \set{v_1,\dots,v_n}$. Entonces todo vector se expresa de forma única como CL de $B$.

### 2.4.2 Coordenadas
Si $a\in V$ es un vector:
- Escribimos $a = a_1v_1 + \dots + a_nv_n$.
- $(a_1,\dots,v_n)_B$ son las **coordenadas de $a$ en la base $B$**.

### 2.4.3 Propiedad fundamental de las coordenadas
Todo lo que hemos hecho en los apartado anterior con $K^n$ lo podemos hacer con cualquier espacio vectorial $V$ sustituyendo los vectores por sus coordenadas respecto a una base fija $B$.

### 2.4.4 Cambio de base
Sean $V$ un espacio vectorial, bases $B = \set{v_1,\dots,v_n}$ y $B'=\set{w_1,\dots,w_n}$, y $u$ un vector de $V$ con coordenadas $X_B = (x_1,\dots,x_n)$ e $Y_{B'} = (y_1,\dots,y_n)$
- Tomamos $P$ la matriz cuyas columnas son las coordenadas de los vectores de $B$ respecto de la base $B'$.
- Entonces $Y_{B'} = P \cdot X_B$ (las coordenadas puestas por columnas)

> La matriz $P$ se denomina **matrix del cambio de base de $B$ a $B'$**

### 2.4.5 Propiedades
Sea $V$ un espacio vectorial  y $B$, $B'$ y $B''$ bases.
1. Si $P$ es la matriz de cambio de $B$ a $B'$, y $Q$ la de cambio de $B'$ a $B''$, entonces $QP$ es la matriz de cambio de $B$ a $B''$.
2. Si $P$ es la matriz de cambio de $B$ a $B'$, $P^{-1}$ es la matriz de cambio de $B'$ a $B$.

# 3. Subespacios Vectoriales
## 3.1 Subespacios Vectoriales
Si $V$ es un espacio vectorial y $W\subseteq V$ es un subconjunto. $W$ es un *subespacio vectorial* de $V$ si $W$ es un espacio vectorial con las mismas operaciones que $V$, es decir, las operaciones de $V$ tienen que ser cerradas en $W$.

### 3.1.1 Subespacio generado
Un subespacio generado por $B$, $L(B)$ o $\langle B \rangle$ es el conjunto de todas las CL de elementos de $B$.

### 3.1.2 Relación entre subespacios generados y transformaciones elementales
Sea $V$ un espacio vectorial y $B$ un subconjunto de vectores:
1. Si $B'$ es el resultado de cambiar el orden en los vectores de $B$, entonces $\langle B \rangle = \langle B' \rangle$.
2. Si $B'$ es el resultado de cambiar el vector $v_i$ de $B$ por $a \cdot v_i$ para un escalar no nulo, entonces $\langle B \rangle = \langle B' \rangle$.
3. Si $B'$ es el resultado de cambiar el vector $v_i$ de $B$ por $v_i + a \cdot v_j$ para algún $a \in K$, entonces $\langle B \rangle = \langle B' \rangle$.

## 3.2 Ecuaciones de un Subespacio Vectorial
### 3.2.1 Ecuaciones Paramétricas
Sea $V$ un espacio vectorial, $W$ un subespacio vectorial, $B$ una base de $V$ y $B_W$ una base de $W$. Las ecuaciones **paramétricas** de $W$ respecto a ambas bases se obtienen:
1. Ponemos los vectores de $B_W$ como CL de $B$. Poniendo las coordenadas por **columnas** obtenemos la matriz $A$.
2. Dando valores a los parámetros $\lambda_i$, obtenemos los vectores de $W$:

$$\pmatrix{x_1 \\ x_2 \\ \vdots \\ x_n} = \pmatrix{a_{11} & a_{12} & \cdots & a_{1m} \\ \vdots & \vdots &\ddots & \vdots \\ a_{n1} & a_{n2} & \cdots & a_{nm}} \cdot \pmatrix{\lambda_1 \\ \lambda_2 \\ \vdots \\ \lambda_n}$$

### 3.2.2 Ecuaciones cartesianas
Sea $V$ un espacio vectorial, $W$ un subespacio vectorial, $B$ una base de $V$ y $B_W$ una base de $W$. Las ecuaciones **cartesianas** de $W$ respecto a ambas bases se obtienen:
1. Tenemos que buscar $n - m$ ecuaciones.
2. Tomamos las ecuaciones paramétricas y eliminamos parámetros (mediante transformaciones elementales con los parámetros) hasta obtener las ecuaciones.

> **Observaciones**
> 1. La dimensión del subespacio vectorial es igual al número de parámetros de las ecuaciones paramétricas.
> 2. La dimensión del subespacio vectorial es igual a la dimensión del espacio vectorial $V$ menos el número de ecuaciones.

## 3.3 Suma e intersección de subespacios vectoriales
### 3.3.1 Intersección de subespacios vectoriales
Dados dos subespacios $W_1$ y $W_2$ de un espacio vectorial $V$, la intersección de ellos, $W_1 \cap W_2 = \set{x\in V\ |\ x \in W_1,\ x \in W_2}$. es un subespacio vectorial.

Las ecucaciones cartesianas de $W_1 \cap W_2$ es el conjunto de ecuaciones formadas por las de $W_1$ y $W_2$ juntas.

### 3.3.2 Suma de subespacios vectoriales
Dados dos subespacios vectoriales $W_1$ y $W_2$ de un espacio vectorial $V$, se define su suma como $W_1 + W_2 = \set{u+v \ |\ u \in W_1,\ v \in W_2}$.

**Propiedades**
1. La suma de dos subespacios vectoriales es un subespacio vectorial
2. Si $S$ es un SG de $W_1$ y $S'$ es un SG de $W_2$, entonces $S \cup S'$  es un SG de $W_1 + W_2$. 

### 3.3.3 Suma directa
Si $V$ es un EV y $U$ y $W$ son subespacios suyos tales que
- $V = U + W$ 
- $U \cap W = 0$
decimos que $V$ es *suma directa* de $U$ y $W$: $$V = U \oplus W$$
En tal caso, $W$ es el *complementario* de $U$.

### 3.3.4 Cálculo de un complemento de $U$
- Tomamos una base $\set{u_1, \dots,\ u_m}$ de $U$.
- La ampliamos a una base de $V$, $\set{u_1, \dots,\ u_m, u_{m+1},\dots,\ u_n}$
- Un complementario es $L(u_{m+1},\dots,\ u_n)$

### 3.3.5 Fórmula de las dimensiones
Dados subespacios vectoriales $U_1$ y $U_2$ de $V$, se cumple $$\dim(U_1 + U_2) = \dim(U_1) + \dim(U_2) - \dim(U_1\cap U_2)$$

