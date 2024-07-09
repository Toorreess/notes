# 1. I/O Concepts
## 1.1 Basic concepts
Periphericals have mechanisms and electronic devices that are controlled via electric signals. Some simple periphericals obtain all signals for the mechanism control from the outside. More advanced periphericals have their own logic up to a microprocessor and receive commands on higher level, e.g. SCSI disk, postscript printers.

Periphericals are not directly connected to the internal system bus. They require I/O controllers:
- Communication requires other signals than that of the processor bus (*signal adaptation*)
- The I/O controller can elaborate operations on its own (e.g. transport of blocks of bytes)
- Having several periphericals connected to the same controller requires an I/O bus. There are several standards for that: *USB*, *ATA*, *SATA*, *PATA*, *SCSI*, *FireWire*, *FC*, *IDE*...

### Connection example
Bus SCSI bus connected to a SCSI hard disk

![[Pasted image 20240709181458.png]]

- Intelligent controllers (I/O controller and peripherical logic) make the CPU not to be involved in I/O, such that parallelism between I/O and CPU is stimulated.
- CPU sends commands to controller via instructions IN y OUT. Controller sends commands to peripherical logic.
## 1.2 Storage devices
### 1.2.1 Storage Systems (StS)
Connection buses between StS are highly standardised to interact: high (SCSI/SAS) and low (IDE/ATA) performance.
### 1.2.2 Hard disk
- Present in many systems
- Magnetic disks
- Up to Terabytes of storage

### 1.2.3 Insertable devices
- Pendrive, floppy
- Optical device
- backup tapes

### 1.2.4 Tape libraries
Backup in computer centres

## 1.3 Definitions
- **Physical device**: any peripherical connected to a computer. Each device requires other media to communicate.
	- depending on: connection bus type (IDE, SCSI, USB, ...), peripherical type (disk, magnetic tape, scanner), up to the producing company.
	- requires specific software for each peripherical: device driver.
- **Device driver**: specific software for communication and access to a physical device.
	- offers same interface on each platform for the same device to control: initialise, read, write, configure.
- **Logical Device**: the SO kernel provides high level (syscall) functions to the processes, to unify all variants of the same type of peripherical (hard disks, magnetic tape, printer...).
- **Unit**: each physical device connected to a logical is a unit.
	- SO provides names to access logical devices. In UNIX files in dir. /dev: hd*, tty*, dsp*.

### Example
![[Pasted image 20240709182100.png]]


- The two disks from OS perspective see each other as a logical device
- As they use different technology, they require different drivers
- The OS knows how to map files in each unit; hda or hdb.

## 1.4 OS Services for I/O
- From OS perspective, the standard operations for all periphericals: read,write,open,close.
- I/O redirecting is independent of device.

There are 2 I/O modes for the OS:
- **synchronous / asynchronous**
	- *Synchronous*: process request I/O stays blocked up to end I/O.
	- *Asynchronous*: process leaves syscall and continues execution. The OS takes care of the operation.
- **Buffering / Spooling** to optimise slow devices
	- *Buffering*: written data are saved by the OS in a buffer and written asynchronously overlapping I/O with process execution; for streaming, terminal (teletype), modem series, etc
	- *Spooling*: written data are saved in a temporal file (spool). SO takes care of writing the fule to the real device. The corresponding writing process is called a spooler.

# 2. Device Files
In linux all devices are files.

Device nodes and special files lets us devices like a file.
- Redirect: cat file > /dev/dsp
- Use system call: open, close, write, read

There are 3 ways to do so:
- **Character**: Communicate character by character.
	- for streaming, terminal (teletype), modem series, etc.
	- does not allow random access.
	- typically no buffer use.
- **Block**: Communicate blocks
	- hard disk, CD-Rom, DVD, memory regions
	- Random access and via buffer (to form a block to send later)
- **Pseudo-devices**
	- – /dev/null, /dev/zero, /dev/random
	- /dev/loop: permits block access of a file: allows mounting a file system as file

# 3. Disk Management
Each platter has tens of thousands of tracks in concentric circles. Each track has tens of thousands of sectors containing the minimum access unit (512 bytes). In current hard disks, more sectors are packed into the outside tracks (zoned recording).

## 3.1 Access to the hard disk
All disks are sets of sectors numbered 0 to N-1. Sectors may be of different size. To standardize:
- *Sector*: minimum size unit for transport between disk and memory. Typically 512B (4K from 2011).
- *Block*: minimum unit to assign space on disk, tape, file etc. Should be at least one sector.
- *Disks* should be able to transport several consecutive sectors at once between disk and memory 8Kb, 16Kb, 32Kb, etc

## 3.2 Partitions
It is convenient to divide the disk into various parts for different use:
- Different file systems.
- Swap space.

**Partitions** are managed by software.

![[Pasted image 20240710001405.png]]

Many partition table formats due to OS development.
- most typical is the nowadays PC based and used by BIOS (called MBR or PC BIOS).
- UNIX servers have seen many formats. At the moment Linux servers use MBR by default.
- Development of Intel’s GUID (part of the EFI (Extensible Firmware Interface) standard that replaces BIOS).

