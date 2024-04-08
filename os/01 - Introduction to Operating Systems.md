# Introduction
## Computer-System Components
- **Hardware**: Provides basic computing resources (CPU, memory, I/O).
- **Operating system**: Controls and coordinates use of hardware among various applications and users.
- **Application programs**: define the ways in which the system resources are used to solve the computing problems of the users (Word processors, compilers, web browsers, database systems, games...)
- **Users**: People, machines, other computers

# OS Concepts
## Definitions
- A set of programs, implemented as software or firmware, that facilitates the use of the HW of the computer for all users leading to good performance.
- A program that acts as an intermediate between a user and the hardware of a computer.

The **kernel** is the one program running at all times on the computer.
## Informatics Systems
![[Pasted image 20240330193423.png]]

## Parts of the Informatics System
![[Pasted image 20240330193456.png]]

## System Goals
The goals of the OS is
- Provide the user with a friendly machine.
- Manage efficiently the HW of the computer.

Views on the OS:
- *Top-down*: extended machine
	- Looking down you see an easy to use machine.
	- The OS provides the tools to use the HW comfortably.
	- Without the OS, we should program the naked machine.

- *Bottom-up*: Manager of the hardware.
	- View from the HW POV.
	- SW that manages an does administration of HW (CPU, MEM & I/O)
	- The OS controls the HW.
	- The OS should allocate resources without consuming them.

## Why an OS?
**Embedded systems:**
- You have few resources.
- You only solve one simple problem with a micro-controller.
- You only run a single program.

**Desktops:**
- You have more resources than needed for one single program
- You need to do many things at a time (**multitasking**)
- You run several programs at the same time.

**Servers.**
- You have more resources than needed for one single program.
- You need to do the same operation for thousands of users (**multiprocessing / multiuser**)
- You run many instances of the same program at the same time.

# History of OS

## 1st generation .*Vacuum tubes*
**No OS existed**
- I/O by plugboards, switches and lights.
- The programmer:
	- Reserves the lab for machine use: signup sheet on wall.
	- Load a binary program, execute and analyse output.
	- Inconveniences:
		- Only feasible for small programs.
		- Low productivity: one can reserve 2h, and finish in 1.5

**Improvement**
- 1950 punched cards, tapes (paper and magnetic)
- Compilers (Fortran and cobol)

**Compilers**
- Simplify programming.
- Complicate loading/executing the program.
	- Load compiler, compile, load assembler, assemble, load binary, execute, analyse output.
	- Conclusion: preparation time big. Idle CPU.

**Solutions**
- Contract operator
	- Programmer gives punched cards to operator and returns next day
	- Cleaning more difficult: post-mortem
- Operator groups tasks
	- First load compiler and compile all programs
	- Load assembler and assemble them all
	- Execute all

## 2nd generation. *Transistors and batches*
**Still loss of CPU time between end of job and beginning next one**

**Solution: batch system**
- Program monitor who loads jobs in batches
- First operating systems
- *JCL* (Job Control Language)
- Predecessor of .bat files and scripts.

![[Pasted image 20240330195652.png]]

**Loss of CPU time in reading cards**
- A 1200 card program requires 60s reading and 4s executing
	- CPU idle during 93.3% of the time
- Solution: off-line execution
	- Dedicated computers read cards onto magnetic tapes and print off-line. Expensive computer reads tape and executes. Less loss of time in I/O.

## 3rd generation
**ICs and multiprogramming**
- New technology
	- Magnetic disk, terminal
	- Interrupt management and Direct Memory Access (DMA)
- The computer family IBM360 appears.

**OS goal: reduce I/O time**

**Methods**
- Buffering
- Spooling
	-  Simultaneous Peripheral Operation On-Line
-  Multiprogramming
-  Multitasking and time-sharing

*Buffering*
- Buffer to smooth out differences
	- Smoothes differences in velocity between supply and demand of data
	- Example: burn a CD, listen music from internet in streaming format, CD reading with anti-chock buffer
- Idea: Combine CPU and I/O of same process
	- example: Compress a file in blocks
		- Read block, compress and write
		- requires DMA to have overlapping CPU-I/O

![[Pasted image 20240330200013.png]]

