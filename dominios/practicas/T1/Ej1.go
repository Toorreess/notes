package T1

import "fmt"

type Elem int

type Pair struct {
	fst, snd Elem
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
	for _, r0 := range R {
		if r0.fst == r0.snd {
			continue
		}
		for _, r1 := range R {
			if r0.snd != r1.fst {
				continue
			}
			check := false
			for _, r2 := range R {
				if r0.fst == r2.fst && r1.snd == r2.snd {
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
	for _, r0 := range R {
		if r0.fst == r0.snd {
			continue
		}
		for _, r1 := range R {
			if r0.fst == r1.snd && r0.snd == r1.fst {
				return false
			}
		}
	}
	return true
}

func isPartialOrder(P []Elem, R []Pair) bool {
	return isReflexive(P, R) && isAntisymmetric(R) && isTransitive(R)
}

func Ej1() {
	P := []Elem{1, 2, 3}
	Rtest := [][]Pair{
		{{1, 1}, {2, 2}, {3, 3}},
		{{1, 1}, {2, 2}, {3, 3}, {1, 2}},
		{{1, 1}, {2, 2}, {3, 3}, {1, 2}, {2, 3}},
		{{1, 1}, {2, 2}, {3, 3}, {1, 2}, {2, 3}, {1, 3}},
		{{1, 1}, {3, 3}, {1, 2}, {2, 3}, {1, 3}},
	}

	for _, R := range Rtest {
		if isPartialOrder(P, R) {
			fmt.Printf("%v es un orden parcial\n", R)
		} else {
			fmt.Printf("%v no es un orden parcial\n", R)
		}
	}
}
