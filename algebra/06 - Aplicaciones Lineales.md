# 1 Aplicaciones Lineales
## 1.1 Definición y Ejemplos
Una **aplicación lineal** es un homomorfismo de espacios vectoriales, es decir, aplicaciones que preservan las operaciones de espacios vectoriales:
1. $f(v+w) = f(v) + f(w)$ para cualesquiera $v,w \in V$. 
$$
\begin{align}
	V &\xrightarrow{f} W \\ v_1 &\xmapsto{} f(v_1) \\ v_2 &\xmapsto{} f(v_2) \\ v_1 + v_2 &\xmapsto{} f(v_1+v_2)
\end{align}$$

2. $f(kv) = kf(v)$ para cualesquiera $v\in V$ y $k \in K$.
$$\begin{align}
	V &\xrightarrow{f} W \\ v &\xmapsto{} f(v) \\ kv &\xmapsto{} f(kv)
\end{align}$$


###### Ejemplos: 
1. La aplicación $f: \mathbb{R}^3 \rightarrow \mathbb{R}^3$ dada por $f(x,y,z) = (x, 2x+y, y)$ es una aplicación lineal. 
2. La aplicación $f: \mathbb{R}^2 \rightarrow \mathbb{R}$ dada por $f(x,y) = x^2$ no es lineal.

### 1.1.1 Propiedades
1. $f(0) = 0$
2. $f(-u) = -f(u)$
3. $f$ preserva CLs.

### 1.1.2 Suma, producto y composición de aplicaciones lineales


## 1.2 Aplicaciones Lineales y Matrices
Una aplicación lineal $f$ entre dos espacios vectoriales $V$ y $V'$ queda determinada por las imágenes de los vectores de una base $B$ de $V$.
##### Ejemplo: De $f: \mathbb{R}^2 \rightarrow \mathbb{R}^2$ se sabe que es lineal y $f(1,0) = (3,-1)$ y $f(0,1) = (-2,3)$. ¿Cuánto vale $f(4,2)$?
$$
\begin{align}
(4,2) &= 4\cdot(1,0) + 2\cdot (0,1) \\
f(4,2) &= 4\cdot f(1,0) + 2\cdot f(0,1)\\
f(4,2) &= 4\cdot (3,-1) + 2\cdot (-2,3) = (8,2)
\end{align}$$



### 1.2.1 Matriz  de una aplicación lineal
Sean $f: V \rightarrow V'$ una aplicación lineal y $B$ y $B'$ bases de $V$ y $V'$ respectivamente. La matriz de $f$ respecto a las bases $B$ y $B'$, $M(f, B, B') = M_{B,B'}(f)$, se obtiene:
1. Calculamos las imágenes de los vectores de $B, \set{f(v_1), f(v_2),\dots,f(v_n)}$.
2. Obtenemos las coordenadas de estás imágenes en la base $B'$, esto es, $f(v_i) = a_{1i}v_1' + \cdots + a_{mi}v_m'$.
3. Las coordenadas de cada vector son las columnas de la matriz $M(f, B, B')$.

### 1.2.2 Expresión matricial de una aplicación
Dada $f: V \rightarrow V'$ una aplicación lineal, y bases $B$ de $V$ y $B'$ de $V'$, la expresión matricial de $f$ es: 

![[Pasted image 20240506170435.png]]

### 1.2.3 Matrices de aplicaciones lineales y operaciones
Sean $f$ y $g$ aplicaciones lineales sobre los mismos espacios vectoriales, $a\in K$, $B$ base de $V$ y $B'$ base de $V'$. Entonces:
1. $M(f+g, B, B') = M(f,B,B') + M(g, B, B')$.
2. $M(af, B, B') = aM(f,B,B')$.

Si $V''$ es otro espacio vectorial, $h: V' \rightarrow V''$ una aplicación vectorial, $h: V' \rightarrow V''$ una aplicación lineal y $B''$ una base de $V''$, entonces:
3. $M(h \circ f, B, B'') = M(h, B', B'') \cdot M(f, B,B')$

### 1.2.4 Matrices de aplicaciones y matrices de cambio de base
Sea $f: V \rightarrow V'$ una aplicación lineal, $B_1$ y $B_2$ bases de $V$, y $B'_1$ y $B'_2$ bases de $V'$. Entonces: $$M(f, B_1, B_1') = M(B'_2, B'_1) \cdot M(f,B_2,B_2') \cdot M(B_1,B_2)$$

# 2. Núcleo e Imagen de una Aplicación Lineal
## 2.1 Núcleo e imagen de una aplicación lineal
Dada una aplicación lineal $f: V\rightarrow V'$, se define:
1. El núcleo de $f$, $N(f)$, como: $$Ker(f) = \set{v\in V\ |\ f(v)=0}$$
2. La imagen de $f$, $Img(f)$, como: $$Img(f)=\set{f(v)\ |\ v\in V}$$

### 2.1.1 Subespacios vectoriales
El núcleo y la imagen de una aplicación vectorial son subespacios vectoriales.

1. Para calcular el núcleo, basta reducir el sistema de ecuaciones dado por la condición $f(x)=0$.
2. Para calcular la imagen, tomamos una base $B$ de $V$. Entonces, $Im(f)$ es el subespacio generado por $f(B)$.

### 2.1.2 Relación entre las dimensiones
Sea $f: V \rightarrow V'$ una aplicación lineal. Entonces: $$\dim(V) = \dim(Ker(f)) + \dim(Img(f))$$
# 3. Aplicaciones Lineales Inyectivas, Sobreyectivas y Biyectivas

