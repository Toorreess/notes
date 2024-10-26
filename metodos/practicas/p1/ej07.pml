mtype = {puro, leche}
chan chocolateChan  = [1] of {mtype}
chan dineroChan = [1] of {int}

active proctype maquina(){
    int dinero;
    mtype tipo;

    do
    :: dineroChan ? dinero
        if
        :: (dinero == 75) -> chocolateChan ! leche;
        :: (dinero == 100) -> chocolateChan ! puro;
        :: else -> skip;
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
        fi
    :: dinero = 100; dineroChan ! dinero; chocolateChan ? tipo ->
        if
        :: (tipo == puro) -> printf("Recibido chocolate puro.\n");
        fi
    od
}
