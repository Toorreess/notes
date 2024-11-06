mtype = {REQ, req, IND, RSP, rsp, CNF, ABO, abo, aok};
chan A_N1 = [0] of {mtype};
chan N1_N2 = [0] of {mtype};
chan N2_B = [0] of {mtype};

proctype A(){
	inicio:
		A_N1!REQ;
		if
		:: A_N1?CNF; skip;
		:: A_N1?ABO; skip;
		fi

		goto inicio;
}

proctype N1(){
	inicio:
		if
		:: A_N1?REQ -> N1_N2!req;
		:: N1_N2?rsp -> A_N1!CNF;
		:: N1_N2?abo ->
			if
			:: A_N1!ABO; N1_N2!aok; goto inicio;
			:: N1_N2!req; goto inicio;
			fi
		fi
		goto inicio;
}

proctype N2(){
	inicio:
	if
	:: N1_N2?req -> {
			if
			:: N2_B!IND;
			:: N1_N2!abo;
			fi
			goto inicio;
		}
	:: N2_B?RSP -> N1_N2!rsp;
	fi
	goto inicio;
}

proctype B(){
	do
	:: N2_B?IND -> N2_B!RSP;
	od
}

init {
	atomic{
		run A();
		run N1();
		run N2();
		run B();
	}
}
