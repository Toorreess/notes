module prision

sig Banda { miembros : set Interno }
sig Interno { celda : Celda }
sig Celda {}

fact {
	// Un preso no puede pertenecer a más de una banda
	all i : Interno | #i[miembros] <= 1
	
	// Una banda debe tener algún miembro
	all b : Banda | some b.miembros
}

pred asigSegura () {
	// Todas las personas dentro de la misma celda deben pertenecer a la misma banda o no pertenecer a ningun
}

pred asigFeliz () {
	// Todas las personas dentro de la misma celda deben pertenecer a la misma banda
}

pred show () {
}

run show
