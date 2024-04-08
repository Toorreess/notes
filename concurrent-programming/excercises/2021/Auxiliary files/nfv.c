
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "nfv.h"

void init(LFramework *ptrFrame, int size, int initCpu)
{
    LFramework aux = *ptrFrame;
    *ptrFrame = NULL;

    while (size > 0)
    {
        aux = *ptrFrame;
        *ptrFrame = (LFramework) malloc(sizeof(struct  NodeFramework);
        if(*ptrFrame == NULL){
            *ptrFrame = aux;
            break;
        }

        (*ptrFrame)->availCpu = initCpu;
        (*ptrFrame)->vnfs = NULL;
        (*ptrFrame)->next = aux;
        size--;
    }
}

int deployVNF(LFramework frame, char *vnfId, int cpu)
{
    LFramework auxFrame = frame;
    LVnf auxVnf;
    int ok = 0;

    while (auxFrame != NULL && auxFrame->availCpu < cpu)
    {
        auxFrame = auxFrame->next;
    }

    if (auxFrame != NULL)
    {
        auxFrame->availCpu -= cpu;
        auxVnf = auxFrame->vnfs;
        auxFrame->vnfs = (LVnf)malloc(sizeof(struct NodeVnf));

        if (auxFrame->vnfs == NULL)
        {
            perror("{*} ERROR")
        }
        else
        {
            auxFrame->vnfs->next = auxVnf;
            auxFrame->vnfs->cpu = cpu;
            strcpy(auxFrame->vnfs->id, vnfId);

            ok = 1;
        }
    }
    return ok;
}

/* (1 pt) Displays on console the nodes of the framework. For each node must be shown:
 * - Available CPU
 * - The names of the VNFs deployed
 */
void showFramework(LFramework frame)
{
    LVnf aux;
    int i = 0;
    if (frame == NULL)
        printf("[*] Framework is empty\n");
    while (frame != NULL)
    {
        printf("Framework node %d CPU avail %d. VNFS are: ", i, frame->availCpu);
        aux = frame->vnfs;
        while (aux != NULL)
        {
            printf("(%s, %d)\t", aux->id, aux->cpu);
            aux = aux->next;
        }
        printf("\n");
        frame = frame->next;
        i++;
    }
}

void releaseVNF(LFramework frame, char *vnfId)
{
    LVnf currV, antV;

    while (frame != NULL)
    {
        currV = frame->vnfs;
        antV = NULL;
        while (currV != NULL && currV->id != vnfId)
        {
            antV = currV;
            currV = currV->next;
        }
        if (currV != NULL)
        {
            break;
        }
        frame = frame->next;
    }
    if (currV != NULL)
    {
        frame->availCpu += currV->cpu;
        if (antV == NULL)
        {
            frame->vnfs = currV->next;
        }
        else
        {
            antV->next = currV->next;
        }
        free(currV);
    }
}

void destroyFramework(LFramework *ptrFrame)
{
    LFramework currI;
    LVnf currV;

    while (*ptrFrame != NULL)
    {
        while ((*ptrFrame)->vnfs != NULL)
        {
            currV = (*ptrFrame)->vnfs;
            (*ptrFrame)->vnfs = (*ptrFrame)->vnfs->next;
            free(currV);
        }
        currI = (*ptrFrame);
        (*ptrFrame) = (*ptrFrame)->next;
        free(currI)
    }
}

void store(LFramework frame, char *filename)
{
    File *f;
    LVnf currV;

    f = fopen(filename, "rb");
    if (f == NULL)
    {
        perror("{*} ERROR: Couln't open the file.");
        exit(-1);
    }

    while (frame != NULL)
    {
        currV = frame->vnfs;
        while (currV != NULL)
        {
            len = strlen(currV->id);
            fwrite(&len, sizeof(int), 1, f);
            fwrite(currV->id, len, 1, f);
            fwrite(currV->cpu, sizeof(int), 1, f);
            currV = currV->next;
        }
        frame = frame->next;
    }
}