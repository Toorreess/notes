package T2

import "fmt"

type Elem int

type Relation struct {
	From, To Elem
}

func isLessThan(relations []Relation, a, b Elem) bool {
	for _, r := range relations {
		if r.From == a && r.To == b {
			return true
		}
	}
	return false
}

func generateMonotonicFunctions(elems []Elem, relations []Relation, current []Elem, result *[][]Elem) {
	if len(current) == len(elems) {

		funcCopy := make([]Elem, len(current))
		copy(funcCopy, current)
		*result = append(*result, funcCopy)

		return
	}

	for _, e := range elems {
		if len(current) == 0 || (isLessThan(relations, current[len(current)-1], e)) {
			current = append(current, e)
			generateMonotonicFunctions(elems, relations, current, result)
			current = current[:len(current)-1] // Retroceder
		}
	}
}

func Ej2() {
	elems := []Elem{1, 2, 3}
	relations := []Relation{
		{1, 1},
		{2, 2},
		{3, 3},
		{1, 2},
		{1, 3},
	}

	// elems := []Elem{1, 2, 3, 4}
	// relations := []Relation{
	// 	{1, 1},
	// 	{1, 2},
	// 	{1, 3},
	// 	{1, 4},
	// 	{2, 2},
	// 	{2, 4},
	// 	{3, 3},
	// 	{3, 4},
	// 	{4, 4},
	// }
	var result [][]Elem
	generateMonotonicFunctions(elems, relations, []Elem{}, &result)

	fmt.Println("Funciones monótonas generadas:")
	for _, f := range result {
		fmt.Println(f)
	}
}
