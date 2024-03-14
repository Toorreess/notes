typedef struct node *Ptr_Node;

typedef struct node
{
    int data;
    Ptr_Node ptrPrev, ptrNext;
} T_Node;

typedef struct head
{
    Ptr_Node ptrFirst, ptrLast;
} T_Head;

typedef T_Head T_List;

// ! When you pass an array, you pass a pointer
// ! When you pass an struct, you pass a copy

void initialize(T_Head *ptrHead);
void removeAll(T_Head *ptrHead);
void show(T_Head head); // Passed by value => A copy of the DLL
void insertFirst(T_Head *ptrHead, int value);
void insertLast(T_Head *ptrHead, int value);
int insertPos(T_Head *ptrHead, int pos, int value);
