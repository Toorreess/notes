package T1

import "fmt"

func canReach(x Elem, y Elem, R []Pair) bool {
	if x == y {
		return true
	}

	relMap := make(map[Elem][]Elem)
	for _, pair := range R {
		relMap[pair.fst] = append(relMap[pair.fst], pair.snd)
	}

	visited := make(map[Elem]bool)
	return dfs(x, y, relMap, visited)
}

func dfs(current, target Elem, relMap map[Elem][]Elem, visited map[Elem]bool) bool {
	if visited[current] {
		return false
	}

	visited[current] = true

	if current == target {
		return true
	}

	for _, neighbor := range relMap[current] {
		if dfs(neighbor, target, relMap, visited) {
			return true
		}
	}

	return false
}

func isDirectedSubset(A []Elem, R []Pair) bool {
	var check bool
	for i := 0; i < len(A); i++ {
		a := A[i]
		for j := i + 1; j < len(A); j++ {
			b := A[j]
			check = false

			for _, c := range A {
				if canReach(a, c, R) && canReach(b, c, R) {
					check = true
					break
				}
			}
			if !check {
				return false
			}
		}
	}
	return true
}

func Ej3() {
	// P := []Elem{1, 2, 3, 4}
	// A := []Elem{2, 3, 4}
	// R := []Pair{
	// 	{1, 2},
	// 	{1, 3},
	// 	{2, 4},
	// 	{3, 4},
	// }

	P := []Elem{1, 2, 3, 4}
	A := []Elem{2, 3, 4}
	R := []Pair{
		{1, 2},
		{2, 4},
		{1, 3},
	}

	if isDirectedSubset(A, R) {
		fmt.Printf("%v es un subconjunto dirigido de %v con la relación %v\n", A, P, R)
	} else {
		fmt.Printf("%v no es un subconjunto dirigido de %v con la relación %v\n", A, P, R)
	}
}
