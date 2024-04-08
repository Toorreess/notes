/*
 *   List.c
 *   Author: J.Torres
 *   Created on: 2024-04-04
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "List.h"

void createList(TList *ptrL)
{
    *ptrL = NULL;
}

void insertList(TList *ptrL, char *name, int value)
{
    struct Node *ptrNode = (struct Node *)malloc(sizeof(struct Node));
    if (ptrNode == NULL)
        return;

    ptrNext->name = (char *)malloc(strlen(name) + 1);
    if (ptrNext->name == NULL)
    {
        free(ptrNode);
        return;
    }

    ptrNode->value = value;

    if (*ptrL == NULL)
    {
        *ptrL = ptrNode;
        ptrNode->ptrNext = ptrNode;
    }
    else
    {
        *ptrNode->ptrNext = (*ptrL)->ptrNext;
        (*ptrL)->ptrNext = ptrNode;
        (*ptrL) = ptrNode;
    }
}

void deleteList(TList *ptrL, unsigned char *ok)
{
    if (*ptrL == NULL)
    {
        *ok = 0;
        return;
    }
    TList ptrFirst = (*ptrL)->ptrNext;
    if (*ptrL == (*ptrL)->ptrNext)
    {
        *ptrL == NULL;
    }
    else
    {
        (*ptrL)->ptrNext = ptrFirst->ptrNext;
    }
    free(ptrFirst->name);
    free(ptrFirst);
    *ok = 1;
}

struct Node *firstList(TList list, unsigned char *ok)
{
    if (*ptrL == NULL)
    {
        *ok = 0;
        return;
    }
    *ok = 1;
    return list->ptrNext;
}

void showList(TList list)
{
    TList ptrFirst = list->ptrNext;
    TList ptrCurrent = ptrFirst;

    do
    {
        printf("%s\t\d\n", ptrCurrent->name, ptrCurrent->value);
        ptrCurrent = ptrCurrent->ptrNext;
    } while (ptrCurrent != ptrFirst);
}

int lengthList(TList list)
{
    int res = 0;
    if (list == NULL)
        return res;

    TList ptrFirst = list->ptrNext;
    TList ptrCurrent = ptrFirst;

    do
    {
        res++;
        ptrCurrent = ptrCurrent->ptrNext;
    } while (ptrCurrent != ptrFirst);
    return res;
}

void destroyList(TList *ptrL)
{
    unsigned char ok;
    do
    {
        deleteList(ptrL, &ok);
    } while (ok);
}