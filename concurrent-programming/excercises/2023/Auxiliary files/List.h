/*
 * List.h
 *
 *  Created on: 1 mar. 2023
 *      Author: galvez
 */

#ifndef LIST_H_
#define LIST_H_
/*
 * Implementation of a circular linked list where the pointer points
 * to the last element and this one points to the first one, the first
 * to the second and so on.
 */

typedef struct Node * TList;

struct Node {
	char * name; // This is, simply, a pointer.
	int value;
	struct Node * ptrNext;
};

// Makes the list to point to NULL
// 0.25 pts.
void createList(TList * ptrL);

// Insert a new node at the end of the list.
// Memory for the node must be allocated, as well as for the name.
// If no memory can be allocated (malloc returns NULL), then
// this function returns immediately.
// 1.50 pts.
void insertList(TList * ptrL, char * name, int value);

// Remove the first node of the list and frees all its memory.
// If the list is empty the stores 0 in ok, otherwise stores 1.
// 1.50 pts.
void deleteList(TList * ptrL, unsigned char *ok);

// Returns a pointer to the first node of the list.
// If the list is empty the stores 0 in ok, otherwise stores 1.
// 0.5 pts.
struct Node * firstList(TList list, unsigned char *ok);

// Prints the content of the list: from the first to the last.
// name and value are printed in different lines.
// 1.50 pts.
void showList(TList list);

// Return how many nodes the list has.
// 1.25 pt.
int lengthList(TList list);

// Frees all the memory used by the list and makes the list to point to NULL.
// Hint: Use deleteList as an auxiliary function.
// 1.0 pts.
void destroyList(TList * ptrL);

#endif /* LIST_H_ */
