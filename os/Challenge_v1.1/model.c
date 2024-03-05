/*
 * This library is called at main.c
 */

#include "vectors.h"

/* Computes the center of mass of an array of vectors */
void
centerofmass (vector cluster[], int numvectors, vector *center)
{
  vector sum = {0, 0, 0};

  int i;
  /* Add all vectors in the array into sum */
  for (i = 0; i < numvectors; i++)
    {
      vectoradd (&sum, &cluster[i], &sum);
    }
  /* Computer average for each dimension */
  center->x = sum.x * (1 / numvectors);
  center->y = sum.y * (1 / numvectors);
  center->z = sum.z * (1 / numvectors);
}