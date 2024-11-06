package T2

import "fmt"

type Elem string

type Relation struct {
	From, To Elem
}

func IsMonotoneIncreasing(relations []Relation, f map[Elem]Elem) bool {
	for _, rel := range relations {
		if f[rel.From] > f[rel.To] {
			return false
		}
	}
	return true
}

// GenerateMonotoneFunctions genera todas las posibles funciones monótonas crecientes en el mismo poset
func GenerateMonotoneFunctions(elem []Elem, relations []Relation) []map[Elem]Elem {
	var results []map[Elem]Elem
	currentMapping := make(map[Elem]Elem)

	// Iniciar la generación de funciones recursivamente
	generateMappingsHelper(elem, relations, currentMapping, 0, &results)
	return results
}

// Función recursiva para generar todas las asignaciones posibles
func generateMappingsHelper(elem []Elem, relations []Relation, currentMapping map[Elem]Elem, index int, results *[]map[Elem]Elem) {
	// Si todos los elementos están asignados, verifica la monotonía y agrega si es monótona creciente
	if index == len(elem) {
		// Crear una copia del mapeo actual para almacenar en resultados
		mappingCopy := make(map[Elem]Elem)
		for k, v := range currentMapping {
			mappingCopy[k] = v
		}
		if IsMonotoneIncreasing(relations, mappingCopy) {
			*results = append(*results, mappingCopy)
		}
		return
	}

	// Asignar el valor del elemento actual en `elem`
	x := elem[index]
	for _, y := range elem {
		currentMapping[x] = y
		generateMappingsHelper(elem, relations, currentMapping, index+1, results)
	}
}

func Ej2() {
	var opt string
	var elems []Elem
	var relations []Relation

	fmt.Println("Ejercicio 2: Generar funciones monótonas.\n Ingrese el diagrama de Hasse:")
	fmt.Scan(&opt)

	if opt == "a" {
		elems = []Elem{"a", "b", "c"}
		relations = []Relation{
			{"a", "a"},
			{"b", "b"},
			{"c", "c"},
			{"a", "b"},
			{"a", "c"},
		}
	} else {
		elems = []Elem{"a", "b", "c", "d"}
		relations = []Relation{
			{"a", "a"},
			{"b", "b"},
			{"c", "c"},
			{"d", "d"},
			{"a", "b"},
			{"a", "c"},
			{"a", "d"},
			{"b", "d"},
			{"c", "d"},
		}
	}

	monotoneFunctions := GenerateMonotoneFunctions(elems, relations)
	fmt.Println("Funciones monótonas generadas:")
	for _, f := range monotoneFunctions {
		fmt.Println(f)
	}
	fmt.Printf("Longitud total: %d\n", len(monotoneFunctions))
}
