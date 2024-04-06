/* 
 * File:   main.c
 * Author: Guillermo Pérez Trabado
 *
 * This example show how to write variables contents to a file in binary format.
 * 
 * Created on April 1, 2021, 7:03 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>


/**
 * This function is called to print fatal errors and exit if code is not 0.
 * @param msg Message to print besides errno explanation.
 * @param code An error code to return to the shell. If code==0, the program will not exit.
 */
void
log_error (char *msg, int code)
{
  perror (msg);
  if (code > 0) exit (code);
}

/*
 * 
 */
int
main (int argc, char** argv)
{
  /* This integer stores the number of the descriptor returned by open. */
  int binfile;

  /* Create a new file. If it already exists, then truncate it to zero length.
   *  Beware. O_TRUNC loses all previous data inside this file.
   *  If created, set permissions to rw- --- ---
   */
  binfile = open ("binfile.dat", O_CREAT | O_TRUNC | O_WRONLY, S_IRUSR | S_IWUSR);
  /* Always check if open succeeded. */
  if (binfile == -1)
    {
      /* errno variable contains error code when result is -1 for almost all system calls. errno is defined in errno.h */
      fprintf (stderr, "error code is %d\n", errno);
      /* strerror translates the error code to a string with a description of the error. It is defined in string.h */
      fprintf (stderr, "error message is %s\n", strerror (errno));
      /* perror prints our message and also prints the message with the description of the current value of errno. It is intended to be called immediately after any error. */
      perror ("File couldn't be opened.");
      /* Exit code 0 is interpreted in shells as exit with no error. Any other value is arbitrarily assigned by developer to inform about each error condition. */
      exit (1);
    }

  /* We start a new block to define some variables local to that block */
  {
    int out_int = 1;
    long long out_long_long = 2;
    float out_float = 3.0;
    double out_double = 4.0;
    char out_char = 'a';

    /* We define a static length char array. */
    char out_char_sarray[9];
    /* We also define a dynamically allocated array here (dynamically allocated <> dynamic size). Size is static after allocation. */
    char *out_char_darray = (char *) malloc (9 * sizeof (char));

    /* We define a static length float array here. */
    float out_float_sarray[50];
    /* We also define a dynamically allocated float array here */
    float *out_float_darray = (float *) malloc (50 * sizeof (float)); /* Size is specified in bytes */

    /* Now, we'll write the memory implementing each variable to the file.
     * Write takes a pointer to a memory position and a length.
     * We'll use C's & operator to get the memory address of each variable and sizeof() to get its length in bytes.
     */

    /* This is the data type that the man page states for write. It is some kind of integer, maybe long, but we
     * better use the defined type instead of declaring it as integer. */
    ssize_t l;

    /* Write an integer. We could also use sizeof(int), but sizeof(var) is safer if we change the type of out_int. */
    l = write (binfile, &out_int, sizeof (out_int));
    /* We have to check all system calls every time. Do not trust your luck as programmer by not checking it. */
    /* We better write a function to improve code readability as we are going to call it many times. */
    if (l == -1) log_error ("Error writing to binary file.", 1);
    /* Print number of bytes written and the current offset in output file. Offset is in bytes. */
    /* The file offset is the number of bytes resulting of storing the binary representation of an integer. */
    printf ("wrote int (%ld bytes): current offset=%ld\n", l, lseek (binfile, 0, SEEK_CUR));
    /* Write a long long */

    l = write (binfile, &out_long_long, sizeof (out_long_long));
    if (l == -1) log_error ("Error writing to binary file.", 1);
    /* The file offset is the resulting of storing the binary representation of 1 int + 1 long long. */
    printf ("wrote long long (%ld bytes): current offset=%ld\n", l, lseek (binfile, 0, SEEK_CUR));

    l = write (binfile, &out_float, sizeof (out_float));
    if (l == -1) log_error ("Error writing to binary file.", 1);
    printf ("wrote float (%ld bytes): current offset=%ld\n", l, lseek (binfile, 0, SEEK_CUR));

    /* Single chars are padded to 4 bytes in memory, but sizeof(char) returns 1. So, data in file is not aligned to multiples of 4 as in memory.  */
    l = write (binfile, &out_char, sizeof (out_char));
    if (l == -1) log_error ("Error writing to binary file.", 1);
    printf ("wrote char (%ld bytes): current offset=%ld\n", l, lseek (binfile, 0, SEEK_CUR));

    /* Single chars are padded to 4 bytes in memory, but sizeof(char) returns 1. So, data in file is not aligned to multiples of 4 as in memory.  */
    l = write (binfile, &out_char, sizeof (out_char));
    if (l == -1) log_error ("Error writing to binary file.", 1);
    printf ("wrote char (%ld bytes): current offset=%ld\n", l, lseek (binfile, 0, SEEK_CUR));

    /* To write an static array we can use sizeof() */
    /* Remember: the name of an array is a pointer to the first element. */
    l = write (binfile, out_char_sarray, sizeof (out_char_sarray));
    if (l == -1) log_error ("Error writing to binary file.", 1);
    printf ("wrote char sarray (%ld bytes): current offset=%ld\n", l, lseek (binfile, 0, SEEK_CUR));

    /* To write a dynamically allocated array we have to indicate the size that we used to allocate. */
    /* Beware: sizeof(pointer) will give you the size of the address (8 bytes), not the size of the referenced data, */
    l = write (binfile, out_char_darray, 9 * sizeof (char));
    if (l == -1) log_error ("Error writing to binary file.", 1);
    printf ("wrote char darray (%ld bytes): current offset=%ld\n", l, lseek (binfile, 0, SEEK_CUR));

    l = write (binfile, out_float_sarray, sizeof (out_float_sarray));
    if (l == -1) log_error ("Error writing to binary file.", 1);
    printf ("wrote float sarray (%ld bytes): current offset=%ld\n", l, lseek (binfile, 0, SEEK_CUR));
  }

  close (binfile);

  return (EXIT_SUCCESS);
}

