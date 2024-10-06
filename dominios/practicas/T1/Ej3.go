package T1

import "fmt"

func isSubset(P []Elem, A []Elem) bool {
	set := make(map[Elem]bool)
	for _, p := range P {
		set[p] = true
	}
	for _, a := range A {
		if !set[a] {
			return false
		}
	}
	return true
}

func isRelated(x, y Elem, R []Pair) bool {
	for _, r := range R {
		if r.fst == x && r.snd == y {
			return true
		}
	}
	return true
}

func isDirectedSubset(P []Elem, A []Elem, R []Pair) bool {
	if !isSubset(P, A) {
		return false
	}

	var check bool
	for i := 0; i < len(A); i++ {
		a := A[i]
		for j := i + 1; j < len(A); j++ {
			b := A[j]
			check = false

			for _, c := range A {
				if isRelated(a, c, R) && isRelated(b, c, R) {
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
	P := []Elem{1, 2, 3, 4, 5}
	A := []Elem{2, 3, 4}
	R := []Pair{
		{1, 1},
		{2, 2},
		{3, 3},
		{4, 4},
		{5, 5},
	}

	if isDirectedSubset(P, A, R) {
		fmt.Println("A es un subconjunto dirigido de P con la relación R")
	} else {
		fmt.Println("A *NO* es un subconjunto dirigido de P con la relación R")
	}
}
