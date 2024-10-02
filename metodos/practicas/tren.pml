mtype trainStates = {far, near}
mtype barrierStates = {up, down}

chan trainControlChan = [0] of {trainStates};
chan controlBarrierChan = [0] of {controlStates}; 


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
	far:
	trainControlChan!near;
		printf("Train near.\n");
		goto near;

	near:
		trainControlChan!far;
		printf("Train far.\n");
		goto far;
}


active proctype barrier(){
	up: 
		controlBarrierChan?down;
		printf("Barrier down.\n")
		goto down;

	down: 
		controlBarrierChan?up;
		printf("Barrier up.\n")
		goto up;
}
