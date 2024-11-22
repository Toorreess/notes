module ejercicio

sig Llave {}

sig Cliente { llave: lone Llave }

sig Habitacion{ llaves: set Llave, llaveActual: one Llave,  ocupacion: lone Cliente }

sig Hotel { hab: some Habitacion }

fact {
	// Todas las habitaciones pertenecen a exactamente un hotel
	all hb: Habitacion | one h : Hotel | hb in h.hab
	
	// Las llaves de las habitaciones son disjuntas (ningun par de habitaciones comparten llaves)
	all disj h1, h2 : Habitacion | h1.llaves != h2.llaves
	
	// Todas las habitaciones tienen al menos una llave asociada
	all hb : Habitacion | #hb.llaves > 1
	
	// La llave actual de cada habitacion es una de sus llaves
	all hb : Habitacion | hb.llaveActual in hb.llaves

	// Cada cliente ocupa a lo sumo una habitacion

	// Si un cliente ocupa una habitacion, entonces su llave es la llave actual de la habitacion

	
	// Si un cliente no ocupa una habitacion, entonces no tiene ninguna llave asociada
}

pred show() {}

run show
