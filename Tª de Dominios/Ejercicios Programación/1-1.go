package main

type Pair struct {
	First  any
	Second any
}

func isReflexive(P []string, R []interface{}) bool {
	for _, x := range P {
		p := Pair{First: x, Second: x}

	}
	return true
}

func isTransitive()    {}
func isAntiSymmetric() {}
