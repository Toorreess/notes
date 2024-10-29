// Se ejecutará una rama según la aletoriedad
active proctype NONDET() {
	if
		:: true -> printf("1\n")
	 	:: true -> printf("2\n")
		:: true -> printf("3\n")
	fi
}

// Como ambas sentecias son true, nunca se ejecuta la rama else
active proctype NONDET2() {
	if
		:: true -> printf("1\n")
		:: true; printf("2\n")
		:: else -> printf("3\n")
	fi
}

// bucle do tamb es no determinista
active proctype NONDET3(){
	do
		:: true -> printf("1\n")
		:: true -> printf("2\n")
		:: true -> printf("3\n")
	od
}

active proctype NONDET4(){
	step0:
	if
		:: printf("1\n"); goto step0;
		:: printf("2\n"); goto step0;
		:: printf("3\n"); goto step0;
	fi
}
