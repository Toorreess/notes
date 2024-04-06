/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * File:   main.c
 * Author: Guillermo Pérez Trabado
 *
 * Created on April 1, 2021, 7:03 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <stddef.h>

/* This is the size of our in-memory table of structs */
#define MAX_PLAYERS 20
/* Length of name including a final 0 */
#define MAX_NAME 41

/* We define a complex data type to use it as a register similar to those on databases. */

/* A struct is actually a contiguous block of memory with enough space to store all the fields.*/
typedef struct
{
    int ID;
    char Gender; // The compiler will add padding after this char.
    float Score;
    char Name[MAX_NAME];
} player;

/**
 * This function is called to print fatal errors and exit if code is not 0.
 * @param msg Message to print besides errno explanation.
 * @param code An error code to return to the shell. If code==0, the program will not exit.
 */
void log_error(char *msg, int code)
{
    perror(msg);
    if (code > 0)
        exit(code);
}

int writeDB()
{
    /* This integer stores the number of the descriptor returned by open. */
    int binfile;
    /* Aux variable */
    int i;

    /* Create a new file. If it already exists, then truncate it to zero length.
     *  Beware. O_TRUNC loses all previous data inside this file.
     *  If created, set permissions to rw- --- ---
     */
    binfile = open("playerDB.dat", O_CREAT | O_TRUNC | O_WRONLY, S_IRUSR | S_IWUSR);
    /* Always check if open succeeded. */
    if (binfile == -1)
    {
        /* errno variable contains error code when result is -1 for almost all system calls. errno is defined in errno.h */
        fprintf(stderr, "error code is %d\n", errno);
        /* strerror translates the error code to a string with a description of the error. It is defined in string.h */
        fprintf(stderr, "error message is %s\n", strerror(errno));
        /* perror prints our message and also prints the message with the description of the current value of errno. It is intended to be called immediately after any error. */
        perror("File couldn't be opened.");
        /* Exit code 0 is interpreted in shells as exit with no error. Any other value is arbitrarily assigned by developer to inform about each error condition. */
        exit(1);
    }

    /* Allocate space for a dynamic array of registers. We use calloc() to clear it. */
    player *PlayerTable = (player *)calloc(MAX_PLAYERS, sizeof(player));

    /* Initialize table with some data */
    for (i = 0; i < MAX_PLAYERS; i++)
    {
        PlayerTable[i].ID = i;
        PlayerTable[i].Gender = 'N';                              // Gender not available
        snprintf(PlayerTable[i].Name, MAX_NAME, "Player%06d", i); // The register has static size. Print directly on reserved space.
        PlayerTable[i].Score = 0.0;
    }

    ssize_t l;
    /* Write the whole table to the file in one single operation. */
    l = write(binfile, PlayerTable, MAX_PLAYERS * sizeof(player));
    /* Return value must be the same number of bytes requested */
    if (l != MAX_PLAYERS * sizeof(player))
        log_error("Error writing to binary file.", 1);
    printf("wrote table (%ld bytes): current offset=%ld\n", l, lseek(binfile, 0, SEEK_CUR));

    /* Free array space */
    free(PlayerTable);
    /* Clear pointer to avoid using it after this point. */
    PlayerTable = NULL;

    /* Close the file */
    close(binfile);
}

int readDB()
{
    /* This integer stores the number of the descriptor returned by open. */
    int binfile;
    /* Aux variable */
    int i;

    /* Open existing file for reading.
     */
    binfile = open("playerDB.dat", O_RDONLY);
    /* Always check if open succeeded. */
    if (binfile == -1)
    {
        /* errno variable contains error code when result is -1 for almost all system calls. errno is defined in errno.h */
        fprintf(stderr, "error code is %d\n", errno);
        /* strerror translates the error code to a string with a description of the error. It is defined in string.h */
        fprintf(stderr, "error message is %s\n", strerror(errno));
        /* perror prints our message and also prints the message with the description of the current value of errno. It is intended to be called immediately after any error. */
        perror("File couldn't be opened.");
        /* Exit code 0 is interpreted in shells as exit with no error. Any other value is arbitrarily assigned by developer to inform about each error condition. */
        exit(1);
    }

    /* Allocate space for a dynamic array of registers. We use calloc() to clear it. */
    player *PlayerTable = (player *)calloc(MAX_PLAYERS, sizeof(player));

    ssize_t l;
    /* Read the whole table from the file in one single operation. */
    l = read(binfile, PlayerTable, MAX_PLAYERS * sizeof(player));
    /* Return value must be the same number of bytes requested */
    if (l != MAX_PLAYERS * sizeof(player))
        log_error("Error reading from binary file.", 1);
    printf("read table (%ld bytes): current offset=%ld\n", l, lseek(binfile, 0, SEEK_CUR));

    /* Print table data */
    for (i = 0; i < MAX_PLAYERS; i++)
    {
        printf("%6d\t%c\t%-15s\t%8.02f\n",
               PlayerTable[i].ID,
               PlayerTable[i].Gender,
               PlayerTable[i].Name,
               PlayerTable[i].Score);
    }

    /* Free array space */
    free(PlayerTable);
    /* Clear pointer to avoid using it after this point. */
    PlayerTable = NULL;

    /* Close the file */
    close(binfile);
}

/**
 * Register table on file
 * @param argc Number of command line options.
 * @param argv Array with command line options.
 * @return exit code.
 */
int main(int argc, char **argv)
{
    /* Create DB file on disk */
    writeDB();
    /* Read DB file from disk */
    readDB();
    return (EXIT_SUCCESS);
}
