package T1

import (
	"fmt"
)

func isDCPO(P []Elem, R []Pair) bool {
	for i := 1; i < (1 << len(P)); i++ {
		var subset []Elem
		for j := 0; j < len(P); j++ {
			if i&(1<<j) > 0 {
				subset = append(subset, P[j])
			}
		}
		if hasUpperBound(subset, P, R) && !hasMinimalUpperBound(subset, P, R) {
			return false
		}
	}
	return true
}

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
			return true
		}
	}
	return false
}

func Ej4() {
	P := []Elem{1, 2, 3}
	R := []Pair{
		{1, 2},
		{1, 3},
	}

	if isDCPO(P, R) {
		fmt.Println("El conjunto P es un dcpo.")
	} else {
		fmt.Println("El conjunto P *NO* es un dcpo.")
	}
}
