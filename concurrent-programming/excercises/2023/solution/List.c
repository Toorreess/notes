/*
 * List.c
 *
 *  Created on: 1 mar. 2023
 *      Author: galvez
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "List.h"

void createList(TList * ptrL) {
	*ptrL = NULL;
}

void insertList(TList * ptrL, char * name, int value){
	struct Node * ptrNode = (struct Node *) malloc(sizeof(struct Node));
	if (ptrNode == NULL) return;
	ptrNode->name = (char *) malloc(strlen(name) + 1);
	if (ptrNode->name == NULL) { free(ptrNode); return; }
	strcpy(ptrNode->name, name);
	ptrNode->value = value;
	if(*ptrL == NULL) {
		*ptrL = ptrNode;
		ptrNode->ptrNext = ptrNode;
	} else {
		ptrNode->ptrNext = (*ptrL)->ptrNext;
		(*ptrL)->ptrNext = ptrNode;
		(*ptrL) = ptrNode;
	}
}

void deleteList(TList * ptrL, unsigned char *ok) {
	if (*ptrL == NULL) { *ok = 0; return; }
	TList ptrFirst = (*ptrL)->ptrNext;
	if (*ptrL == (*ptrL)->ptrNext) { // There is a single node in the list
		*ptrL = NULL;
	} else {
		(*ptrL)->ptrNext = ptrFirst->ptrNext;
	}
	free(ptrFirst->name);
	free(ptrFirst);
	*ok = 1;
}

struct Node * firstList(TList list, unsigned char *ok) {
	if (list == NULL) { *ok = 0; return NULL; }
	*ok = 1;
	return list->ptrNext;
}

void showList(TList list) {
	FILE * fout = stdout;
	if (list == NULL) return;
	TList ptrFirst = list->ptrNext;
	TList ptrCurrent = ptrFirst;
	do {
		fprintf(fout, "%s\n%d\n", ptrCurrent->name, ptrCurrent->value);
		ptrCurrent = ptrCurrent->ptrNext;
	} while (ptrCurrent != ptrFirst);
}

int lengthList(TList list) {
	int ret = 0;
	if (list == NULL) return ret;
	TList ptrFirst = list->ptrNext;
	TList ptrCurrent = ptrFirst;
	do {
		ret++;
		ptrCurrent = ptrCurrent->ptrNext;
	} while (ptrCurrent != ptrFirst);
	return ret;
}

void destroyList(TList * ptrL) {
	unsigned char ok;
	do{
		deleteList(ptrL, &ok);
	} while (ok);
}


