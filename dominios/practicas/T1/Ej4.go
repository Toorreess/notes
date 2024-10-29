package T1

import (
	"fmt"
)

<<<<<<< HEAD
func isDCPO(P []Elem, R []Pair) bool {
	for i := 1; i < (1 << len(P)); i++ {
		var subset []Elem
		for j := 0; j < len(P); j++ {
			if i&(1<<j) > 0 {
				subset = append(subset, P[j])
			}
		}
		if hasUpperBound(subset, P, R) && !hasMinimalUpperBound(subset, P, R) {
=======
// Función para verificar si P con la relación R es un dcpo
func isDCPO(P []Elem, R []Pair) bool {
	// Verificar la completitud: Para cada subconjunto dirigido de P, debe existir un supremo
	subsets := getAllSubsets(P)
	for _, A := range subsets {
		if isDirectedSubset4(A, R) && !hasSupremum(A, P, R) {
			return false
		}
	}

	return true
}

// Función para verificar si A tiene un supremo en P bajo la relación R
func hasSupremum(A []Elem, P []Elem, R []Pair) bool {
	for _, c := range P {
		isSupremum := true
		// c es un supremo si es un límite superior de A
		for _, a := range A {
			if !canReach4(a, c, R) {
				isSupremum = false
				break
			}
		}
		// Verificar que no haya ningún elemento en P más pequeño que también sea un límite superior
		if isSupremum {
			for _, d := range P {
				if d != c && canReach4(c, d, R) && isUpperBound(A, d, R) {
					return false
				}
			}
			return true
		}
	}
	return false
}

// Función para verificar si d es un límite superior de A bajo la relación R
func isUpperBound(A []Elem, d Elem, R []Pair) bool {
	for _, a := range A {
		if !canReach4(a, d, R) {
>>>>>>> 1456fac1a737b4ddc84bc87c47bb6a1533f5597c
			return false
		}
	}
	return true
}

<<<<<<< HEAD
func hasUpperBound(subset []Elem, P []Elem, R []Pair) bool {
	for _, p := range P {
		isUpper := true
		for _, s := range subset {
			if !precedes(s, p, R) {
				isUpper = false
				break
			}
		}
		if isUpper {
			return true
		}
	}
	return false
}

func precedes(s, p Elem, R []Pair) bool {
	for _, pair := range R {
		if pair.fst == s && pair.snd == p {
			return true
		}
	}
	return false
}

func hasMinimalUpperBound(subset []Elem, P []Elem, R []Pair) bool {
	var upperBounds []Elem
	for _, p := range P {
		if hasUpperBound([]Elem{p}, subset, R) {
			upperBounds = append(upperBounds, p)
		}
	}

	if len(upperBounds) == 0 {
		return false
	}

	for _, ub := range upperBounds {
		isMinimal := true
		for _, other := range upperBounds {
			if other != ub && precedes(ub, other, R) {
				isMinimal = false
				break
			}
		}
		if isMinimal {
=======
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
>>>>>>> 1456fac1a737b4ddc84bc87c47bb6a1533f5597c
			return true
		}
	}
	return false
}

func Ej4() {
<<<<<<< HEAD
	P := []Elem{1, 2, 3}
	R := []Pair{
		{1, 2},
		{1, 3},
	}

	if isDCPO(P, R) {
		fmt.Println("El conjunto P es un dcpo.")
	} else {
		fmt.Println("El conjunto P *NO* es un dcpo.")
=======
	// P := []Elem{1, 2}
	// R := []Pair{}

	P := []Elem{1, 2, 3, 4}
	R := []Pair{
		{1, 1},
		{1, 2},
		{1, 3},
		{1, 4},
		{2, 2},
		{2, 4},
		{3, 3},
		{3, 4},
		{4, 4},
	}

	// P := []Elem{1, 2}
	// R := []Pair{
	// 	{1, 1},
	// 	{2, 2},
	// }

	if !isDCPO(P, R) {
		fmt.Printf("%v es un dcpo bajo la relación %v\n", P, R)
	} else {
		fmt.Printf("%v no es un dcpo bajo la relación %v\n", P, R)
>>>>>>> 1456fac1a737b4ddc84bc87c47bb6a1533f5597c
	}
}
