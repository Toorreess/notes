int c1 = 1;
int c2 = 1;
int enSC = 0;

active proctype p1(){
	do
	:: true ->
		printf("SNC 1\n");
		c1 = 0;
		c2 != 0;
		
		enSC = enSC+1;
		printf("SECCION CRITICA 1\n");
		assert(enSC == 1);
		enSC = enSC-1;
		c1 = 1;
	od
}

active proctype p2(){
	do
	:: true ->
		printf("SNC 2\n");
		c2 = 0;
		c1 != 0;
		
		enSC = enSC+1;
		printf("SECCION CRITICA 2\n");
		assert(enSC == 1);
		enSC = enSC-1;
		c2 = 1;
	od
}
