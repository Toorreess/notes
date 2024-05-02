# 1. Introduction to Scheduling
## 1.1 Effects of scheduling policies
Processes don't execute alone on a system:
- OS uses to run many other basic processes.
- If there are more processes than CPU cores, they must be *shared*.
- Sharing a CPU core requires turns, so some processes *run* and some *wait*.

Some programs require a predictable behavior:
- An editor must update the screen (**compute**) immediately *after* reading a key press (**I/O**).
- A real-time control program must calculate answer (**compute**) to sensor data (**I/O**) in a limited time.
- Other non-critical processes can *steal* the CPU when it is really needed.

> **A scheduling policy guarantees that the system will assign the CPU to the right program. (…or expects to do that most of the times)**

## 1.2 Study of scheduling policies
The purpose of a scheduling policy is to decide which is the next *READY* process to enter the *RUN* state and when is has to enter the *RUN* state.

Parts of a scheduling policy:
- *Preemption:* Deciding when a process exits the RUN state.
- *Dispatcher:* When it's decided which process will enter the RUN state after preemption of current one.

![[Pasted image 20240425132029.png]]

A **policy**,
- Is a set of rules to take decisions when writing the kernel code that implements the scheduler. 
- Different rules make different schedulers.

**Comparison between different policies**:
- Each policy generates a different decision for the same situation -> Future evolution will be different.
- We use statistics to *measure* the *average behavior* after many thousands of decisions on thousands of processes.
- We can predict the effect (or expect one desired effect) that each scheduler will have on the execution of one specific program.

# 2. Scheduler Types
## 2.1 Scheduler Types as term-related
![[Pasted image 20240425133003.png]]



