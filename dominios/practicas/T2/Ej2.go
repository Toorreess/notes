package T2

import (
	"fmt"
)

type Tuple struct {
	DomainElement   int
	CodomainElement int
}

func findMonotoneFunctionsA() [][]Tuple {
	var functions [][]Tuple
	elems := []int{1, 2, 3}

	relations := map[int][]int{
		1: {1, 2, 3},
		2: {2},
		3: {3},
	}

	for _, fA := range elems {
		for _, fB := range relations[fA] {
			for _, fC := range relations[fA] {
				function := []Tuple{
					{1, fA},
					{2, fB},
					{3, fC},
				}
				functions = append(functions, function)
			}
		}
	}
	return functions
}

func findMonotoneFunctionsB() [][]Tuple {
	var functions [][]Tuple
	// elems := []string{"a", "b", "c", "d"}
	// relations := map[string][]string{
	// 	"a": {"a", "b", "c", "d"},
	// 	"b": {"b", "d"},
	// 	"c": {"c", "d"},
	// 	"d": {"d"},
	// }

	elems := []int{1, 2, 3, 4}
	relations := map[int][]int{
		1: {1, 2, 3, 4},
		2: {2, 4},
		3: {3, 4},
		4: {4},
	}

	for _, fA := range elems {
		for _, fB := range relations[fA] {
			for _, fC := range relations[fA] {
				for _, fD := range relations[fB] {
					if contains(relations[fC], fD) {
						function := []Tuple{
							{1, fA},
							{2, fB},
							{3, fC},
							{4, fD},
						}
						functions = append(functions, function)
					}
				}
			}
		}
	}
	return functions
}

func contains(list []int, elem int) bool {
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
	// fmt.Println("Diagrama apartado a):")
	functionsA := findMonotoneFunctionsA()
	printFunctions(functionsA)

	// fmt.Println("Diagrama apartado b):")
	functionsB := findMonotoneFunctionsB()
	printFunctions(functionsB)
}
