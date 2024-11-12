sig Planta{
	residentes : set Hombre // pueden vivir 0+ hombres
}

sig Hombre {
	techo : Planta,	// techo: 1 Planta
	suelo : Planta,	// suelo: 1 Planta
	casa : Planta -> Planta,	// (hombre, suSuelo, suTecho)
// Añadir una relación vecinos que relaciona un hombre con todos los vecinos de su planta
	vecinos : set Hombre
}

// Restricciones que deben satisfacer todas las instancias del modelo
fact{
// 1) El techo y el suelo de un hombre tienen que ser plantas diferentes
	all h: Hombre | h.suelo != h.techo
// 2) La casa de un hombre es su suelo y su techo
	all h: Hombre | (h.suelo -> h.techo) = h.casa
// 3) Los residentes de una planta son todos los hombre que tienen el suelo en dicha planta
	all p: Planta | p.residentes.suelo = p
// 4) Los vecinos de cada hombre residen en la misma planta
	some h1, h2: Hombre | h2 in h1.vecinos => h1.suelo = h2.suelo
// 5) Es una relación simétrica

// 6) Un hombre no es vecino de si mismo

}

pred show(){}

run show for 4
