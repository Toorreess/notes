/*
 * nfv.h
 *
 *  Created on: 26 mar. 2021
 *      Author: galve
 */

#ifndef NFV_H_
#define NFV_H_

#define INFRA_NODES 5
#define INFRA_MAX_CPU 5

typedef struct NodeVnf *LVnf;

struct NodeVnf { // Stores the data of a VNF
	char id[10]; // Name of the VNF
	int cpu;     // Amount of CPU required by the VNF
	LVnf next;
};

typedef struct NodeFramework *LFramework;
struct NodeFramework { // Stored the data of the node in the framework
	int availCpu;  	   // CPU currently available in the node
	LVnf vnfs;    	   // List of VNFs deployed in this node
	LFramework next;
};


/* (1.5 pts) Initializes the framework with 'size' nodes and each node with
   'initCpu' CPU available
*/
void init(LFramework *ptrFrame,int size, int initCpu);

/* (2 pts) Deploys (inserts) a VNF in the first node with enough CPU available.
   The just inserted VNF will be the first in the list of VNFs of such a node.
   The available CPU must be updated.
   Returns 1 if VNF has been inserted correctly.
   Returns 0 if no node has enough CPU for the VNF.
*/
int deployVNF(LFramework frame, char *vnfId, int cpu);


/* (1 pt) Displays on console the nodes of the framework. For each node must be shown:
 * - Available CPU
 * - The names of the VNFs deployed
*/
void showFramework(LFramework frame);


/* (2 pts) Searches the VNF with the given id and removes it from the node containing it.
 * The available CPU must be updated.
*/
void releaseVNF(LFramework frame, char *vnfId);

/* (1.5 pts) Frees all the memory of the framework, including the deployed VNFs
*/
void destroyFramework(LFramework *ptrFrame);

/* (2 pts) Saves into a binary file nly the information of all the VNFs deployed
   in the framework. For each VNF the file should contain:
   <length of the id><id><cpu> */
void store(LFramework frame, char *filename);

#endif /* NFV_H_ */
