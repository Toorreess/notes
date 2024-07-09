# 1. OS Concepts
An **OS** is software that acts as an intermediary between the computer user and its hardware. A set of programs that facilitates and coordinates the use of computer hardware efficiently and fairly between users.

![[Pasted image 20240330193423.png]]

A computer system is the hardware and software for automatic data processing. It is composed of:

![[Pasted image 20240330193456.png]]

**Views on the OS:**
- *Top-down*: extended machine (user's view)
	- Looking down you see an easy to use machine.
	- The OS provides the tools to use the HW comfortably.
	- Without the OS, we should program the naked machine.

- *Bottom-up*: Manager of the hardware (system's view)
	- View from the HW POV.
	- SW that manages an does administration of HW (CPU, MEM & I/O)
	- The OS controls the HW.
	- The OS should allocate resources without consuming them.

# 2. History of OS
## 2.1. $1^{st}$ generation: *Vacuum tubes*
OS did not exist yet. Switches, lights and plugs were available as *entrance* and *exit*; the first being the ENIAC. The programmer was responsible for loading the program in binary format, executing it and analyzing its output.
- **Compilers (A-0, Arithmetic Language V0)**, simplified programming, but made execution and loading difficult; It was necessary to hire a chart operator to compile all the programs, put them together and, finally, execute them.
## 2.2. $2^{nd}$ generation (1955-65)
- **Transistors**.
	- Counted on some loss between the end of one task and the next; to solve this, the battery system and a resident monitor (first OS) were used.
	- Time was also lost in reading the letters, so we opted for offline execution, converting letters into magnetic tapes.

- Hardware support for OS begins with:
	- Interrupts, to manage exceptions and I/O efficiently.
	- Timers, to not allow a task to monopolize the CPU.
- Strachey's proposals:
	- Memory protection, to prevent access to the monitor.
	- Privileged instructions, for the integrity of the system by the monitor.
## 2.3 $3^{rd}$ generation
- **Integrated circuits (ICs)**, along with new technologies for direct access to memory and magnetic disks, terminal (monitor and keyboard). To reduce the time it was proposed:
	- **Multiprogramming**, several programs in memories to overlap inputs and outputs, it had: memory protection for the memory spaces of other programs and planning, scheduling. ![[Pasted image 20240330200300.png]]
	 - **Multitasking**, CPU shared between processes, so that programs without I/O are prevented from monopolizing the CPU. For multi-user programs they require authentication and protection of memory and CPU.
	- The **MULTICS** (MULTIiplexed Information and Computer System) project gave rise to **UNICS** (Uniplexed ICS), initially single-task and in assembly language; and as a consequence, UNIX (1971**),** **B** and **C** (as successor to B), whose standard interface was IEE POSIX. Years later (1983) the Linux kernel for GNU appears, whose **GNU/Linux** combination.
## 2.4. $4^{th}$ generation
- Very Large Sacle Integration (**VLSI**), microprocessors on a chip, giving rise to operating systems for **personal computers**. Initially the OS for Personal computers did not support multiprogramming or multitasking. Of this
rise of the processors, different types of OSs can be observed.
- Types of OS
	- **Distributed**, for networks of computers that together could be seen as a great computer.
	- For **multiprocessors**, providing parallelism, multithreading (SMP, Symmetric multiprocessing).
	- In **real time**, with priority scheduling.
	- **Mobile phones**, which have limited resources.
	- **Virtual Machines** (VMs), different OSs in one VM at the same time with a OS in hypervisor mode to manage the rest.

# 3. OS Functions & Services
## 3.1 Computer Resources Management
A *process* (the execution of a program by one or more users) shares HW to compete for resources. The OS must be in charge of tracking resources to manage the allocation of HW and logistical resources.
- Managing a process carried out by the OS will involve the creation, completion, planning, synchronization and communication (IPC) between processes.
- Memory management will carry out allocating, deallocating and swapping memory spaces.
- File and I/O management will provide a driver and file system interface.

It must strengthen user protection, preventing process interference and protecting confidential data.
It must also carry out the bookkeeping task (Monitor Resource Uses).

## 3.2 Programming Services. Syscalls
The services that must be offered:
- **Program execution**, system call management.
- **I/O operations**: open, read, wtite, ... peripheral devices.
- **File system manipulation**, as an abstraction layer.
- **Detection and management of errors**, in *I/O* (disk parity) or *CPU* (overflow, illegal operation code, ...).

## 3.3 User Interface
- Textual, with access through a Shell and commands to communicate with the computer (.bat or scripts)
	- The Shell is the command line interpreter
- Graphic (GUI, Graphical User Interface), user-friendly.

## 3.4 Implementation
- **Monolithic** (MS-DOS), without a clear structure, difficult to modify, but fast because they are in the same memory space.
- **Structured** (Windows):
	- By layers.
	- Microkernel, client-server model.
	- Modules, which can be loaded dynamically, without the need to recompile or reboot.
- **Hybrid** (Linux), partially structured and monolithic.

# 4. HW Support for OS
The hardware provides the OS with functionalities such as *user/kernel mode*, *interrupts*, *DMA*, *timer* and *memory hierarchy*.
## 4.1 CPU's Modes
A flag (bit) indicates in the status register whether we are in user or kernel mode.
- **User mode**, using a *subset* of *machine instructions*, some registers cannot be accessed, with restricted memory accesses such as I/O ports.
- **Kernel mode**, supervisor or privileged mode, without such restrictions.

![[Pasted image 20240330225054.png]]

## 4.2 Interrupts
The OS runs when they are activated, transferring control between applicati1ons and the OS, its general procedure is:
1. **Hardware**
	1. Saves the PC (Program Counter) and SR (State Register).
	2. Switch to kernel mode.
	3. Loads the address of the interrupt handling routine (IHR) into memory.
		1. The address is obtained from the vector table (IVT).
		2. The IVT is indexed with the vector number.
2. **Software**
	1. The routine saves the state of the process.
	2. The IHR is executed.
	3. The process state is restored.
3. **Hardware**
	1. User mode is restored with PC and SR previously saved.

Interruptions will be managed according to their priority. They can also be classified into:
- **Software interrupts**, syscalls, with int or trap instructions.
	- int 0x80, syscall to 80 address
- **Hardware interrupts**, INT or IRQ, by I/O devices, resets or timers.
- **Exceptions**, generated in the microprocessor by software (divide by 0, invalid opcode...) or hardware overheating, parity error...). The processor itself gives the vector.

![[Pasted image 20240330225933.png]]

## 4.3 Timer
An oscillator serves directly to the processor as a **clock** and its divided frequency allows a **counter for interrupts**. A counter can be used to count seconds from a date or as a tick generator. Its uses include allowing **multitasking** and **protecting the CPU** by controlling the time spent on tasks.
## 4.4 Direct Memory Access (DMA)
Allows **multiprogramming**. It evolved along with the I/O until reaching **maximum concurrency**. To carry out the operation, the controller will have the address of the device, memory and amount of data to be transferred.
![[Pasted image 20240330230406.png]]
## 4.5 Hierarchy & Protection Memory
The operating system takes care of the mapping between virtual and real addresses. To provide management, there is a memory management unit (**MMU**), to accelerate the translation of addresses (Page walk, TLB) and exceptions (illegal accesses or lack of pages in memory).

![[Pasted image 20240330232839.png]]

# 5. System boot
When you turn on or press the reset button, the boot loader (system boot) is started, which is executed in the ROM, testing the system and copying the boot block from the disk to main memory, with the operating system boot loader and initializing the values ​​of certain registers. Personal computers usually have a ROM-BIOS and an I/O firmware (BIOS/UEFI).

The operating system's Bootstrap program is responsible for loading the operating system resident.
1. Hardware Test
2. Test system files
3. Initialize operating system structures.
4. Create processes.
5. After authentication, the login process becomes the Shell.