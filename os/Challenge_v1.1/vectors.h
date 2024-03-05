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
#ifndef VECTOR_H
#define VECTOR_H

typedef struct
{
  double x, y, z;
} vector;

void
vectoradd (vector *a, vector *b, vector *res);

void
vectorsub (vector *a, vector *b, vector *res);

void
vectorscalardiv (vector *a, double s, vector *res);

double
vectormodule (vector *a);

void
vectornormalize (vector *a, vector *res);

#endif /* VECTOR_H */

