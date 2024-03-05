# Why an OS?
## Embedded systems:
- You have few resources.
- You only solve one simple problem with a micro-controller.
- You only run a single program.

## Desktops:
- You have more resources than needed for one single program
- You need to do many things at a time (**multitasking**)
- You run several programs at the same time.

## Servers.
- You have more resources than needed for one single program.
- You need to do the same operation for thousands of users (**multiprocessing / multiuser**)
- You run many instances of the same program at the same time.

# Resources & Security
- We have multiple running programs at the same time from different developers:
	- Thus, we need arbitration of resources (CPU, RAM, I/O) between programs.
- We have multiple persons using a system:
	- We need protection of data
- High Level Abstractions:
	- developers want transparent implementation of abstractions.
	- develop on a private machine with its own CPU, RAM and I/O
