Una aplicación $f: A \rightarrow B$ se dice:
- *inyectiva* si $\forall x,y\in A, f(x) = f(y) \Rightarrow x = y$, es decir, elementos distintos $x$ e $y$ tienen imágenes distintas.
- *sobreyectiva* si $\forall b \in B, \ \exists a\in A \ / \ f(a) = b$, es decir, todo elemento de $B$ es imagen de alguno de $A$. 
- *biyectiva* si es inyectiva y sobreyectiva. La aplicación es biyectiva si y solo si tiene inversa, es decir, $\exists g: B \rightarrow A \ / \ fg(b) = b\ \text{  y }\  gf(b)=a, \ \forall\ a \in A, \ b\in B$.

# 1. Conjuntos equipotentes. Cardinal de un conjunto
## 1.1 Conjuntos equipotentes
Dos conjuntos $A$ y $B$ se dicen *equipotentes*, denotado $A\sim B$, si $\exists f: A\rightarrow B$ tal que $f$ sea una aplicación biyectiva. 

Verifican las siguientes propiedades:
1. **Reflexiva**: $A\sim A$ para todo conjunto $A$.
2. **Simétrica**: Si $A \sim B \Rightarrow B\sim A$
3. **Transitiva**: Si $A\sim B$ y $B\sim C \Rightarrow A\sim C$

## 1.2 Cardinal de un conjunto
Define el número de elementos de un conjunto. Se denota $|A| = n$. 

Sea $A$ un conjunto, el proceso de asociar a $A$ el valor $n$ es el siguiente:
1. Buscamos un conjunto canónico $C$ tal que $C \sim A$.
2. Usamos un símbolo para denotar el cardinal de $C$, p.e., $\aleph$: $\ |C| = \aleph$.

### 1.2.1 Definición
Decimos que el cardinal de un conjunto $A$ es $\aleph$, escrito $|A| = \aleph$, si $A$ es equipotente al conjunto canónico $C$ cuyo cardinal hemos denotado por $\aleph$.

El cardinal de $\mathbb{N}$ se denota por $\aleph_0$. Por tanto, $A \sim \mathbb{N} \iff |A| = \aleph_0$.

## 1.3 Comparación de cardinales
La ventaja de introducir cardinales es que se pueden realizar operaciones aritméticas con ellas, con lo que son útiles para calcular el número de elementos de conjuntos infinitos.
### 1.3.1 Definición
Dados dos conjunto $A$ y $B$, diremos que:
1. $|A| \leq |B|$ si $\exists f: A\rightarrow B$ tal que $f$ sea una aplicación inyectiva.
2. $|A| < |B|$ si $|A| \leq |B|$ y $|A| \neq |B|$.

Sean $A$, $B$ y $C$ conjuntos, las propiedades de los cardinales son los siguientes:
1. $|A| = |B| \iff A\sim B$
2. $|A| \leq |A|$
3. Si $|A| \leq |B|$ y $|B| \leq |C| \Rightarrow |A| \leq |C|$
4. **Tª Cantor-Bernstein**: Si $|A| \leq |B|$ y $|B| \leq |A| \Rightarrow |A| = |B|$

## 1.4 Conjuntos finitos e infinitos
Un conjunto $A$ es:
1. *finito* si es equipotente a $\{1,2,\dots,n\}$ para algún $n\in \mathbb{N}$, es decir, si $|A| = n$.
2. *infinito* si no es finito, es decir, si $\forall n\in \mathbb{N},\ n < |A|$.

Un conjunto $A$ es infinito si y solo si existe un subconjunto $B \subseteq A$ distinto de $A$ tal que $A \sim B$, es decir, $|A| = |B|$.

# 2. Conjuntos numerables
Un conjunto $A$ es *numerable* si es finito o es equipotente al conjunto de los números naturales.

Un conjunto *infinito numerable* $A$ verifica que $|A| = |\mathbb{N}|$. Para demostrar que un conjunto $A$ es infinito numerable tenemos que encontrar una aplicación biyectiva $f: \mathbb{N} \rightarrow A$. Esto es lo mismo que encontrar una **enumeración** de $A$, es decir, expresar $A$ en la forma $\{a_1, a_2,a_3,\dots\}$. o, lo
que es lo mismo, colocar cada elemento en una posición. 

