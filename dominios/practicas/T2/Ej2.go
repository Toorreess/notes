package T2

import (
	"fmt"
)

type Tuple struct {
	DomainElement   string
	CodomainElement string
}

func findMonotoneFunctionsA() [][]Tuple {
	var functions [][]Tuple
	elems := []string{"a", "b", "c"}

	relations := map[string][]string{
		"a": {"a", "b", "c"},
		"b": {"b"},
		"c": {"c"},
	}

	for _, fA := range elems {
		for _, fB := range relations[fA] {
			for _, fC := range relations[fA] {
				function := []Tuple{
					{"a", fA},
					{"b", fB},
					{"c", fC},
				}
				functions = append(functions, function)
			}
		}
	}
	return functions
}

func findMonotoneFunctionsB() [][]Tuple {
	var functions [][]Tuple
	elems := []string{"a", "b", "c", "d"}

	relations := map[string][]string{
		"a": {"a", "b", "c", "d"},
		"b": {"b", "d"},
		"c": {"c", "d"},
		"d": {"d"},
	}

	for _, fA := range elems {
		for _, fB := range relations[fA] {
			for _, fC := range relations[fA] {
				for _, fD := range relations[fB] {
					if contains(relations[fC], fD) {
						function := []Tuple{
							{"a", fA},
							{"b", fB},
							{"c", fC},
							{"d", fD},
						}
						functions = append(functions, function)
					}
				}
			}
		}
	}
	return functions
}

func contains(list []string, elem string) bool {
	for _, v := range list {
		if v == elem {
			return true
		}
	}
	return false
}

func printFunctions(functions [][]Tuple) {
	fmt.Println("[")
	for i, function := range functions {
		fmt.Print("[")
		for j, tuple := range function {
			fmt.Print(tuple)
			if j < len(function)-1 {
				fmt.Print(", ")
			}
		}
		fmt.Print("]")
		if i < len(functions)-1 {
			fmt.Println(", ")
		}
	}
	fmt.Println("\n]")
}

func Ej2() {
	fmt.Println("Diagrama apartado a):")
	functionsA := findMonotoneFunctionsA()
	fmt.Printf("Número de funciones generadas: %d\n", len(functionsA))
	printFunctions(functionsA)

	fmt.Println("Diagrama apartado b):")
	functionsB := findMonotoneFunctionsB()
	fmt.Printf("Número de funciones generadas: %d\n", len(functionsB))
	printFunctions(functionsB)
}
