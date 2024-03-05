/*
 * MemoryManager.h
 *
 *  Created on: 2024-03-01
 *      Author: J.Torres
 */

#include "MemoryManager.h"
#include <stdio.h>
#include <stdlib.h>

#define MAX 99

void *giveMemory(long size)
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
    *handler = (T_handler)giveMemory(sizeof(struct T_Node));
    (*handler)->start = 0;
    (*handler)->end = MAX;
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
    T_handler ptr = handler;
    while (ptr != NULL)
    {
        printf("[%d,%d] ", ptr->start, ptr->end);
        ptr = ptr->next;
    }
    printf("\n");
}

void Allocate(T_handler *handler, unsigned size, unsigned *ad, unsigned *ok)
{
    T_handler curr = *handler;
    T_handler prev = NULL;
    *ok = 0;
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
                    // Remove the first node
                    *handler = curr->next;
                }
                else
                {
                    // Remove intermediate or final node
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

void insert(T_handler *handler, unsigned size, unsigned ad)
{
    T_handler ptr = (*handler);
    if (ptr->next == NULL)
    {
        ptr->start = ad;
    }
}

void collapse()
{
}

void Deallocate(T_handler *handler, unsigned size, unsigned ad)
{
}