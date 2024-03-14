/**
 * date: 2024-03-13
 * author: JTorres
 *
 * Double Linked List with header
 */

#include <stdio.h>
#include <stdlib.h>

#include "20240313.h"

// ! When you pass an array, you pass a pointer
// ! When you pass an struct, you pass a copy
void initialize(T_Head *ptrHead)
{
    ptrHead->ptrFirst = ptrHead->ptrLast = NULL;
}

void show(T_Head head)
{
    for (Ptr_Node aux = head.ptrFirst; aux != NULL; aux = aux->ptrNext)
    {
        printf("%d ", aux->data);
    }
    printf("\n");
}

void removeAll(T_Head *ptrHead)
{
    Ptr_Node aux = ptrHead->ptrFirst;
    while (aux != NULL)
    {
        Ptr_Node temp = aux;
        aux = aux->ptrNext;
        free(temp);
    }
}

void insertFirst(T_Head *ptrHead, int value)
{
    Ptr_Node new = (Ptr_Node)malloc(sizeof(T_Node));
    new->data = value;
    if (ptrHead->ptrFirst == NULL)
    {
        ptrHead->ptrFirst = ptrHead->ptrLast = new;
        new->ptrNext = new->ptrPrev = NULL;
    }
    else
    {
        new->ptrNext = ptrHead->ptrFirst;
        ptrHead->ptrFirst->ptrPrev = new;
        new->ptrPrev = NULL;
        ptrHead->ptrFirst = new;
    }
}

int insertPos(T_Head *ptrHead, unsigned int pos, int value)
{
    if (pos > length(*ptrHead))
    {
        return 0;
    }
    if (pos == 0)
    {
        insertFirst(ptrHead, value);
    }
    else if (pos == length(*ptrHead))
    {
        insertLast(ptrHead, value);
    }
    else
    {
        Ptr_Node aux = ptrHead->ptrFirst;
        while (pos > 1)
        {
            aux = aux->ptrNext;
            pos--;
        }
        Ptr_Node new = (Ptr_Node)malloc(sizeof(T_Node));
        new->data = value;
        new->ptrPrev = aux;
        new->ptrNext = aux->ptrNext;
        aux->ptrNext->ptrPrev = new;
        aux->ptrNext = new;
    }
    return 1;
}

int main(void)
{
    puts("Starting...\n");
    T_Head head;
    initialize(&head);
    show(head);
    return EXIT_SUCCESS;
}