Support:
- Windows supports MBR scheme (PC-BIOS).
- Linux supports PC-BIOS, GUID and can handle almost tens of old UNIX formats. Can also format using the main remaining file systems: IRIX, Sun Disklabel, UFS.

## 3.3 MBR
MBR contains:
- Start up code to run after starting BIOS
- Table of main partitions

![[Pasted image 20240710001613.png]]

## 3.4 Partition table
4 entries of 16 bytes
- 1 status (0x80 = bootable, 0x00 = non-bootable)
- 3 CHS address of first block in partition
- 1 part. type (0x07 NTFS, 0x83 Linux, 0x82 Swap)
- 3 CHS address of last block in partition
- 4 LBA of first sector in the partition
- 4 number of blocks in partition

addressing CHS -> LBA not used anymore
- maximum number of blocks is $2^{32}$.
- Not this limit using GUID Partition Table (GPT)

If more than 4 partitions required
- Make extended partition using an EBR (ext BR)
- Within that make logical partitions

# 4. File Systems
The file system provides a cooked vision on I/O via de disk driver. Each partition can be considered another file type system if the kernel has the corresponding code

![[Pasted image 20240710002014.png]]


## 4.1 Managing disk space
A partition has small internal data zones for the OS (superblock, start up block, FS desc.).

Non metadata blocks are for file data storage. Blocks may contain:
- FS control structure 
- data blocks
- free blocks: not yet assigned to a file

Two main methods to check which blocks are free and which assigned
- List of block numbers, FAT: File Allocation Table.
- Bitmap of free blocks.

## 4.2 FAT (File Allocation Table)
- As many entries as blocks in the disk or partition.
- Number of each entry gives link to next up to number -1.
- To describe a file, we only need to store the first block number and its length in bytes.

## 4.3 File Attributes
- *Name*: easier to remember than a number
- *Type*: necessary for systems that support different types, e.g. file, directory, device, softlink
- *Size in bytes*: as we assign in blocks, one should know the exact size; internal fragmentation in last block
- *Protection*: user and group it belongs to and access control
- *Date*: creation, last change, last access
- *Location information*: where is it on disk

## 4.4 Volume management
Disk partitioning may be useful.
- data separation: 
	- Loosing a partition is not loosing all 
	- Protect via read-only
- may promote storage efficiency:
	- different block sizes for different partitions
	- locality promotion placing associated data and programs together
	- smaller partitions are faster, NTFS: New Technology File System
- different OS, file systems, swap possible
- bounds data increase
	- avoiding user data eating all space
- drawback: fixed size, high level fragmentation

## 4.5 Logical volumes
File systems located in partitions are limited when they fill up by data.
- space may be available in another partition
- blocks have to be contiguous in disk
- extension to other disks impossible
- changing size implies copy (backup), delete, copy back

solution: use abstraction layer over the partitions and disks where file systems are stored
- generated partitions are called Logical Volumes
- the layer is called Logical Volume Manager

## 4.6 Kernel data structures
File system requires linked data structures to locate data blocks.
- Data structures must be on RAM to be used 
- Data structures must be on disk for space and persistence reasons

Solution:
- There are cached copies of disk structures in RAM 
- Private to the kernel code

Kernel I/O structures 
- RAM i-node (i-node cache) 
- RAM data block (block cache)
- System-wide Open File Table 
- Process-private File Descriptor Table 
- *Indices* of i-node and *data caches* 
- *Mount table* for mounted file systems

Interesting details
- Many processes may have descriptors pointing to the same open file (inheritance).
- Processes may open the same file twice with different permissions (descriptors point to different open files) with the same i-node.
- Many structures have a reference counter to know that they are pointed by another structure
	- Open files have it. 
	- i-nodes have it. 
	- Mount
- i-node and data caches have dirty state, writeback policy and replacement policy. 
	- Trade-off: efficiency vs. data integrity

## 4.7 File System Integrity
File system integrity 
- Longer write-back time increases disk efficiency 
	- More reuses of same data blocks 
- Shorter write-back time increases integrity 
	- Updated data is only in RAM for a shorter period 
- Unix flushed any block after 30s 
	- Caches are also flushed when unmounting!!! 
	- OS unmounts all file systems when shutting down. 
- Write-back order of file metadata is critical 
	- File/directory creation and removal are dangerous: dangling pointers. 
	- File systems are checked after boot to check for dangling pointers: fsck 
	- Current file systems use an auxiliary transaction log like databases: **log file systems**

## 4.8 I/O Requests
I/O devices provide operation queues 
- Asynchronous I/O (process thread is free to continue before I/O ends) 
- I/O operations are described by an I/O request 
Implementation 
- System call queues I/O request onto device queue. 
- IRQs process I/O requests (dequeue, enqueue). 
- Terminated requests are queued onto process return queue. 
- Synchronous syscalls just sleep waiting for I/O request to be returned to process