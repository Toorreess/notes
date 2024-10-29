chan enter[2] = [0] of {int};
chan release = [0] of {int};

init {
	atomic{
		run arbitro();
		run p(0);
		run p(1);
	}
}

proctype p(int i){
	inicio: enter[i]?_;
	critical: printf("Proc %d enters critical section\n", i);
	release!1;
	printf("Proc %d leaves critical section\n", i);
	goto inicio;
}

proctype arbitro(){
	inicio:
		if
		:: enter[0]!1;
		cara: printf("Cara\n");
		:: enter[1]!1;
		cruz: printf("Cruz\n");
		fi
		release?_;
		goto inicio;
}

ltl p2_justicia{ ( ([]<> arbitro@cara) && ([]<>arbitro@cruz) ) -> ([]<> (p[3]@critical) ) }
