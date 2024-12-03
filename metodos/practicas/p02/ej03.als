module ejercicio

sig Planta {}

sig Ascensor {
	ascPlanta : one Planta,
	ascPersonas : set Persona,
}

/* a --> b */
// a.r -> b
// r.b -> a / b[r] -> a

sig Edificio {
	edifPlantas : some Planta,
	edifAscensores : set Ascensor,
}

sig Persona {
	edificio : lone Edificio,
	ascensor : lone Ascensor,
}

fact {
	// 1) Cada planta está exactamente en un edificio
	all p : Planta | one edifPlantas.p

	// 2) Cada ascensor está exactamente en un edificio
	all asc : Ascensor | one edifAscensores.asc
	
	// 3) El número de ascensores de un edificio es estrictamente menor que el número de plantas del edificio
	all e : Edificio | #e.edifAscensores < #e.edifPlantas

	// 4) Si un edificio tiene menos de 3 plantas entonces no tiene ascensores
	all e : Edificio | #e.edifPlantas < 3 implies #e.edifAscensores = 0
	
	// 5) Si un edificio tiene más de 3 plantas entonces tiene algún ascensor
	all e : Edificio | #e.edifPlantas >= 3 implies #e.edifAscensores > 0

	// 6) Cada ascensor se encuentra en una planta del edificio al que pertence
	all asc : Ascensor |  asc.ascPlanta in asc[edifAscensores].edifPlantas
	
	// 7) Si una persona está en un ascensor, entonces debe encontrarse también en el edificio en el que está dicho ascensor
	all p : Persona, asc : Ascensor| (p in asc.ascPersonas) implies (p.edificio = asc[edifAscensores])

	// 8) La relación ascPersonas contiene exactamente a las personas que están en el ascensor
	all p : Persona | one p.ascensor implies p in p.ascensor.ascPersonas
}

pred show() {
	// Es posible que una persona esté en un edificio, pero no esté en ningún ascensor
	some p : Persona | #p.edificio = 1 && #p.ascensor = 0	

	// Es posible que un ascensor lleve a dos personas
	some asc : Ascensor | #asc.ascPersonas = 2

	// Es posible que todos los ascensores de un edificio estén en la misma planta
	

	// Es posible que todos los ascensores de un edificio estén en plantas diferentes
	
}

run show
