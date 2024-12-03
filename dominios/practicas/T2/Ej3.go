package T2

import (
	"fmt"
	"math"
)

type Elem int

type Pair struct {
	fst, snd Elem
}

// Función para obtener todos los subconjuntos de P
func getAllSubsets(P []Elem) [][]Elem {
	var subsets [][]Elem
	n := len(P)
	for i := 0; i < (1 << n); i++ {
		var subset []Elem
		for j := 0; j < n; j++ {
			if (i & (1 << j)) > 0 {
				subset = append(subset, P[j])
			}
		}
		subsets = append(subsets, subset)
	}
	return subsets
}

func isDirectedSubset4(A []Elem, R []Pair) bool {
	for i := 0; i < len(A); i++ {
		for j := i + 1; j < len(A); j++ {
			a, b := A[i], A[j]
			found := false
			for _, c := range A {
				if canReach4(a, c, R) && canReach4(b, c, R) {
					found = true
					break
				}
			}
			if !found {
				return false
			}
		}
	}

	return true
}

func canReach4(x Elem, y Elem, R []Pair) bool {
	if x == y {
		return true
	}

	relMap := make(map[Elem][]Elem)
	for _, pair := range R {
		relMap[pair.fst] = append(relMap[pair.fst], pair.snd)
	}

	visited := make(map[Elem]bool)
	return dfs4(x, y, relMap, visited)
}

func dfs4(current, target Elem, relMap map[Elem][]Elem, visited map[Elem]bool) bool {
	if visited[current] {
		return false
	}
	visited[current] = true
	if current == target {
		return true
	}
	for _, neighbor := range relMap[current] {
		if dfs4(neighbor, target, relMap, visited) {
			return true
		}
	}
	return false
}

func generateFunctions(P []Elem) [][]Elem {
	n := len(P)
	functions := [][]Elem{}

	// Generar funciones como combinaciones posibles de elementos en P
	total := int(math.Pow(float64(n), float64(n)))
	for i := 0; i < total; i++ {
		f := make([]Elem, n)
		temp := i
		for j := 0; j < n; j++ {
			index := temp % n
			f[j] = P[index]
			temp /= n
		}
		functions = append(functions, f)
	}
	return functions
}

// Función para verificar si una función es continua
func isContinuous(f []Elem, P []Elem, R []Pair) bool {
	subsets := getAllSubsets(P)
	for _, A := range subsets {
		if isDirectedSubset4(A, R) {
			supA := findSupremum(A, P, R)
			imageA := mapImage(A, f, P)
			supImageA := findSupremum(imageA, P, R)
			idx := indexOf(*supA, P)
			if idx == -1 || *supImageA != f[idx] {
				return false
			}
		}
	}
	return true
}

// Función para encontrar el supremo de un conjunto dirigido
func findSupremum(A []Elem, P []Elem, R []Pair) *Elem {
	for _, c := range P {
		isSup := true
		for _, a := range A {
			if !canReach4(a, c, R) {
				isSup = false
				break
			}
		}
		if isSup {
			return &c
		}
	}
	return nil
}

// Mapea los elementos de A usando la función f
func mapImage(A []Elem, f []Elem, P []Elem) []Elem {
	image := []Elem{}
	for _, a := range A {
		image = append(image, f[indexOf(a, P)])
	}
	return image
}

// Encuentra el índice de un elemento en P
func indexOf(e Elem, P []Elem) int {
	for i, p := range P {
		if e == p {
			return i
		}
	}
	return -1
}

// Función principal para calcular funciones continuas
func Ej3() {
	P1 := []Elem{1, 2, 3}
	R1 := []Pair{
		{1, 1}, {1, 2}, {1, 3},
		{2, 2},
		{3, 3},
	}

	P2 := []Elem{1, 2, 3, 4}
	R2 := []Pair{
		{1, 1}, {1, 2}, {1, 3}, {1, 4},
		{2, 2}, {2, 4},
		{3, 3}, {3, 4},
		{4, 4},
	}

	// Ejemplo 1
	functions := generateFunctions(P1)

	for _, f := range functions {
		if isContinuous(f, P1, R1) {
			fmt.Println(f)
		}
	}

	// Ejemplo 2
	functions = generateFunctions(P2)
	for _, f := range functions {
		if isContinuous(f, P2, R2) {
			fmt.Println(f)
		}
	}
}
