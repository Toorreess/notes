Un **anillo** es un *grupo abeliano* $(A, +)$ con otra operación $\cdot$ que verifica:
- El producto es asociativo: $(a\cdot b) \cdot c = a \cdot (b\cdot c)$
- Se verifica la distributiva del producto respecto a la suma: $(b+c) \cdot a = b \cdot a + c \cdot a$

## Tipos de anillos
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

## Subanillos
Un **subanillo** de un anillo $(R, +, \cdot)$ es un subconjunto $S \subseteq R$ que es un anillo con las operaciones de $R$.

### Teorema
Sea $(R,+,\cdot)$ un anillo y $S$ un subconjunto suyo. $S$ es un subanillo sii verifica:
1. Es cerrado para las operaciones.
2. Respecto a la suma: contiene elemento neutro.
3. Respecto a la suma: si $s \in S \Rightarrow -s \in S$.

#### Ejemplo. $(\mathbb{Z}, +, \cdot)$
$S = \text{múltiplos de 5}$
1. Es cerrado para la suma y para la multiplicación.
2. Contiene al cero: $0 = 0 \cdot 5 \in S$
3. Tiene inverso respecto a la suma: $-z_1 \cdot 5 = (-z_1)\cdot 5$

### Anillo ideal
Un anillo ideal es un subanillo $S$ de $R$ en el que se cumple que:
$$r\in R, s\in S \Rightarrow rs \in S$$
## Homomorfismo de anillos
Dados dos anillos $(R,+,\cdot)$ y $(S, \oplus, \star)$. Un *homomorfismo de anillos* es una aplicación $f: R \rightarrow S$ que respeta las operaciones de los anillos. Verifican:
1. $f(x+y)=f(x) \oplus f(y),\ \forall x,y \in R$ 
2. $f(x\cdot y)=f(x) \star f(y),\ \forall x,y \in R$ 