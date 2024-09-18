package main

type Pair struct {
	First  any
	Second any
}

func isReflexive(p Pair, R []Pair) bool {
	for _, r := range R {
		if p == r {
			return true
		}
	}
	return false
}

func isTransitive(p Pair, R []Pair) {

}

func isAntisymmetric(p Pair, R []Pair) bool {
	firstCheck := false
	secondCheck := false

	pAux := Pair{
		First:  p.Second,
		Second: p.First,
	}

	for _, r := range R {
		if p == r {
			firstCheck = true
		}
		if pAux == r {
			secondCheck = true
		}
		if firstCheck && secondCheck {
			return true
		}
	}
	return false
}

func main() {
	// Conjunto P de números o caracteres
	// Relación R, dado mediante parejas de elementos
}
