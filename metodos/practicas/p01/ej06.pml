int turn;
bool f0 = false;
bool f1 = false;
int procInCS = 0;
active proctype process0(){
	do
	:: true ->
		printf("P0 non-critical section.\n");

		f0 = true;
		turn = 1;

		procInCS = procInCS+1;

		(turn != 1 && !f1)
		printf("P0 critical section.\n");
		assert(procInCS == 1);
		procInCS = procInCS-1;

		f0 = false;
	od
}

active proctype process1(){
	do
	:: true ->
		printf("P1 non-critical section.\n");

		f1 = true;
		turn = 0;
		procInCS = procInCS+1;
		(f0 && turn==0)
		printf("P1 critical section.\n");
		procInCS = procInCS-1;

		f1 = false;
	od
}


ltl mutex1{[] procInCS<=1}
