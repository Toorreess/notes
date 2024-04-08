/*
 * Tree.h
 *
 *  Created on: 30 mar. 2022
 *      Author: galvez
 */

#ifndef TREE_H_
#define TREE_H_

typedef struct Node * Tree;
typedef struct Node {
	char* name;
	double lat, lon;
	Tree left, right;
} Node;

// Creates an empty tree.
// 0.25 pts.
void createTree(Tree* ptrTree);

// Assuming that the tree is ordered (Binary Search Tree),
// it inserts a new Node with the data passed as parameters
// and ordered by name.
// 1.75 pts.
void insertTree(Tree* ptrTree, char* name, double lat, double lon);

// Displays the tree in order, i.e. traversing it in infix mode.
// 1.0 pt.
void showTree(Tree t);

// Frees the memory of a tree and set it to NULL.
// 1.25 pts.
void destroyTree(Tree* ptrTree);

// Return the name of the nearest village.
// If the tree is empty then NULL is returned.
// The whole tree must be visited.
// 2.0 pt.
char* locateNearestCity(Tree t, double lat, double lon);

#endif /* TREE_H_ */
