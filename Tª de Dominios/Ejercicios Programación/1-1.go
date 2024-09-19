package main

import (
	"fmt"
)

type Elem interface{}

type Pair struct {
	a, b Elem
}

// Ejercicio 1
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

func exercise1() {
	P := []Elem{"a", "b", "c", "d"}
	R := []Pair{
		{"a", "a"},
		{"b", "b"},
		{"c", "c"},
		{"a", "b"},
		{"b", "c"},
		{"a", "c"},
	}

	if isPartialOrder(P, R) {
		fmt.Print("La relación es un orden parcial.")
	} else {
		fmt.Print("La relación NO es un orden parcial.")
	}
}

func exercise2() {
	P := []Elem{1, 2, 3, 4}
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

}

func exercise4() {

}
func main() {

}