La ventaja que tiene este método es que podemos visualizar geométricamente el conjunto y buscar nuevas numeraciones. Esto lo vemos en las propiedades de los conjuntos numerables:

**TEOREMA**
>1. Si $A$ es un conjunto numerable y $B\subseteq A$, entonces $B$ es numerable.
>2. Si $A$ y $B$ son numerables, entonces $A \cup B$ es numerable.
>3. Si $\{A_n | n\in \mathbb{N}\}$ es una familia de conjuntos numerables, entonces $\bigcup_{n\in\mathbb{N}} A_n$ es numerable.

Demostración de la segunda propiedad:
![[Pasted image 20240314162249.png]]

La *propiedad 3* es muy útil y suele usarse del siguiente modo: para probar que un conjunto es numerable, intentaremos expresarlo como unión numerable de conjuntos numerables. Las siguientes propiedades se pueden demostrar utilizando esta propiedad:

**TEOREMA**
>1. Si $A$ y $B$ son dos conjuntos no vacíos numerables, entonces $A \times B$ es numerable.
>2. El conjunto $\mathbb{Q}$ es numerable.

# 3. Conjuntos no numerables
**TEOREMA**
>El intervalo $[0,1]$ no es numerable. Por tanto, $|[0,1]| > \aleph_0$.
#### Demostración
Supongamos que $[0,1]$ es numerable. Entonces $[0,1]$ tiene una enumeración, 
$$[0,1] = \{x_1,x_2,x_3,\dots\}.$$
Denotemos la expresión decimal de cada número del siguiente modo:
$$x_n = 0,a_1^{(n)}a_2^{(n)}a_3^{(n)}\dots,$$
y consideremos el número $y$ cuya expresión decimal es $0,b_1b_2b_3\dots$ donde:
- Si $a_n^{(n)} = 0 \Rightarrow b_n = 1$
- Si $a_n^{(n)} \neq 0 \Rightarrow b_n = 0$

El número $y$ así construido es distinto de $x_1,x_2,x_3,\dots$, ya que $y \neq x_n$ ya que $b_n$ es distinto de la cifra $n$ de $x_n$, $a_n^{(n)}$.

En definitiva, esto es una contradicción ya que hemos supuesto que todos los números de $[0, 1]$son $x_1, x_2, \dots$ y, sin embargo, hemos encontrado un número $y$ de $[0, 1]$ que es distinto a todos estos. La conclusión es que $[0, 1]$ no puede ser numerable.

Como consecuencia, tenemos:

### Corolario
El conjunto $\mathbb{R}$ de los números reales no es numerable.
#### Demostración
Se tiene $|\mathbb{R}| \geq |[0,1]| > \aleph_0$.

Sabemos que si $A$ es finito, $\mathcal{P}(A)$, el conjunto formado por todos los subconjuntos de $A$, es finito. No hemos dicho nada de $|\mathcal{P}(N)|$. El cálculo de este cardinal se realiza en el siguiente resultado.

**TEOREMA**
>El conjunto $\mathcal{P}(\mathbb{N})$ no es numerable. Por tanto, $|\mathcal{P}(A)| > \aleph_0$.
#### Demostración
Supongamos que $\mathcal{P}(\mathbb{R})$ es numerable y tomemos una enumeración,
$$\mathcal{P}(N) = \{A_n\ | n\in \mathbb{N}\}.$$ Consideremos el conjunto
$$B = \{n \in \mathbb{N}\ | n \notin A_n\}.$$
Entonces $B$ es distintos a todos los $A_n$ porque:
- Si $n\in A_n \Rightarrow n\notin B\ \ \therefore B\neq A_n$
- Si $n\notin A_n \Rightarrow n\in B\ \ \therefore B\neq A_n$

Esto es una contradicción ya que, por un lado, hemos dicho que todos los subconjuntos de $\mathbb{N}$ son de la forma $A_n$ para algún $n$. Por otro, hemos encontrado un conjunto $B$ que no es de esta forma.

**TEOREMA**
 >Se verifica la igualdad $|\mathbb{R}| = 2^{\aleph_0} = \mathcal{P}(\mathbb{N})$. Por tanto, $\mathbb{R} \sim \mathcal{P}(\mathbb{N})$. 
 
 **TEOREMA**
 > Para cualquier conjunto A, |P(A)| > |A|.
 
 