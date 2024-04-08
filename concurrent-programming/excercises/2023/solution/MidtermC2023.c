/*
 ============================================================================
 Name        : MidtermC2023.c
 Author      : SGR, JB
 Version     :
 Copyright   : uma.es
 Description : Main file with
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include "Scheduler.h"

int mainList(void);
int mainScheduler(void);

int main(void) {
	// Uncomment whatever you want to test.
	mainList();
	//mainScheduler();

	return 0;
}

int mainList(void) {
	unsigned char ok;
	TList list;
	createList(&list);
	assert(list==NULL);

	assert(lengthList(list)==0);
	printf("%d\n", lengthList(list));

	showList(list); printf("-----\n");

	deleteList(&list, &ok);
	assert(list==NULL);
	assert(ok==0);

	insertList(&list, "Alpha", 1);
	assert(list!=NULL);
	assert(strcmp(list->name,"Alpha")==0);
	assert(list->value==1);
	assert(list->ptrNext==list);

	insertList(&list, "Beta", 1);
	assert(strcmp(list->name,"Beta")==0);
	assert(list->value==1);
	assert(strcmp(list->ptrNext->name,"Alpha")==0);

	insertList(&list, "Gamma", 2);
	assert(strcmp(list->name,"Gamma")==0);
	assert(list->value==2);
	assert(strcmp(list->ptrNext->name,"Alpha")==0);
	assert(strcmp(list->ptrNext->ptrNext->name,"Beta")==0);

	assert(lengthList(list)==3);

	showList(list); printf("-----\n");
	struct Node * ptrNode = firstList(list, &ok);
	assert(ok==1);
	assert(ptrNode==list->ptrNext);

	deleteList(&list, &ok);
	assert(strcmp(list->name,"Gamma")==0);
	assert(list->value==2);
	assert(strcmp(list->ptrNext->name,"Beta")==0);
	assert(list==list->ptrNext->ptrNext);
	assert(ok==1);
	assert(lengthList(list)==2);

	destroyList(&list);
	assert(list==NULL);
	showList(list);

	printf("End Of Program.\n");
	return 0;
}

int mainScheduler(void) {
	unsigned char ok;
	char filename[] = "Z:\\casa\\betelgeuse\\seguridad\\Sergio\\Docencia-Ingles\\Concurrency\\PSC_workspace\\MidtermC2023\\src\\Scheduler.txt";
	TScheduler sched = readFile(filename, &ok);
	assert(ok==1);
	assert(sched.vector[0]!=NULL);
	assert(strcmp(sched.vector[0]->name,"USB02")==0);
	assert(strcmp(sched.vector[0]->ptrNext->name,"Printer01")==0);
	assert(sched.vector[1]==NULL);
	assert(strcmp(sched.vector[2]->name,"Thunderbolt01")==0);
	assert(strcmp(sched.vector[2]->ptrNext->name,"Thunderbolt01")==0);
	assert(strcmp(sched.vector[3]->name,"HDMI41")==0);
	assert(strcmp(sched.vector[3]->ptrNext->name,"Printer11")==0);
	assert(strcmp(sched.vector[3]->ptrNext->ptrNext->name,"Printer21")==0);
	insertScheduler(&sched, "Other", 77, 3, &ok);
	assert(ok==1);
	insertScheduler(&sched, "Other", 77, 6, &ok);
	assert(ok==0);
	showScheduler(&sched);
	printf("-----\n");
	destroyScheduler(&sched);
	showScheduler(&sched);
	printf("End Of Program.\n");
	return 0;
}