*Spooling*
- Simultaneous Peripheral Operation On-Line
- Similar to buffering but
	- overlap I/O of one process with execution other process
	- Uses a zone of the HD as buffer
- Example: spooling the printer
	- Print file from editor
	- File is printed to a directory spool and the editor can proceed
	- Simultaneously (to editor) a so-called spooler program reads from directory spool and sends to printer
	- Note: overlapping editor computation with I/O of the spooler
- Requires two programs in memory

*Multiprogramming*
- Keep several programs in memory
	- The OS is one of them and occupies part of the memory
	- Each program occupies a part of the memory
	- Have to add mechanisms to protect one program to read/write over memory of other programs (at least the OS)
	- When one program requires I/O, another that is loaded in memory uses the CPU
	- Analogy: lawyers dealing with various cases in parallel

![[Pasted image 20240330200300.png]]

*Timesharing (multitasking)*
- Focus on improving interaction
	- The batch process
		- Prevents users to interact with the program
		- Difficulty of program cleaning (post-mortem)
	- Appearance of the terminal (keyboard+monitor) interaction makes sense
		- The command interface and file system appear
		- The user reacts on earlier results
		- Interaction with short response time required
		- The machine is expensive for just one user
- Multitasking: interaction at low cost
	- Computer with several terminals for several users
	- The CPU “multiplexing in time” the execution of processes

**The processes share the machine**

**Needed**
- Multiprogramming
-  Subsystem of the OS for planning (scheduler)

**Reducing cost due to**
- Intensive use of the CPU
- Computer bought for several users

**The batch systems are still used**
- Within multitasking
- For big jobs that do not require interaction.

**Multi-user systems: authentication and protection**

**A multi-user system is multitasking, but not necessarily the other way around**
- example: program for reservation of space: 1 program with several remote terminals.


**History of UNIX**
- Project MULTICS in 1965
	- MULTiplexed Information and Computing System
	- MIT and General Electric. the Lab. Bell join the project
	- Idea: supply computing power similarly to supplying electricity
	- in 1969 they give up; seems loss of money
		- Multics had already 20 millions of lines of code written in PL/I, but did not work well
	- Bell left Ken Thompson in the project
		- Implements a space travel game in FORTRAN
			- Time sharing slow. Playing on the GE-645 costs 75$ /hour
			- Transfer the game to a PDP-7 and make a file system
		- Named UNICS by Brian Kernigan.
			- Supports two users
			- Process scheduler and assembler

**Birth of UNIX: 1971**
- UNICS put on the PDP-11 re-baptized.
	- It has a working group
	- Decide to re-write UNIX in a high level language
	- Dennis Ritchie designs language B, but shows limitations
	- Ritchie and Kernigan design the language C
	- UNIX C-rewritten in ’73. there were 25 installations in that year
	- UNIX spreads due to Univ. affordable PDP-11
	- Many UNIX appear: System V, BSD, Sun-OS, Irix, Solaris.
	- Interface standard: POSIX (IEEE standard)
	- Linux appears due to executing UNIX on PCs and GNU
		- Development due to Internet: discussion groups and mail lists from 1991
		- GPL Licence of the Free Software Foundation. GNU.

## 4th generation
**Personal computers**
- Apple II and III 1980
- IBM-PC 1981, 8088 proc and MS-DOS

**OS for personal computers**
- PC-DOS (IBM) and MS-DOS (Bill) no multi-user no multitask
	- No file protection: birth of the virus concept
- the command interpreters make way for GUI environments:
	- Mono-user: increase productivity and improve efficiency
	- Windows 3.11 is a multi-program but mono-user system
- development of internet promotes
	- Distributed processes: users share PCs and devices
	- Going back to multitask and multi-user
- Databases replace filing systems

**Distributed OS**
- OS for a net of computers
- Provides the idea of a stronger unique computer
- Examples: Mach, Amoeba. They make place for:

**Middleware**
- Hull of SW runs over a net of computers
- Each computer has its conventional OS
- Examples: Corba (open-source), DCOM (Microsoft)

**Virtual machines: emulators**
- A machine emulates via SW another with other OS
- PC emulators: MAC, Commodore, Spectrum, MSX
- VMWare: Emulates a PC via SW. In this virtual PC one can install any PC OS
- WindowsXP has virtual machines for MS-DOS and Win16.
- Java runs on a virtual machine (JVM)

