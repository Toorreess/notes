chan papelMesa = [0] of { bit };
chan tabacoMesa = [0] of { bit };
chan cerillasMesa = [0] of { bit };
chan agenteMesa = [0] of { short };

init{
	atomic{
		run Agente();
		run Mesa();
		run Fumador(0);
		run Fumador(1);
		run Fumador(2);
	}
}

proctype Fumador(int n){
	inicio:
		if
		:: n == 0 && tabacoMesa?_ -> goto fumando;
		:: n == 1 && papelMesa?_ -> goto fumando;
		:: n == 2 && cerillasMesa?_ -> goto fumando;
		fi

	fumando:
		printf("Fumador %d fumando.\n", n);
	terminado:
		if
		:: n == 0 -> tabacoMesa!0;
		:: n == 1 -> papelMesa!0;
		:: n == 2 -> cerillasMesa!0;
		goto inicio;
}

proctype Agente(){
	inicio:
	if
	:: agenteMesa!1;
	agenteTabaco: skip;
	:: agenteMesa!2;
	agentePapel: skip;
	:: agenteMesa!3;
	agenteCerilla: skip;
	fi
	agenteMesa?0;
	goto inicio;
}

proctype Mesa(){
	short fumador;
	mesaLlena:
		if
		::
		fi
}
