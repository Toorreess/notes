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
		:: n == 0 && tabacoMesa?1 -> goto fumando;
		:: n == 1 && papelMesa?1 -> goto fumando;
		:: n == 2 && cerillasMesa?1 -> goto fumando;
		fi

	fumando:
		printf("Fumador %d fumando.\n", n);
	terminado:
		if
		:: n == 0 -> tabacoMesa!0;
		:: n == 1 -> papelMesa!0;
		:: n == 2 -> cerillasMesa!0;
		fi
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
	inicio:
		agenteMesa?fumador;

	mesaLlena:
		if
		:: fumador == 0 -> tabacoMesa!1;
		:: fumador == 1 -> papelMesa!1;
		:: fumador == 2 -> cerillasMesa!1;
		fi

	mesaVacia:
		if
		:: fumador == 0 -> tabacoMesa?0;
		:: fumador == 1 -> papelMesa?0;
		:: fumador == 2 -> cerillasMesa?0;
		fi
	
	fumador = 0;
	agenteMesa!0;
	goto inicio;
}

ltl p1 { []<> Mesa@mesaLlena }
ltl p2 { []<> (Mesa@mesaVacia && (Fumador[3]@fumando ||Fumador[4]@fumando || Fumador[5]@fumando) ) }
ltl p3 { []<> (Mesa@mesaVacia && Mesa@fumador == 2)}

ltl p4 { ([]<> Agente@agenteCerilla && []<> Agente@agenteTabaco && []<> Agente@agentePapel) -> []<>(Mesa@mesaLlena && Mesa:fumador == 3)}