**OS for multiprocessors**
- Parallel systems provide speed and failure tolerance
- OS for asymmetric multiprocessing
	- One processor runs the OS and the rest runs processes
- OS for symmetric multiprocessing (SMP)
	- OS runs on each processor
	- the OS multithread (Linux, WindowsXP) support SMP

**Real-time OS**
- For managing the control of HW
- Limits in time: response time is limited
- Without HD no virtual memory. Typically storage in ROM
- Hard real-time: response time guaranteed
	- Requires all delay to be limited
- Soft real-time: critical processes have high priority
	- Requires planning with priorities


# Tasks of the OS
## Tasks of the OS
Manage the resources of the computer.
-  Manage processor: manage processes, disk transfer scheduling.
- Storage management, main & secondary storage allocation.
- Manage I/O.

Provides service for programming.
- Offers a set of services: system calls, API.
- Provides an extended machine.

Interface with the user.
- Provides a command interpreter (text or GUI)
	- Wait for a command (text or mouse click)
	- Analyses the command and executes when correct.
	- After finishing the command go for waiting again.

## OS as Administrator of Resources
Several processes share the HW
- One or several users.
- Processes compete for resources

The OS allocates the resources
- Checks which resources are occupied and when they are free.
- Physical resources: CPU, MEM, I/O
- Logic resources: files, ports

Protection among users
- Confidentiality of information
- Prevents interference of processes

Bookkeeping
- Monitor the use of resources

Decides between conflicting requests for efficient and fair resource use
## OS as Extended (or Abstract) Machine
OS offers service: syscalls
- Allows comfortable execution and protection
	- The alternative is complicated programming and no protection
- Type of service
	- Program execution: manage processes
		- Create, kill, communicate, synchronize, suspend, ...
	- I/O orders: open, read and write in devices
	- File operations: on higher abstraction level
		- Create, delete, rename, open, write, read and close
	- Detection and treatment of errors
		- The OS analyses all received commands before execution
		- Deals with different types of errors
			- I/O, execution (infinite loop, illegal call, ...) OS as extended machine tasks

## OS as Interface with the User
Command interpreter: shell
- Allows interaction
- Runs an infinite loop:
	- Wait order, execute when correct, goes back for wait

Two types of interpreters
- Text: window MS-DOS, terminal UNIX
	- Requires a user to remember the commands
	- Depends on OS: type (MS-DOS) equivalent to cat (UNIX)
- GUI: Graphical User Interface
	- Friendly environment of windows, icons, menus, buttons,...

File of commands (.bat or scripts)
- Automates executing a sequence of commands
- Control stream commands: for, if, case, ...

## Tasks of the Interface with User
Allows going into and handle the system
- Handle files and directories
- Program execution
- Tools to develop programs
- Communication with other systems
- Information about the state of the system
- Configuration of interface and environment
- Interchange data between applications
- Control of access
- Other utilities: calculator, mediaplayer,...
- system of interactive help

Question: which programs belong to the interface?

## Desirable properties of the OS
**Efficiency**
- Minimise idle CPU and response time
- Maximise use of resources and performance

**Reliable**
- Free of errors in any situation
- Tolerance with respect to errors

**Easy to maintain**
- Correction and actualization cost
- Modular design and well defined interface
- Well documented

**Small size**

**Portability to several platforms**

## Implementation of the OS
**The OS is complex SW**

**Two types of implementations**
- *Monolithic:* without clear structure and definition (multics)
	- Components are mixed without organisation
	- Difficult to change and extend
- *Structured:* two ways
	- In layers:
		- Each layer provides service using only the services of the lower layer
		- Easy development: each layer separately
	- Client-server model (microkernel)
		- The kernel of the OS contains basic tasks
		- The other tasks offer services (processes of the OS and daemons)

## Examples of implementations
**Implementation of MS-DOS**
- Monolithic: grew as spaghetti
- Initially aimed at using few space
	- Applications with direct access to HW
	- OS sensitive to imperfect programs
	- Easy and simple to access HW

![[Pasted image 20240330224151.png]]

**Implementation of Windows XP**
- Microkernel (client-server)

![[Pasted image 20240330224235.png]]

