mtype = {puro, leche}
chan chocolateChan  = [1] of {mtype}
chan dineroChan = [1] of {int}

int initialLeche = 10;
int initialPuro = 5;

int numLeche = 10;
int numPuro = 5;

int caja = 0;
int MAX_MONEY = 1500;

active proctype maquina(){
    int dinero;
    mtype tipo;

    do
    :: dineroChan ? dinero
        if
        :: (dinero == 75 && numLeche > 0) -> chocolateChan ! leche; atomic{numLeche--; caja = caja + dinero;};
        :: (dinero == 100 && numPuro > 0) -> chocolateChan ! puro; atomic{numPuro--; caja = caja + dinero;};
        :: (dinero == 75 && numLeche == 0) -> chocolateChan ! dinero;
        :: (dinero == 100 && numPuro == 0) -> chocolateChan ! dinero;
        :: (numLeche == 0 && numPuro == 0) -> chocolateChan ! dinero;
        fi
    od
}

active proctype cliente(){
    int dinero = MAX_MONEY;
    mtype tipo;
    do
    :: dinero > 0 -> 
        dinero = 75; dineroChan ! dinero; chocolateChan ? tipo
        if
        :: (tipo == leche) -> printf("Recibido chocolate con leche.\n"); MAX_MONEY - dinero;
        :: (tipo == dinero) -> printf("No hay chocolate con leche.\n");
        fi
    :: dinero > 0 -> 
        dinero = 100; dineroChan ! dinero;  chocolateChan ? tipo ->
        if
        :: (tipo == puro) -> printf("Recibido chocolate puro.\n"); MAX_MONEY - dinero;
        :: (tipo == dinero) -> printf("No hay chocolate puro.\n");
        fi
    od
}

ltl p1{[](caja == (initialLeche - numLeche) * 75 + (initialPuro - numPuro) * 100)};
ltl p2{[](caja <= MAX_MONEY)};
