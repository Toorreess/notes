chan enter[2] = [0] of {int};
chan release = [0] of {int};

init {
	atomic{
		run Arbitro();
		run Proceso(0);
		run Proceso(1);
	}
}

proctype Proceso(int i){
	inicio:
		enter[i]?_;
	critical:
		printf("Proc %d enters critical section\n", i);

	release!1;
	printf("Proc %d leaves critical section\n", i);
	goto inicio;
}

proctype Arbitro(){
	inicio:
		if
		:: enter[0]!1; cara: printf("Cara\n");
		:: enter[1]!1; cruz: printf("Cruz\n");
		fi

		release?_;
		goto inicio;
}

ltl p1 { []<> (Proceso[3]@critical) }
ltl p2{ ( ([]<> Arbitro@cara) && ([]<>Arbitro@cruz) ) -> ([]<> (Proceso[3]@critical) ) }
