/*
 * MemoryManager.c
 *
 *  Created on: 1 mar 2024
 *      Author: alumno
 */
#include "MemoryManager.h"
#include <stdio.h>
#include <stdlib.h>

// auxiliar fun
void *giveMeMemory(long size)
{
    void *aux = malloc(size);
    if (aux == NULL)
    {
        printf("Memory insufficient\n");
        exit(-1);
    }
    return aux;
}

void Create(T_handler *handler)
{
    *handler = (T_handler)giveMeMemory(sizeof(struct T_Node));
    (*handler)->start = 0;
    (*handler)->end = 99;
    (*handler)->next = NULL;
}

void Destroy(T_handler *handler)
{
    while (*handler != NULL)
    {
        T_handler aux = (*handler);
        (*handler) = (*handler)->next;
        free(aux);
    }
}

void Show(T_handler handler)
{
    T_handler aux = handler;
    while (aux != NULL)
    {
        printf("[%d,%d] ", aux->start, aux->end);
        aux = aux->next;
    }
    printf("\n");
}

void Allocate(T_handler *handler, unsigned size, unsigned *ad, unsigned *ok)
{
    T_handler curr = *handler;
    T_handler prev = NULL;
    *ok = 0;
    // 1st: find a block such that (end-start+1) >= size
    while (curr != NULL)
    {
        if (curr->end - curr->start + 1 >= size)
        {
            *ok = 1;
            *ad = curr->start;
            if (curr->end - curr->start + 1 > size)
            {
                curr->start += size;
            }
            else
            {
                if (prev == NULL)
                {
                    // remove the 1st node of the list
                    *handler = curr->next;
                }
                else
                {
                    // remove intermediate or final node
                    prev->next = curr->next;
                }
                free(curr);
            }
            break;
        }
        else
        {
            prev = curr;
            curr = curr->next;
        }
    }
}

/* Auxiliary functions to implement Deallocate */

/* Inserts a block in the list ORDERED by the start memory address.
   Recursive implementation. It is also possible the iterative implementation. */

void insertBlock(T_handler *handler, unsigned size, unsigned ad)
{
    // Base case: empty list or the new node has to be inserted before the current 1st node
    if (*handler == NULL || ad < (*handler)->start)
    {
        T_handler aux = (T_handler)giveMeMemory(sizeof(struct T_Node));
        aux->start = ad;
        aux->end = ad + size - 1;
        aux->next = *handler;
        *handler = aux;
    }
    else
    { // Recursive case: the new node has to be inserted in the rest of the list
        insertBlock(&((*handler)->next), size, ad);
    }
}

/* Merges pairs of nodes with consecutive memory address*/
void compactBlocks(T_handler *handler)
{
    T_handler curr = *handler;
    while (curr != NULL && curr->next != NULL)
    { // The list has at least two nodes
        if (curr->end + 1 == curr->next->start)
        {
            T_handler aux = curr->next;
            curr->end = curr->next->end;
            curr->next = curr->next->next;
            free(aux);
            // In this case we do not advance curr in case the next node has also a consecutive memory address
        }
        else
        {
            curr = curr->next;
        }
    }
}

void Deallocate(T_handler *handler, unsigned size, unsigned ad)
{
    insertBlock(handler, size, ad);
    compactBlocks(handler);
}
