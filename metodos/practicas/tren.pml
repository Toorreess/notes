mtype = {far, near, up, down};

chan trainControlChan = [0] of {mtype};
chan controlBarrierChan = [0] of {mtype}; 


active proctype control(){
	AF: 
		trainControlChan?near;
		printf("Alert: a train is coming.\n");
		goto AA;

	AA:
		controlBarrierChan!down;
		goto CA;

	CA:
		trainControlChan?far;
		goto CF;

	CF:
		controlBarrierChan!up;
		goto AF;
		
}

active proctype train(){
	FAR:
	trainControlChan!near;
		printf("Train near.\n");
		goto NEAR;

	NEAR:
		trainControlChan!far;
		printf("Train far.\n");
		goto FAR;
}


active proctype barrier(){
	UP: 
		controlBarrierChan?down;
		printf("Barrier down.\n")
		goto DOWN;

	DOWN: 
		controlBarrierChan?up;
		printf("Barrier up.\n")
		goto UP;
}
