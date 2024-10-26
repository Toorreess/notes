mtype = {REQ, req, IND, RSP, rsp, CNF, ABO, abo, aok};
chan A_N1 = [0] of {mtype};
chan N1_N2 = [0] of {mtype};
chan N2_B = [0] of {mtype};

active proctype A(){
	do
	:: A_N1!REQ ->
		if
		:: A_N1?CNF -> skip;
		fi
	od
}

active proctype N1(){
	do
	:: A_N1?REQ-> N1_N2!req;
	:: N1_N2?abo -> N1_N2!req;
		if
		:: N1_N2?rsp -> A_N1!CNF;
		fi
	od
}

active proctype N2(){
	do
	:: N1_N2?req ->
		if
		:: N1_N2!abo;
		:: N2_B!IND;
		:: N2_B?RSP -> N1_N2!rsp;
		fi
	:: N2_B?RSP -> N1_N2!rsp;
	od
}

active proctype B(){
	do
	:: N2_B?IND -> N2_B!RSP;
	od
}
