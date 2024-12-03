module ejercicio

sig Llave {}

sig Cliente {
	llave: lone Llave
}

sig Hotel { 
	hab: set Habitacion 
}

sig Habitacion{
	llaves: set Llave,
	llaveActual: one Llave,  
	ocupacion: lone Cliente
}

fact {
	// 1) Todas las habitaciones pertenecen a exactamente un hotel
	all hb: Habitacion | one hab.hb
	
	// 2) Las llaves de las habitaciones son disjuntas (ningun par de habitaciones comparten llaves)
	all disj h1, h2 : Habitacion | h1.llaves != h2.llaves
	// all disj h1, h2:Habitacion | no (h1.llaves & h2.llaves)
	
	// 3) Todas las habitaciones tienen al menos una llave asociada
	all hb : Habitacion | #hb.llaves > 1
	// all hb: Habitacion | some hb.llaves

	// 4) La llave actual de cada habitacion es una de sus llaves
	all hb : Habitacion | hb.llaveActual in hb.llaves

	// 5) Cada cliente ocupa a lo sumo una habitacion
	all c : Cliente | lone ocupacion.c

	// 6) Si un cliente ocupa una habitacion, entonces su llave es la llave actual de la habitacion
	all c : Habitacion.ocupacion | c.llave = (ocupacion.c).llaveActual
	
	// 7) Si un cliente no ocupa una habitacion, entonces no tiene ninguna llave asociada
	all c : Cliente | no ocupacion.c implies no c.llave
}

pred show() {}

run show
