/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * File:   vector.h
 * Author: guille
 *
 * Created on March 6, 2022, 3:16 PM
 */

#include <math.h>
#include "vectors.h"

typedef struct
{
  double x, y, z;
} vector;

void vectoradd(vector *a, vector *b, vector *res)
{
  res->x = a->x + b->x;
  res->y = a->y + b->y;
  res->z = a->z + b->z;
}

void vectorsub(vector *a, vector *b, vector *res)
{
  res->x = a->x - b->x;
  res->y = a->y - b->y;
  res->z = a->z - b->z;
}

void vectorscalardiv(vector *a, double s, vector *res)
{
  res->x = a->x / s;
  res->y = a->y / s;
  res->z = a->z / s;
}

double
vectormodule(vector *a)
{
  return sqrt(a->x * a->x + a->y * a->y + a->z * a->z);
}

void vectornormalize(vector *a, vector *res)
{
  double module = vectormodule(a);
  res->x = a->x / module;
  res->y = a->y / module;
  res->z = a->z / module;
}
