/*
 * driver.c
 *
 *  Created on: 9 apr. 2021
 *      Author: PSC
 */
#include <stdio.h>
#include "nfv.h"


int main(){
	int r;
	LFramework infraUMA;
	setvbuf(stdout, NULL,_IONBF,0);

	init(&infraUMA, INFRA_NODES, INFRA_MAX_CPU);

	printf("Initializing and showing the framework:\n");
	showFramework(infraUMA);

	r = deployVNF(infraUMA, "v1",3);
	r? printf("Inserted v1 - CPU=3\n") : printf("Not inserted v1 - CPU=3\n") ;

	r = deployVNF(infraUMA, "v2",5);
	r? printf("Inserted v2 - CPU=5\n") : printf("Not inserted v2 - CPU=5\n");

	r = deployVNF(infraUMA, "v3",1);
	r? printf("Inserted v3 - CPU=1\n") : printf("Not inserted v3 - CPU=1\n");

	r = deployVNF(infraUMA, "v4",2);
	r? printf("Inserted v4 - CPU=2\n") : printf("Not inserted v4 - CPU=2\n");

	r = deployVNF(infraUMA, "v5",6);
	r? printf("Inserted v5 - CPU=6\n") : printf("Not inserted v5 - CPU=6\n");

	r = deployVNF(infraUMA, "v6",4);
	r? printf("Inserted v6 - CPU=4\n") : printf("Not inserted v6 - CPU=4\n");

	printf("Showing after insertions:\n");
	showFramework(infraUMA);
	printf("Freeing the VNF v1 and showing\n");
	releaseVNF(infraUMA, "v1");
	showFramework(infraUMA);

	store(infraUMA,"deployedVNFs.dat");
	printf("VNFs stored into deployedVNFs.dat\n");
	destroyFramework(&infraUMA);
	printf("Destroying the framework and showing\n");
	showFramework(infraUMA);



}
