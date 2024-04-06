/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: guille
 *
 * Created on March 6, 2022, 3:05 PM
 */
#define _XOPEN_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "vectors.h"
#include "model.h"

#define RANGE_END 500
#define RANGE_START -500
#define RANGE_EXTENT RANGE_END-RANGE_START

vector
callcenter (vector table[], int nvectores)
{
  vector center;

  centerofmass (table, nvectores, &center);
  return center;
}

/*
 * This is the start point of the program
 */
int
main (int argc, char** argv)
{
  /* Lets check the number of arguments */
  if (argc != 2)
    {
      fprintf (stdout, "%s: Not enough arguments.\n", argv[0]);
      return (EXIT_FAILURE);
    }

  /* Lets read an integer */
  int numberofvectors;
  numberofvectors = atoi (argv[1]);

  /* Lets reserve memory for the table */
  vector array[numberofvectors];

  /* Lets create some random vectors */
  int i;
  for (i = 0; i < numberofvectors; i++)
    {
      // Generate a rand double in the range [0,1), and extend it to be a double in the range [-RANGE_START,RANGE_END)
      array[i].x = drand48 () * RANGE_EXTENT + RANGE_START;
      // Generate a rand double in the range [0,1), and extend it to be a double in the range [-RANGE_START,RANGE_END)
      array[i].y = drand48 () * RANGE_EXTENT + RANGE_START;
      // Generate a rand double in the range [0,1), and extend it to be a double in the range [-RANGE_START,RANGE_END)
      array[i].z = drand48 () * RANGE_EXTENT + RANGE_START;
    }

  vector c;
  c = callcenter (array, numberofvectors);
  printf ("Computation ended.\n");
  printf ("Center of mass at <%lf,%lf,%lf>\n", c.x, c.y, c.z);
  sleep (5);
  printf ("End\n");

  return (EXIT_SUCCESS);
}