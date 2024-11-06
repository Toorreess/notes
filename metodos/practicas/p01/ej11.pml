#define BAJA 0
#define SUBE 1
#define ABRE 0
#define CIERRA 1

chan ctrACabina = [0] of {bit};     // El control se comunica con la cabina
chan ctrAPuerta[3] = [0] of {bit};  // El control se comunica con cada puerta

init {
    atomic{
        run Controlador();
        run Cabina();
        run Puerta(0);
        run Puerta(1);
        run Puerta(2);
    }
}

proctype Cabina(){
    P0:
    printf("Cabina en Planta 0.\n");
        if
        :: ctrACabina?SUBE -> goto P1;
        :: ctrACabina?BAJA -> goto P0;
        fi

    P1:
    printf("Cabina en Planta 1.\n");
        if
        :: ctrACabina?SUBE -> goto P2;
        :: ctrACabina?BAJA -> goto P0;
        fi

    P2:
    printf("Cabina en Planta 2.\n");
        if
        :: ctrACabina?SUBE -> goto P2;
        :: ctrACabina?BAJA -> goto P1;
        fi
}

proctype Puerta(int n){
    cerrada:
        if
        :: ctrAPuerta[n]?ABRE -> goto abierta;
        :: ctrAPuerta[n]?CIERRA -> goto cerrada;
        fi

    abierta:
        if
        :: ctrAPuerta[n]?ABRE -> goto abierta;
        :: ctrAPuerta[n]?CIERRA -> goto cerrada;
        fi
}

proctype Controlador(){
    ocupada0:
        if
        :: ctrAPuerta[0]!ABRE -> goto libre0;
        :: ctrACabina!SUBE -> goto ocupada1;
        :: ctrACabina!SUBE -> goto _0a2;
        fi

    ocupada1:
        if
        :: ctrAPuerta[1]!ABRE -> goto libre1;
        :: ctrACabina!SUBE -> goto ocupada2;
        :: ctrACabina!BAJA -> goto ocupada0;
        fi

    ocupada2:
        if
        :: ctrAPuerta[2]!ABRE -> goto libre2;
        :: ctrACabina!BAJA -> goto ocupada1;
        :: ctrACabina!BAJA -> goto _2a0;
        fi

    libre0:
        ctrAPuerta[0]!CIERRA; goto ocupada0;
    libre1:
        ctrAPuerta[1]!CIERRA; goto ocupada1;
    libre2: 
        ctrAPuerta[2]!CIERRA; goto ocupada2;


    _0a2:
        ctrACabina!SUBE; goto ocupada2;

    _2a0: 
        ctrACabina!BAJA -> goto ocupada0;
}

// En ningún momento, dos de las tres puertas del ascensor están simultáneamente abiertas.
ltl p1 {[] !( (Puerta[3]@abierta && Puerta[4]@abierta) || (Puerta[3]@abierta && Puerta[5]@abierta) || (Puerta[4]@abierta && Puerta[5]@abierta) ) }

// No es posible que la puerta 0 esté abierta, y que la cabina no esté en la planta 0.
ltl p2 { []!( Puerta[3]@abierta && !(Cabina[2]@P0)) }

// Siempre es cierto que en el futuro, la cabina estará en la planta 0
ltl p3 { ([]<>Controlador[1]@ocupada0) -> ([]<>Cabina[2]@P0) }
