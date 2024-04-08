/*
 * Tree.c
 *
 *  Created on: 30 mar. 2022
 *      Author: galvez
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "Tree.h"

// Assuming that the tree is ordered (Binary Search Tree),
// it inserts a new Node with the data passed as parameters
// and ordered by name.
// If the name is already in the tree then no node is inserted.
void insertTree(Tree* ptrTree, char* name, double lat, double lon) {
	if (*ptrTree == NULL) {
		Node* ptrNode = (Node*) malloc(sizeof(Node));
		ptrNode->name = (char*) malloc(strlen(name) + 1);
		strcpy(ptrNode->name, name);
		ptrNode->lat = lat;
		ptrNode->lon = lon;
		ptrNode->left = ptrNode->right = NULL;
		(*ptrTree) = ptrNode;
	} else if (strcmp(name, (*ptrTree)->name) < 0) {
		insertTree(&((*ptrTree)->left), name, lat, lon);
	} else if (strcmp(name, (*ptrTree)->name) > 0) {
		insertTree(&((*ptrTree)->right), name, lat, lon);
	} // if the name already exists then nothing happens
}
// Creates an empty tree
void createTree(Tree* ptrTree) {
	(*ptrTree) = NULL;
}
// Frees the memory of a tree and set it to NULL
void destroyTree(Tree* ptrTree) {
	if (*ptrTree != NULL) {
		destroyTree(&((*ptrTree)->left));
		destroyTree(&((*ptrTree)->right));
		free((*ptrTree)->name);
		free(*ptrTree);
		(*ptrTree) = NULL;
	}
}
// Displays the tree in order, i.e. traversing it in infix mode.
void showTree(Tree t) {
	if (t != NULL) {
		showTree(t->left);
		printf("%s %lf %lf\n", t->name, t->lat, t->lon);
		showTree(t->right);
	}
}

// Returns the distance between two coordinates
// The formula is sqrt((lat1-lat2)^2+(lon1-lon2)^2)
double distance(double lat1, double lon1, double lat2, double lon2) {
	return sqrt(pow(lat1 - lat2, 2)+pow(lon1 - lon2, 2));
}

Node* recursiveLocateRocket(Tree t, double lat, double lon) {
	if (t == NULL) return NULL;
	double currDistance = distance(lat, lon, t->lat, t->lon);
	Node* ret = t;
	// Compare to the left
	Node* shortestLeft = recursiveLocateRocket(t->left, lat, lon);
	if (shortestLeft != NULL) {
		double leftDistance = distance(lat, lon, shortestLeft->lat, shortestLeft->lon);
		if (leftDistance < currDistance) {
			currDistance = leftDistance;
			ret = shortestLeft;
		}
	}
	// Compare to the right
	Node* shortestRight = recursiveLocateRocket(t->right, lat, lon);
	if (shortestRight != NULL) {
		double rightDistance = distance(lat, lon, shortestRight->lat, shortestRight->lon);
		if (rightDistance < currDistance) {
			currDistance = rightDistance;
			ret = shortestRight;
		}
	}
	return ret;
}

// Return the name of the nearest village.
// If the tree is empty then NULL is returned.
char* locateNearestCity(Tree t, double lat, double lon) {
	if (t == NULL) return NULL;
	return recursiveLocateRocket(t, lat, lon)->name;
}

