module ejercicio

/* Signatures */
sig Color {}

sig Objeto{ color : one Color }

sig Caja { objetos: set Objeto }

fact {
	// Cada objeto está en una y solo una caja
	all o : Objeto | one c : Caja | o in c.objetos
}

// En alguna caja todos los objetos sean del mismo color
pred p1 () {
	some c : Caja | all o1, o2 : c.objetos | o1.color = o2.color
}

// alguna caja tenga mas de dos objetos, y todos los objetos que contienen son de distinto color
pred p2 () {
	some c : Caja | #c.objetos > 2 and all disj o1, o2 : c.objetos | o1.color != o2.color
}

pred show () {}

run p2
