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


## OS as Extended Machine
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