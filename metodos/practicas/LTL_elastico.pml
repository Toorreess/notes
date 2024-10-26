bool estirado = false;
bool roto = false;

active proctype elastico(){
	do
	:: !estirado && !roto -> estirado = true;
	:: estirado && !roto -> estirado = false;
	:: estirado && !roto -> roto = true;
	:: estirado && roto -> skip;
	od
}

ltl p1{<> estirado}
ltl p2{[] !roto}
ltl p3{[] (!roto -> estirado)}
ltl p4{!<>[] estirado}