**Implementation of Linux**
- Monolithic Kernel to get to speed
-  Implemented in modules
	- Organised in independent loadable modules
	- Provides basic tasks
- he libraries of the system provide the rest
	- interface of syscalls POSIX
	- Functions: mathematics, handling lists, ordering, ... 

![[Pasted image 20240330224339.png]]


# HW Support
## Key HW Subsystems for the OS
The OS contacts directly the HW
- Most tasks of the OS is implemented in SW
-  Some tasks
	- Cannot be only in SW
	- Would be very slow if not in HW

Support HW for the OS
- user/system mode
- Interrupt system
- Direct Memory Access (DMA)
- Clock of the computer
- Storage hierarchy

## User/System mode
Two ways of execution. Flag in register:
- *user:*
	- Available in a set of machine instructions
	- Some part of the registers cannot be accessed
	- Forbidden zones in the memory
	- Control of I/O (ports of I/O) forbidden
- *supervisor, system, monitor, privileged mode:*
	- No restrictions. Total access to HW.

Two execution modes:
- The OS runs in *system mode*
-  User processes run in *user mode*
	- If a user process performs a syscall we go for system mode if that syscall belongs to the OS

![[Pasted image 20240330225054.png]]


## Interrupts
Control information mechanism
- interrupt request line, checked after each instruction execution
- Device controller can raise an interrupt to the line
On the event of interrupt (int. vector)
- The instruction in execution is put on hold
- The cycle after accepting the interrupt
	- Save Program counter (*PC*) and Stack register (*SR*)
	- Change for system mode
	- Load interrupt-handler routine (*IHR*) in PC
	- Address in table of interrupt vectors
		- Table has an index to link to the agent that interrupts
	- The IHR saves rest of registers (processor state)
	- IHR runs process indicated by vector and when ready
		- Recuperates PC and SR (return to user mode)
		- The interrupted process resumes its task

The IHRs and table of vectors belong to the OS
![[Pasted image 20240330225933.png]]

## Classification of interrupts
- Nonmaskable vs. maskable: can be turned off by CPU before critical instruction sequence.
- SW or SVC (call to supervisor) interrupt
	- Actually a syscall (to OS) (“trap” or “int”)
	- Not done as “call” (call to subroutine)
		- We move to system mode and the process does not have access to addresses in OS memory space
	- The interrupt vector provides the instruction
- HW Int: activates a side door of the micro (int)
	- Of I/O: device controllers, DMA, ...
	- Of reset: nonmaskable. Different side door (reset)
	- Of the clock: Allows the OS to run regularly
- Exceptions: generated by the processor
	- SW: divide by zero, overflow, illegal CO, page fault,...
	- HW: overheating, power peak
	- The interrupt vector belongs to the processor

## Direct Memory Access (DMA)
Allows maximum concurrency CPU-I/O. Development:
- I/O programming: zero concurrency
	- processor is in charge of all the transfer
- I/O by interrupts: average concurrency
	- Processor avoids waiting device action
- I/O by DMA: maximum concurrency
	- Processor in charge of I/O operation to DMA
	- CPU and DMA work in parallel: concurrency CPU-I/O

![[Pasted image 20240330230406.png]]

## Clock of the computer
There are three visions of the clock:
- Frequency of instruction execution in the processor
- Generator of regular interrupts
	- The OS is run regularly
	- Avoid processes to get hold of the processor
- Counter: updates time and day
	- Counter of seconds from a certain date
	- Unix counts from 1/1/70. A 32 bits counter is again at zero in 2038.
![[Pasted image 20240330230518.png]]

## Storage hierarchy
- Blocks of data replicate at several levels of the hierarchy
- Goal: simulate big, fast cheap memory
- The CPU generates virtual addresses
	- Part of the memory map is in Mem. and part on HD
	- MMU translates. Virtual addresses in physical ones
	- Page fault if address is not in main memory
	- OS brings a page to Mem (possibly replacing in Mem)

![[Pasted image 20240330232839.png]]

# Starting up the PC
- *Reset* loads predefined values in the registers
- PC -> address for starting up the loading of ROM
- Run loading ROM of the system
	- Test the system
	- Bring boot memory of the OS
![[Pasted image 20240330233011.png]]

