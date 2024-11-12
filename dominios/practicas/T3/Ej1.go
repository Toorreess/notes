package T3

import (
	"fmt"
)

type Elem int

type DCPO []map[Elem]Elem

var x Elem = -1

func CartesianProduct(DCPO1, DCPO2 []Elem) DCPO {
	var res DCPO
	for _, e1 := range DCPO1 {
		for _, e2 := range DCPO2 {
			res = append(res, map[Elem]Elem{e1: e2})
		}
	}
	return res
}

func SmashProduct(DCPO1, DCPO2 []Elem) DCPO {
	var res DCPO
	for _, e1 := range DCPO1 {
		if e1 == x {
			continue
		}
		for _, e2 := range DCPO2 {
			if e2 == x {
			}
			res = append(res, map[Elem]Elem{e1: e2})
		}
	}
	res = append(res, map[Elem]Elem{x: x})
	return res
}

func SumaFundida(DCPO1, DCPO2 []Elem) DCPO {
	var res DCPO
	for _, e1 := range DCPO1 {
		if e1 == x {
			continue
		}
		res = append(res, map[Elem]Elem{e1: 0})
	}

	for _, e2 := range DCPO2 {
		if e2 == x {
			continue
		}
		res = append(res, map[Elem]Elem{e2: 1})
	}
	res = append(res, map[Elem]Elem{x: x})
	return res
}

func PrintDCPO(DCPO DCPO) {
	for _, m := range DCPO {
		for k, v := range m {
			fmt.Printf("(%v, %v)\n", k, v)
		}
	}
}

func Ej1() {
	DCPO1 := []Elem{x, 1, 2}
	DCPO2 := []Elem{x, 3, 4}
	fmt.Print("CartesianProduct(DCPO1, DCPO2):\n")
	PrintDCPO(CartesianProduct(DCPO1, DCPO2))
	fmt.Print("\nSmashProduct(DCPO1, DCPO2):\n")
	PrintDCPO(SmashProduct(DCPO1, DCPO2))
	fmt.Print("\nSumaFundida(DCPO1, DCPO2):\n")
	PrintDCPO(SumaFundida(DCPO1, DCPO2))
}
