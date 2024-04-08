/*
 * nfv.c
 *
 *  Created on: 24 mar. 2021
 *      Author: Laura
 *      Translated by: Gálvez
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "nfv.h"


/* (1.5 pts) Initializes the framework with 'size' nodes and each node with
   'initCpu' CPU available
*/
void init(LFramework *ptrFrame,int size, int initCpu){
	LFramework aux =*ptrFrame;
	*ptrFrame = NULL;
		while(size>0){
			aux = *ptrFrame;
			*ptrFrame = (LFramework)malloc(sizeof(struct NodeFramework));
			if(*ptrFrame == NULL){
				perror("Initialization error: some nodes have not been created\n");
				*ptrFrame = aux;
				break;
			}
			(*ptrFrame)->availCpu = initCpu;
			(*ptrFrame)->vnfs = NULL;
			(*ptrFrame)->next = aux;
			size--;
		}
}

/* (2 pts) Deploys (inserts) a VNF in the first node with enough CPU available.
   The just inserted VNF will be the first in the list of VNFs of such a node.
   The available CPU must be updated.
   Returns 1 if VNF has been inserted correctly.
   Returns 0 if no node has enough CPU for the VNF.
*/
int deployVNF(LFramework frame, char *vnfId, int cpu){
	LFramework aux = frame;
	LVnf auxV;
	int r = 0;
	while(aux!=NULL && aux->availCpu<cpu)
		aux = aux->next;
	if(aux!=NULL){
		aux->availCpu-= cpu; // Decrease the CPU required by the VNF
		auxV = aux->vnfs;
		aux->vnfs = (LVnf)malloc(sizeof(struct NodeVnf));
		if(aux->vnfs== NULL){
			perror("Error while allocating in memory the new VNF\n");
		}else{
			aux->vnfs->next = auxV;
			aux->vnfs->cpu = cpu;
			strcpy(aux->vnfs->id, vnfId);
			r =1;
		}
	}
	return r;
}

/* (1 pt) Displays on console the nodes of the framework. For each node must be shown:
 * - Available CPU
 * - The names of the VNFs deployed
*/
void showFramework(LFramework frame){
	LVnf auxV;
	int i=0;
	if(frame == NULL)
		printf("Framework is empty\n");
	while(frame!=NULL){
		printf("Framework node %d CPU avail %d. VNFS are: ",i, frame->availCpu);
		auxV = frame->vnfs;
		while(auxV!=NULL){
			printf("(%s, %d)\t", auxV->id, auxV->cpu);
			auxV = auxV->next;
		}
		printf("\n");
		frame = frame->next;
		i++;
	}
}

/* (2 pts) Searches the VNF with the given id and removes it from the node containing it.
 * The available CPU must be updated.
*/
void releaseVNF(LFramework frame, char *vnfId){
	LVnf currV, antV;

	while(frame!=NULL){
		currV= frame->vnfs;
		antV = NULL;
		while(currV!=NULL && strcmp(currV->id, vnfId)!=0){
			antV = currV;
			currV = currV->next;
		}
		if(currV!=NULL)
			break;
		frame = frame->next;
	}
	if(currV!=NULL){
		frame->availCpu+= currV->cpu;
		if(antV==NULL)
			frame->vnfs = currV->next;
		else
			antV->next = currV->next;
		free(currV);
	}
}

/* (1.5 pts) Frees all the memory of the framework, including the deployed VNFs
*/
void destroyFramework(LFramework *infra){
	LFramework currI;
	LVnf currV;
	while(*infra!=NULL){
		while((*infra)->vnfs!=NULL){
			currV = (*infra)->vnfs;
			(*infra)->vnfs = (*infra)->vnfs->next;
			free(currV);
		}
		currI = *infra;
		*infra = (*infra)->next;
		free(currI);
	}
}


/* (2 pts) Saves into a binary file nly the information of all the VNFs deployed
   in the framework. For each VNF the file should contain:
   <length of the id><id><cpu> */
void store(LFramework frame, char *filename)
{
	FILE *f;
	LVnf currV;
	int len;
	if((f=fopen(filename, "wb"))==NULL){
		perror("Error opening the file");
		exit(-1);
	}
	while(frame!=NULL){
		currV = frame->vnfs;
		while(currV!=NULL){
			len = strlen(currV->id);
			fwrite(&len, sizeof(int), 1, f);
			fwrite(currV->id, len, 1,f);
			fwrite(&(currV->cpu), sizeof(int),1,f);
			currV = currV->next;
		}
		frame = frame->next;
	}

}
