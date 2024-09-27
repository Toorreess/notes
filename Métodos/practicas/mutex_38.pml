
active proctype p1{
	do
		:: true ->
			printf("SNC 1\n");
			c2 == true;
			c1 = false;
			enSC = enSC+1;

			printf("SECCION CRITICA 1\n");
			assert(enSC == 1);
			enSC = enSC-1;
			c1 = true;

	od
}
