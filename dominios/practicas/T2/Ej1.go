package T2

import "fmt"

func binomialCoefficient(N, M int) int {
	M = M + N - 1

	result := 1
	for i := 0; i < N; i++ {
		result *= (M - i)
		result /= (i + 1)
	}

	return result
}

func Ej1() {
	var N, M int

	fmt.Print("Ingrese el valor de N: ")
	fmt.Scan(&N)
	fmt.Print("Ingrese el valor de M: ")
	fmt.Scan(&M)
	fmt.Printf("El numero de funciones crecientes de P%d en P%d es: %d\n", N, M, binomialCoefficient(N, M))
}
