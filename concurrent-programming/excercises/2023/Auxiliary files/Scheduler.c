/*
 * Scheduler.c
 *
 *  Created on: 1 mar. 2023
 *      Author: galvez
 */

#include <stdio.h>
#include <stdlib.h>
#include "Scheduler.h"

TScheduler createScheduler()
{
	TScheduler ret;
	ret.numProcesses = 0;
	for (int i = 0; i < NUM_PROCESSORS; i++)
		createList(&(ret.vector[i]));
	return ret;
}

void insertScheduler(TScheduler *ptrS, char *name, int value, unsigned char processorId, unsigned char *ok)
{
	if (processorId >= NUM_PROCESSORS)
	{
		*ok = 0;
		return;
	}
	insertList(&(ptrS->vector[processorId]), name, value);
	ptrS->numProcesses++;
}

void showScheduler(TScheduler *ptrS)
{
	FILE *fout = stdout;
	fprintf(fout, "%d\n", ptrS->numProcesses);
	for (int i = 0; i < NUM_PROCESSORS; i++)
	{
		fprintf(fout, "%d\n", lengthList(ptrS->vector[i]));
		showList(ptrS->vector[i]);
	}
}

// CODE THIS FUNCTION
//
TScheduler readFile(char *filename, unsigned char *ok)
{
	TScheduler ret = createScheduler();
	FILE *fin = fopen(filename, "rt");
	if (fin == NULL)
	{
		printf("[*] ERROR: The file '%s' cannot be opened.", filename);
		*ok = 0;
		return;
	}

	fscanf(fin, "%d", &ret.numProcesses);
	for (int i = 0; i < NUM_PROCESSORS; i++)
	{
		int numNodes;
		fscanf(fin, "%d", &numNodes);
		for (int j = 0; j < numNodes; j++)
		{
			char name[256];
			int value;
			fscanf(fin, "%s", name);
			fscanf(fin, "%d", value);
			insertList(&(ret.vector[i]), name, value);
		}
	}
	fclose(fin);
	*ok = 1;
	return ret;
}

void destroyScheduler(TScheduler *ptrS)
{
	ptrS->numProcesses = 0;
	for (int i = 0; i < NUM_PROCESSORS; i++)
	{
		destroyList(&(ptrS->vector[i]));
	}
}
