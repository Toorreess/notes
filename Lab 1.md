the `cc` command calls various sub-commands

`cpp` -> nothing to do with C lang. Just replace the `#diretctives`. This is called **syntactic replacement**:
```C
#define
#include
#ifdef
(...)
```

`cpp` translates the program into a different, temporally .c programs. This processor compiles files isolated. 

We starting the **compilation stage** with this temporally .c files.

The next step is to parse this temporally .c program with the command `cc1` into machine code.
-  note: **Machine code** is a code a machine can run. It is in binary.

After the parsing stage, we obtain `.obj` files, finishing the *compilation stage* & starting the **linking stage**.

In the *linking stage*, the linker (called `ld` in Linux) is the one responsible to resolve dependencies between modules, **linking them**.
Once the linking stage finishes, we get an executable (.exe) of the C program we wrote.

**interesting**
Null in C doesn't exist, its `#define NULL 0L`