- For a PC the ROM contains also I/O SW (BIOS: Basic Input Output System)
- The ROM loader fetches the boot sector of the OS and gives it to the control
- boot of the OS loads the resident OS:
	- Tests the hardware
	- tests the file system
	- Produces the OS tables
	- Creates processes (OS-like)
		- process INIT
		- processes of system tasks (deamons)
		- Login processes (one per terminal)
	- After authentication, the login process becomes shell

![[Pasted image 20240330233140.png]]

# A VIRTUAL ENVIRONMENT FOR PROGRAMMERS
## Why an OS?
**Embedded systems**: 
- You have few resources (low power CPU and few bytes of memory). 
- You only solve one simple problem with a microcontroller. 
- You only run a single program.
**Desktops**: 
- You have more resources than needed for one single program. 
- You need to do many things at a time (multitasking). 
- You run several programs at the same time. 
**Servers**: 
- You have more resources than needed for one single program. 
- You need to do the same operation for thousands of users (multiprocessing/multiuser). 
- You run many instances of the same program at the same time.

## Resources & Security
- We have multiple running programs (at the same time) from different developers, so *we need arbitration of resources (CPU, RAM, I/O) between programs*.
- We have multiple persons using a system, thus *we need protection of data (users, owners, permissions)*
- High Level Abstractions:
	- Developers want transparent implementation of abstractions. 
	- Develop on a private machine with its own CPU, RAM and I/O.

