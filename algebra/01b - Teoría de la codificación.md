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

## Detección y corrección de errores de transmisión
El **proceso de decodificación** se basa en los siguientes pasos:
1. Recibimos una palabra $w$
2. Buscamos la palabra $v$ del código que más se parezca a $w$: $\ D(w) = D(v)$.
Para encontrar estas palabras más parecidas a $w$ utilizamos la *distancia de Hamming*.

### Definiciones
- El **peso**, $w(x)$, de un elemento $x$ de $\mathbb{Z}_2^n$, es el número de unos que tiene $x$.
- La **distancia de Hamming** entre $x$ e $y$ es el número de coordenadas distintas que tienen $x$ e $y$, $d(x,y) = w(x+y)$.

Para **decodificar** se debe calcular todas las distancias de $w$ a palabras del código.

⚠️ $k$ es la menor distancia entre palabras del código.
### Teorema
> Sea $E: W \rightarrow C$ una función de codificación con $W \subseteq \mathbb{Z}_2^m$ y $C \subseteq \mathbb{Z}_2^n$. Dado $k>0$, y supongamos que el mínimo de las distancias entre elementos de $C$ es mayor o igual que $k$. Entonces:
> 1. El código detecta errores de peso menor o igual que $k-1$.
> 2. Es posible construir una función de decodificación $D$ que corrige los errores de transmisión de peso $\frac{k-1}{2}$.

# Códigos de grupo
### Definición
Un esquema de codificación $(W, C, E)$ se denomia código de grupo si el conjunto $C$ es un subgrupo de $\mathbb{Z}_2^n$.

Como ejemplo de los beneficios de un código de grupo está el cálculo de la menor de las distancias entre los elementos del  código:

### Teorema
> Si $(W,C,E)$ es un código de grupo, entonces el mínimo de las distancias entre elementos de $C$ es $$\text{mín}\{w(x)\ |\ x \in C - \{0\}\}$$

Otra ventaja de los códigos de grupo es que el proceso de decodificación se puede realizar más eficientemente.

`TODO`