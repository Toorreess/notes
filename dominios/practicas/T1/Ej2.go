package T1

import "fmt"

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

func printRelations(R [][]Pair) {
	for _, r := range R {
		fmt.Println(r)
	}
}
func Ej2() {
	P := []Elem{1, 2, 3, 4}
	result := generateRelations(P)

	printRelations(result)
}