# Components of an OS
## Users' POV
The user only interacts with programs through devices of the computer (mouse, keyboard and screen); and only interacts with the UI of applications. There is different types of apps (*from user's POV*):
### User Interfaces (UI)
UI is what the casual user thinks that the computer is:
- a monitor mouse and keyboards interacting with a GUI (Desktop Graphical Environment)
- a text console with a CLI (Command Line Interface)

A developer sees a UI as a program that starts programs.
![[Pasted image 20240401201401.png]]

### System applications
- A system application is a software that does some basic management task and comes inside the OS installation.
- Usually for managing the OS and doing very simple tasks: manage files, edit a text file, display an image, install more applications…

### User applications
- Software that must be installed after OS installation and is not part of the OS distribution: office& business applications, browsers, development environments, etc. 
- Commercial software falls inside this group.

## Developers' POV
A developer writes programs to run on top of an OS:
### Processes
A process is a running instance of the executable file of one program (generated by the compiler and the linker). The executable includes the machine code of library functions used in the code. For a developer, two processes running the same code are “different applications”.

### System calls
They are functions that form the API of the kernel. The machine code is not included in the executable file, but in the **kernel image**.

### Kernel
- Contains the implementation of all the *system calls*. The kernel machine code is loaded at boot from disk (from the *kernel image file*).
- A kernel can be recompiled if code is available (e.g. Linux). Testing a new kernel requires rebooting the system.

## Processes & Kernel
![[Pasted image 20240401201819.png]]

## System designers' POV
There are these types of kernels:

### Monolithic
- It is a large code with plenty of interrelated functions (e.g. terminating a process involves also freeing memory and closing open files).
- *Primitive UNIX was monolithic.*
![[Pasted image 20240401202130.png]]
### Modular
- **Modules**: It is well structured in modules offering different types of resources.
- **Device drivers**: Each device has a separate module for implementing specific control dialog needed to implement I/O with it.
- **Syscalls**: The API for programmers is another separate module.
- *Current UNIXes and Linux are modular.*
![[Pasted image 20240401202420.png]]
### Microkernels:
- $\micro$kernels try to keep the kernel as simple as possible implementing **policies** and system services **outside the kernel** (as user processes) if possible.
- *Windows NT 3 was a micro kernel but a failure because of inefficiency.*

$\micro$kernel concepts:
- **Mechanisms**: 
	- One fundamental service of the Kernel that is needed to implement policies and can’t be implemented outside the kernel.
	- For example, memory allocation, assigning the CPU to execute one code, or read/write an I/O controller register, send a message from one process to another.
- **Policy:**
	- One specific strategy to manage resources.
	- A policy is implemented in a privileged process with rights to use *mechanisms*
	- A policy publishes *services* to other non-privileged processes through the µKernel
		- A user process asks ➔ the micro kernel to perform a service.
		- The micro kernel ➔ redirects the request to the selected policy.
		- The policy process ➔ uses micro kernel mechanisms to implement the policy.

- A micro kernel can replace a policy just by replacing a running process.
- It might be possible to run driver code as a process.

*The idea is very brilliant but nobody has been able to implement it efficiently.*

![[Pasted image 20240401203438.png]]

#### Windows NT3.1 (1993)
![[Pasted image 20240401203521.png]]

## Languages
The most used languages are:
### Assembler (< 1%)
Used for:
- CPU and Interrupt context handling
	- Saving and loading *all* CPU registers onto RAM. (All means all: general purpose, floating point, vector units, processor status, MMU control for page tables, etc)
- Optimized bits of kernel code using special CPU instructions:
	- Copy blocks of RAM efficiently. 
	- Fast arithmetic using SIMD instructions (MMX, SSE2, SSE3, SSE4)

### C Language (>99%)
- Used on everything else.
- Simple data structures (tables and linked lists).
- Static memory allocations (fast).


The next languages are not used:
- *Interpreted languages*, because they have garbage collection (thus not predictable)
- *Object languages*, abstraction makes difficult to keep control of low level implementation.

# CHARACTERISTICS AND TASKS OF OS
## Services & Policies
### Services (System calls):
It is what you want the system to do. Examples:
- Create a process, kill a process.
- Get memory (malloc), free memory.
- Open a network socket, send/receive data.
- Create a file, read/write data.

### Policy
Refers to how the system will manage and perform what you want to do. Examples:
- How to distribute the CPU time between processes.
- How to distribute the RAM of the computer between processes.
- How to organize the sectors of the disk between files.

## How programmers use services
Services are functions belonging to the OS kernel:
- Kernel code needs to be executed in privileged mode (see next section). 
- Calling a kernel function is slightly different from calling a normal function.
	- Functions are called with an instruction to call a subroutine (x86: *CALL*).
	- Syscalls are called with an instruction to generate a software interrupt (x86: *INT*).

In C, syscalls are called using functions that contain INT instructions (libc.a).
- These functions are written in assembler.
- Example of calling syscalls from C code:
```C
	int file=open(“t.dat”,O_WRONLY|O_CREAT); 
	l=read(file,&var, sizeof(var)); 
	close(file);
```

## Services in POSIX standard
**Process management:** 
- Creation and destruction of processes is requested by other processes. 
- User interfaces (command consoles and graphical UI) are programs that use these syscalls to request the kernel to start and kill other programs. 
- Other languages use POSIX syscalls to implement their threads support.

**Memory:** 
- Request change in size of the memory of the current process. 
- The C malloc() and free() functions use sbrk() to ask the kernel to enlarge the memory size of the current process. 
- Language compilers with objects implement new() and delete() as calls to malloc() and free(). 
- Language interpreters (JVM, Python, etc.) implement memory management as calls to malloc() and free(). 

**Files:** 
- POSIX files are modelled as abstract streams of bytes. read() and write() offer a sequential access pattern by default. 
- Random access is achieved using lseek() to set the offset for next read() or write(). The C fopen(), fgetchar(), etc. functions use open/read/write() to implement files. 
- Other languages (JVM, Python, etc.) use C files or POSIX files to implement their file support.

**Concurrency support**
Processes can request the kernel to create some communication object to interact with other processes (shared memory, message queues, semaphores). Other processes can request the kernel to access the existing object if the have access permissions.

- *Shared memory*
	- By default, processes can never access the memory of other processes. 
	- This allows sharing a block of memory between processes.

- *Semaphores*
	- This allows arbitrating concurrent access to shared resources.
- *Message queues*
	- This allows to write consumer-producer algorithms by sending messages (blocks of bytes).

**Network**
- TCP and UDP sockets are modeled exactly the same as files do (an abstract stream of bytes). The only difference is that lseek() has no sense on a connection.
- An open socket can be used with write() at sender side and read() at receiver side.

**Security**
- The concept of *owner* is an integer identifying each object (processes and files).
- Security based in comparing *owner* of *the process calling the kernel* and *owner* of *the object affected by the syscall*.
- Example: open a file (if the process has the same owner than the file, it will use the user permissions; if not, it will use the group or other permissions).
- Example: kill a process (if the process has a different owner than the target process, it will fail)

# HW Support for OS
## CPU support for protection
An OS kernel must implement *protection from user code*. 

**Protection** means that:
- The code of a process (*user’s code*) can never access (read or modify) neither **hardware configuration** nor **kernel data structures**.
- The code of the kernel (*system’s code*) can always access both hardware and kernel data structures.

The kernel needs the CPU to distinguish when it is running *user* or *system* code:

**System/privileged/supervisor/kernel mode:**
- The CPU can execute any instruction without any restrictions. 
- The kernel code runs in this mode.

**User/unprivileged/restricted mode:**
- The CPU can only execute a subset of the instruction set with control of memory addresses. 
- Code of user processes run in this mode.
## CPU modes
The Status Register of the CPU contains a bit field that indicates the *current privilege level*. These are the CPU modes:

### User mode
The CPU can’t execute a subset of the instruction set with instructions considered privileged (harmful for the Kernel or the hardware devices).
- Privileged instructions: Reset or Halt CPU, change the CPU mode, program the MMU, disable IRQs, Input and Output to Hardware registers, etc
- The CPU will generate an *exception (internal interrupt)* when an illegal instruction is decoded. The CPU jumps to the interrupt handler which *kills the offending process immediately*.

The *MMU* (Memory Management Unit) checks the address of each memory access.
- Memory addresses not belonging to the current process are illegal.
- The CPU will generate an *exception (internal interrupt)* when an illegal memory address is found. The CPU jumps to the interrupt handler which *kills the offending process immediately*.

### Kernel mode
Restrictions are fully disabled in privileged mode (no instruction or address checks).

## MMU (Memory Management Unit)
The Kernel implementation needs to check *legality* of *each memory reference*. 

**Legality** means:
- If the CPU is in *privileged mode*, any address pointing any existing memory position is valid.
	- The kernel can reference any memory (kernel or processes).
- If the CPU is in *user mode*, only addresses pointing to the memory of the current process are valid.
	- A process must not access Kernel memory.
	- A process must not access other process’ memory.

**MMU:** The CPU needs a special hardware unit to check every memory access cycle (instruction fetch or movement of data).
- Each address must be checked before the actual access. 
- It must be very fast to avoid slowing down memory cycles. 
- It must generate an exception in the CPU when the address is not legal.

## How to switch CPU modes
**User mode**
The only way of changing to privileged mode is to generate a *software interrupt* (INT \#n).
- Mode is set to privileged, but it enters an interrupt handler function which belongs to the kernel code. Previous mode is saved into the stack.
- The table with addresses of interrupt handler functions can’t be accessed in user mode.

**Privileged mode**
There are instructions to change the privilege level:
- *POPFD*: Pop the CPU status from the stack.
- *IRET*: Return from interrupt handler and pop CPU status from the stack. Mode is set back to the one prior to entering the handler.
#### Summary
- A process must call the kernel code using a *INT \#n* instruction (system call). New mode is *privileged*. Previous mode is *saved on the stack*. 
- The called function of the kernel code is fixed by the *interrupt vector table* of the processor (set by the kernel code). The code is executed in *privileged mode*. 
- At end of system call, kernel code returns to user code using *IRET* instruction which restores from stack IP and CPU status. *User mode is restored from stack* and user code continues.

### External Interrupt Requests
- The interrupt cycle is identical to the software exception cycle.
- Mode is set to privileged.
- Previous mode is saved into the stack.
- Jumps to an interrupt handler function which belongs to the kernel code.
- The table with addresses of handlers can’t be accessed in user mode.
- IRET: Return from interrupt handler and pop CPU status from the stack. Mode is set back to the one prior to entering the handler.

#### Summary
- A process is interrupted by an external I/O controller. Current instructions is ended and CPU jumps to a handler functions.
- New mode is *privileged*. Previous mode is *saved on the stack*.
- The called IRQ handler is fixed by the *interrupt vector table* of the processor (set by the kernel code). The code is executed in *privileged mode*.
- At end of IRQ handler, kernel code returns to user code using *IRET* instruction which restores from stack IP and CPU status. *User mode is restored from stack* and user code continues at the next instruction.

The **Kernel code may be interrupted** by an external IRQ. Privileged mode is saved on the stack and restored at IRET.