/*
 ============================================================================
 Name        : MidtermC2022.c
 Author      : SGR
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Tree.h"

// Loads a text file with lines whose structure is:
// name latitude longitude
// and creates a node in a BST for each of these lines.
//
// 1.75 pts.
void loadTextFile(char* filename, Tree* ptrTree) {
	FILE* fin = fopen(filename, "rt");
	if (fin == NULL) { perror("File not found.\n"); exit(EXIT_FAILURE); }
	char name[256];
	double lat, lon;
	fscanf(fin, "%s", name);
	while(! feof(fin)) {
		fscanf(fin, "%lf %lf", &lat, &lon);
		insertTree(ptrTree, name, lat, lon);
		fscanf(fin, "%s", name);
	}

	fclose(fin);
}


void recursiveSaveBinaryFile(FILE* fout, Tree t) {
	if (t != NULL) {
		recursiveSaveBinaryFile(fout, t->left);
		size_t length = strlen(t->name);
		fwrite(&length, sizeof(size_t), 1, fout);
		fwrite(t->name, length, 1, fout);
		fwrite(&(t->lat), sizeof(t->lat), 1, fout);
		fwrite(&(t->lon), sizeof(t->lon), 1, fout);
		recursiveSaveBinaryFile(fout, t->right);
	}
}

// Saves the tree in a sorted way in a binary file.
// Each node must be saved with the next binary structure:
// - An integer with the length of the field name
// - The characters of the field name.
// - A double with latitude.
// - A double with longitude.
//
// 2.0 pts.
void saveBinaryFile(char* filename, Tree tree) {
	FILE* fout = fopen(filename, "wb");
	if (fout == NULL) { perror("File not found.\n"); exit(EXIT_FAILURE); }
	recursiveSaveBinaryFile(fout, tree);
	fclose(fout);
}

//
void recursiveSaveTextFile(FILE* fout, Node* a, int init, int end) {
	if (init <= end) {
		int middle = (init + end) / 2;
		fprintf(fout, "%s\t%9.6lf\t%9.6lf\n", a[middle].name, a[middle].lat, a[middle].lon);
		recursiveSaveTextFile(fout, a, init, middle-1);
		recursiveSaveTextFile(fout, a, middle+1, end);
	}
}

// Loads a sorted text file and saves it in infix mode
// so a linear read of them builds a balanced tree
// through the function loadTextFile
//
#define NUM_CITIES 100
void orderInPrefix() {
	Node array[NUM_CITIES];
	// Load text file in array
	FILE* fin = fopen("Z:\\casa\\betelgeuse\\seguridad\\Sergio\\Docencia-Ingles\\Concurrency\\PSC_workspace\\MidtermC2022\\src\\data.txt", "rt");
	if (fin == NULL) { perror("File not found.\n"); exit(EXIT_FAILURE); }
	char name[256];
	for(int i=0; i<NUM_CITIES; i++) {
		fscanf(fin, "%s", name);
		array[i].name = (char*) malloc(strlen(name) + 1);
		strcpy(array[i].name, name);
		fscanf(fin, "%lf %lf", &(array[i].lat), &(array[i].lon));
	}
	fclose(fin);
	// Save in infix mode
	FILE* fout = fopen("Z:\\casa\\betelgeuse\\seguridad\\Sergio\\Docencia-Ingles\\Concurrency\\PSC_workspace\\MidtermC2022\\src\\data2.txt", "wt");
	if (fout == NULL) { perror("File not found.\n"); exit(EXIT_FAILURE); }
	recursiveSaveTextFile(fout, array, 0, NUM_CITIES-1);
	fclose(fout);
}
//

int main(void) {
	Tree t;
	createTree(&t);
	printf("Loading...\n");
	loadTextFile("Z:\\casa\\betelgeuse\\seguridad\\Sergio\\Docencia-Ingles\\Concurrency\\PSC_workspace\\MidtermC2022\\src\\data.txt", &t);
	printf("Displaying...\n");
	showTree(t);
	printf("Saving...\n");
	saveBinaryFile("Z:\\casa\\betelgeuse\\seguridad\\Sergio\\Docencia-Ingles\\Concurrency\\PSC_workspace\\MidtermC2022\\src\\data.bin", t);
	printf("Locating rocket. It's near %s\n", locateNearestCity(t, 36.93, -4.99));
}


