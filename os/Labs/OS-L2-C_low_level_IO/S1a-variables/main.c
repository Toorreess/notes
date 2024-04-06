/* 
 * File:   main.c
 * Author: guille
 *
 * This example prints the memory sizes of different data types in C.
 * 
 * Created on April 1, 2021, 7:03 PM
 */

#include <stdio.h>
#include <stdlib.h>

/* All this variables are stored in the static variables segment. They are accessible from any function. */
int out_int = 1;
char out_char = 'a';
long long out_long = 2;
long long out_long_long = 3;
char *out_charp;
char out_char_array[2];
float out_float = 4.0;
double out_double = 5.0;

/*
 * 
 */
int
main (int argc, char** argv)
{
  /* these variables are on the stack */
  int in_int;
  int in_double;

  /* And this pointer is assigned dynamically */
  int *dyn_int = (int *) malloc (1 * sizeof (int));

  /* The length of each data type depends mainly on the C compiler. It can change from one CPU to other.
   It may be even surprising. (long is the same as long long in a x64 architecture. */

  /* DON'T GUESS SIZE. Use sizeof(var) to get actual size in bytes. */
  printf ("\nNotice the size of different data types in this architecture.\n");
  printf ("size of int: %lu\n", sizeof (out_int));
  printf ("size of char: %lu\n", sizeof (out_char));
  printf ("size of long: %lu\n", sizeof (out_long));
  printf ("size of long long: %lu\n", sizeof (out_long_long));
  printf ("size of pointer: %lu\n", sizeof (out_charp));
  printf ("size of char_array: %lu\n", sizeof (out_char_array));
  printf ("size of float: %lu\n", sizeof (out_float));
  printf ("size of double: %lu\n", sizeof (out_double));

  /* C is powerful because you can get low level access to the memory implementation of variables. */

  /* We get pointers to variables to print the actual memory address of each variable. */
  printf ("\nNotice that the starting address of each variable in memory is contiguous to the previous variable.\n");
  printf ("out_int is at \t\t0x%llx.\n", (unsigned long long) &out_int);
  printf ("out_char is at \t\t0x%llx.\n", (unsigned long long) &out_char);
  printf ("out_long is at \t\t0x%llx.\n", (unsigned long long) &out_long);
  printf ("out_long_long is at \t0x%llx.\n", (unsigned long long) &out_long_long);
  printf ("Notice that the char pointer and char array have been implemented after all variables. This reduces the risk of a buffer overrun attack.\n");
  printf ("out_charp is at \t0x%llx.\n", (unsigned long long) &out_charp);
  printf ("out_char_array is at \t0x%llx.\n", (unsigned long long) &out_char_array);
  printf ("out_float is at \t0x%llx.\n", (unsigned long long) &out_float);
  printf ("out_double at \t\t0x%llx.\n", (unsigned long long) &out_double);
  printf ("\nNotice that the local variables are on the stack, far away from global ones.\n");
  printf ("in_int is at \t\t0x%llx.\n", (unsigned long long) &in_int);
  printf ("in_double is at \t0x%llx.\n", (unsigned long long) &in_double);
  printf ("\nNotice that the dynamic memory is on the heap, far away from static data and stack.\n");
  printf ("dyn_int is at \t\t0x%llx.\n", (unsigned long long) dyn_int);

  /* We get pointers to variables (the memory address where it is located) and we subtract them to check the real size in memory of each variable.  */
  printf ("\nNotice that the some PADDING (extra bytes) has been inserted after the char to align the next variable with multiples of 4.\n");
  printf ("out_char is %lld bytes away from previous declaration.\n", (long long) &out_char - (long long) &out_int);
  printf ("out_long is %lld bytes away from previous declaration.\n", (long long) &out_long - (long long) &out_char);
  printf ("out_long_long is %lld bytes away from previous declaration.\n", (long long) &out_long_long - (long long) &out_long);
  printf ("out_float is %lld bytes away from previous declaration.\n", (long long) &out_float - (long long) &out_long_long);
  printf ("out_double is %lld bytes away from previous declaration.\n", (long long) &out_double - (long long) &out_float);
  printf ("\nNotice that variables on the stack are allocated in reverse order.\n");
  printf ("in_double is %lld bytes away from previous declaration.\n", (long long) &in_double - (long long) &in_int);

  return (EXIT_SUCCESS);
}
