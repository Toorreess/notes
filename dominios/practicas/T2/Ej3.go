package T2

import (
	"fmt"
)

type Poset struct {
	elements []int
	order    map[int][]int // order[x] = {y | x <= y}
}

func (p *Poset) IsContinuous(f func(int) int) bool {
	for _, x := range p.elements {
		for _, y := range p.order[x] {
			if f(x) > f(y) {
				return false
			}
		}
	}
	return true
}

func (p *Poset) AllContinuousFunctions() []func(int) int {
	var continuousFunctions []func(int) int

	n := len(p.elements)
	for i := 0; i < (1 << n); i++ {
		f := func(x int) int {
			return i & (1 << x)
		}
		if p.IsContinuous(f) {
			continuousFunctions = append(continuousFunctions, f)
		}
	}
	return continuousFunctions
}

func Ej3() {
	poset := Poset{
		elements: []int{0, 1, 2, 3},
		order: map[int][]int{
			0: {0, 1, 2, 3},
			1: {1, 3},
			2: {2, 3},
			3: {3},
		},
	}

	functions := poset.AllContinuousFunctions()

	for i, f := range functions {
		fmt.Printf("f%d(x) = ", i)
		for _, x := range poset.elements {
			fmt.Printf("%d ", f(x))
		}
		fmt.Println()
	}

	fmt.Printf("Número de funciones continuas: %d\n", len(functions))
}
