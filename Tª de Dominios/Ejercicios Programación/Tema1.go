package main

import (
	"fmt"
)

type Elem string

type Pair struct {
	a, b Elem
}

// For all a in P, aRa in R
func isReflexive(P []Elem, R []Pair) bool {
	for _, p := range P {
		check := false
		pair := Pair{p, p}

		for _, r := range R {
			if pair == r {
				check = true
				break
			}
		}

		if !check {
			return false
		}
	}
	return true
}

// For all a,b,c in P, if aRb and bRc, then aRc
func isTransitive(R []Pair) bool {
	for _, r1 := range R {
		for _, r2 := range R {
			if r1.b != r2.a {
				continue
			}
			check := false
			for _, r3 := range R {
				if r1.a == r3.a && r2.b == r3.b {
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

// For all a,b in P, if aRb and bRa, then a=b
func isAntisymmetric(R []Pair) bool {
	for _, r1 := range R {
		if r1.a == r1.b {
			continue
		}

		for _, r2 := range R {
			if r1.a == r2.b && r1.b == r2.a {
				return false
			}
		}
	}
	return true
}

func isPartialOrder(P []Elem, R []Pair) bool {
	return isReflexive(P, R) && isAntisymmetric(R) && isTransitive(R)
}

// Ejercicio 2
func generateRelations(P []Elem) [][]Pair {
	n := len(P)
	totalPairs := 1 << (n * n)
	relationPowerSet := [][]Pair{}

	for i := 0; i < totalPairs; i++ {
		var relationSet []Pair
		for j := 0; j < n; j++ {
			for k := 0; k < n; k++ {
				if i&(1<<(j*n+k)) != 0 {
					relationSet = append(relationSet, Pair{P[j], P[k]})
				}
			}
		}
		relationPowerSet = append(relationPowerSet, relationSet)

	}
	return filterPartialOrder(P, relationPowerSet)
}

func filterPartialOrder(P []Elem, RGenerated [][]Pair) [][]Pair {
	var partialOrders [][]Pair
	for _, r := range RGenerated {
		if isPartialOrder(P, r) {
			partialOrders = append(partialOrders, r)
		}
	}
	return partialOrders
}

func isRelated(R []Pair, a, b Elem) bool {
	for _, r := range R {
		if r.a == a && r.b == b {
			return true
		}
	}
	return false
}

func contains(A []Elem, elem Elem) bool {
	for _, e := range A {
		if e == elem {
			return true
		}
	}
	return false
}

func isDirectedSet(P []Elem, R []Pair, A []Elem) bool {
	for i := 0; i < len(A); i++ {
		for j := i + 1; j < len(A); j++ {
			found := false
			for _, c := range P {
				if isRelated(R, A[i], c) && isRelated(R, A[j], c) {
					if contains(A, c) {
						found = true
						break
					}

				}
			}
			if !found {
				return false
			}
		}
	}
	return true
}

func exercise1() {

	P := []Elem{"a", "b", "c", "d", "e", "f"}
	R := []Pair{
		{"a", "a"},
		{"b", "b"},
		{"c", "c"},
		{"d", "d"},
		{"e", "e"},
		{"f", "f"},
		{"a", "b"},
		{"a", "c"},
		{"a", "f"},
		{"d", "c"},
		{"d", "e"},
		{"d", "f"},
		{"c", "f"},
		{"e", "f"},
	}

	if isPartialOrder(P, R) {
		fmt.Print("La relación es un orden parcial.")
	} else {
		fmt.Print("La relación NO es un orden parcial.")
	}
}

func exercise2() {
	P := []Elem{"1", "2", "3"}
	relations := generateRelations(P)

	for _, rel := range relations {
		fmt.Print("{ ")
		for _, r := range rel {
			fmt.Printf("{%v, %v} ", r.a, r.b)
		}
		fmt.Print("}\n")
	}
}

func exercise3() {
	P := []Elem{"a", "b", "c", "d", "e", "f"}
	R := []Pair{
		{"a", "a"},
		{"b", "b"},
		{"c", "c"},
		{"d", "d"},
		{"e", "e"},
		{"f", "f"},
		{"a", "b"},
		{"a", "c"},
		{"a", "f"},
		{"d", "c"},
		{"d", "e"},
		{"d", "f"},
		{"c", "f"},
		{"e", "f"},
	}
	A := []Elem{"b", "c", "d", "f"}

	if isDirectedSet(P, R, A) {
		fmt.Print("El conjunto es dirigido.")
	} else {
		fmt.Print("El conjunto NO es dirigido.")
	}
}

func findSupremum(P []Elem, R []Pair, A []Elem) (Elem, bool) {
	for _, p := range P {
		isSupremum := true
		for _, a := range A {
			if !isRelated(R, p, a) {
				isSupremum = false
				break
			}
		}
		if isSupremum {
			return p, true
		}
	}
	return "", false
}

func generateDirectedSubsets(P []Elem, R []Pair) [][]Elem {
	var subsets [][]Elem
	n := len(P)
	for i := 1; i < (1 << n); i++ {
		var subset []Elem
		for j := 0; j < n; j++ {
			if i&(1<<j) != 0 {
				subset = append(subset, P[j])
			}
		}
		if isDirectedSet(P, R, subset) {
			subsets = append(subsets, subset)
		}
	}
	return subsets
}

func isDCPO(P []Elem, R []Pair) bool {
	if !isPartialOrder(P, R) {
		return false
	}

	directedSubsets := generateDirectedSubsets(P, R)
	for _, A := range directedSubsets {
		if _, found := findSupremum(P, R, A); !found {
			return false
		}
	}
	return true
}

func exercise4() {
	P := []Elem{"a", "b", "c"}
	R := []Pair{
		{"a", "b"},
		{"a", "c"},
	}
	if isDCPO(P, R) {
		fmt.Print("El conjunto es un DCPo.")
	} else {
		fmt.Print("El conjunto NO es un DCPo.")
	}
}
func main() {
	exercise1()
}
