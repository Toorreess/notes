# 1. Memory Hierarchy
## 1.1 Objectives of the system of memory management
The OS shares (*multiplexes* in time) resources among processes, so each process functions as if it is alone in the computer.
- *Process management:* distribute processor.
- *Memory management:* distribute memory.

The goals of the memory management are:
- Provide each process with its own **logical/virtual address space**.
- Provide process with **huge address spaces**.
- Share **main memory** among processes.
- Maximize **multiprogramming** degree.
- Ensure **protection** among processes.
- **Performance** and **low cost**.
## 1.2 Solution: Virtual memory
*Virtual memory* involves the separation of user logical memory from physical memory. 
- The CPU generates virtual addresses.
- The *Memory Management Unit* (MMU) translates virtual addresses into physical addresses.
- Part of the memory map is in main memory and the rest on the disk.
- A page fault raises when the address is not in main memory: The OS swaps a page into memory (replacement possibly needed; *costly*).
- Virtual memory completes memory hierarchy: **fulfills memory hierarchy goals**.

![[Pasted image 20240521093301.png]]


# 2. Address Translation
## 2.1 Address spaces
**Virtual or logical address space.**
- Every address the processor generates and stores in Address Registers are logical addresses.
- Size of the logical address space valid for a process: depends on the size allocated for the OS.
		- E.g., a 64bit system may allow for $2^{64}$ addresses ($16$ ExaBytes = $17\ 179\ 869\ 184$ GB).

**Physical or real address space.**
- Addresses that computer memory banks deal with / can interpret.
- Size of the physical address space: depends on the amount of RAM, ROM & I/O mapped memory.

*Memory Management Unit - MMU:* Translates logical addresses into physical addresses.
![[Pasted image 20240521100827.png]]


## 2.2 Address translation
- High level languages do not use numerical addresses. Instead, they use variables to denote memory positions with  data & functions to denote memory positions with instructions.
- Variables & functions symbolic names are translated on various stages:
	1. **Compilation:** Separates text from data. Generates assembler code for each object file with relative addresses to the beginning of functions.
	2. **Linking:** Merges object files together in an executable file. Solves cross references (a function in a source file may call another source file’s function). Creates text and data sections and recalculates relative addresses to the beginning of sections.
	3. **Loading:** Assigns initial addresses to program sections (virtual address space relocation).
	4. **Execution:** MMU translates virtual addresses to physical addresses (physical address space relocation).

- *Advantages:* abstraction and portability (OSs may relocate address spaces freely).

![[Pasted image 20240521101902.png]]

![[Pasted image 20240521102026.png]]

![[Pasted image 20240521102051.png]]

# 3. Memory Management Models
