mtype = {puro, leche}
chan chocolateChan  = [1] of {mtype}
chan dineroChan = [1] of {int}

int numLeche = 10;
int numPuro = 5;

int caja = 0;

active proctype maquina(){
    int dinero;
    mtype tipo;

    do
    :: dineroChan ? dinero
        if
        :: (dinero == 75 && numLeche > 0) -> chocolateChan ! leche; caja = caja + dinero; numLeche--;
        :: (dinero == 100 && numPuro > 0) -> chocolateChan ! puro; caja = caja + dinero; numPuro--;
        :: (dinero == 75 && numLeche == 0) -> chocolateChan ! dinero;
        :: (dinero == 100 && numPuro == 0) -> chocolateChan ! dinero;
        :: (numLeche == 0 && numPuro == 0) -> skip;
        fi
    od
}

active proctype cliente(){
    int dinero;
    mtype tipo;
    do
    :: dinero = 75; dineroChan ! dinero; chocolateChan ? tipo ->
        if
        :: (tipo == leche) -> printf("Recibido chocolate con leche.\n");
        :: (tipo == dinero) -> printf("No hay chocolate con leche.\n");
        fi
    :: dinero = 100; dineroChan ! dinero; chocolateChan ? tipo ->
        if
        :: (tipo == puro) -> printf("Recibido chocolate puro.\n");
        :: (tipo == dinero) -> printf("No hay chocolate puro.\n");
        fi
    od
}
