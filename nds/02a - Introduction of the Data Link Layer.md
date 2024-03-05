It defines the data **communication between two adjacent nodes** (sharing the same transmission media). Provides the next services:

# Multiple Access
In point-to-point links:
- *Half-duplex*.
- *Full-duplex*.

In broadcasting topologies, it is needed a **MAC (Multiple Access Control)** protocol to compete for a shared resource: the bus.

# Flow Control
It is the process of managing the rate of data transmission between two nodes to **prevent a fast sender** from **overwhelming a slow receiver**. 

Classification:
- **Prevention**: Buffers, *PAR* (Positive Acknowledge with Retransmission), flow control windows...
- **Detection**: Time-out (timer).

# Error Control
## Detection
Technique to detect data that is corrupted during transmission.
### Parity-check bits
An extra bit, called the **parity bit**, is added at the end of the data packet. Used in ASCII code (7+1 bits).
- *Odd parity bit*: if the count of 1s is odd the parity bit is set to 1.
- *Even parity bit*: if the count of 1s is even the parity bit is set to 1.
### CRC (Cyclic Redundant Check)
Widely used (Ethernet, 802.11 WiFi, ATM...)
- $d$ bits of data ($D$) to be sent + $r$ redundancy bits ($R$).
$$D \cdot 2^r \ XOR\ R$$
- The operation goal is to choose $r+1$ bit pattern (generator $G$), picking the first $r$ bits such that
	- <D,R> is exactly divisible by G (modulo 2)
	- Receiver knows G, divides <D,R> by G. If non-zero remainder, *error detected*

### Checksum
Used in IPv4, TCP and UDP:
- The sender treats segment contents as sequence of 16-bit integers, which adds and inverts to 1's complement format.
- The receiver computes checksum of received segment, revealing an error depending on the result: **0, correct; 1, error**
## Correction
Enables reconstruction or retransmission of the corrupted frames with techniques such as Hamming codes, sequence numbers, message retransmissions, timeouts, positive/negative acknowledges...

## Error Control Protocols
Protocols implementing both flow control and error control.

### Stop-and-wait
The sender sends one **frame** (message), the receiver sends positive/negative acknowledgement (*with the frame number it waits next*), and the sender **stops until receives confirmation** from the receiver (ACK) and then sends the next frame. 
- Frames and acknowledgments need to be numbered.
- Three cases:
	- *No lost messages*: the sender sends frame $x$  and the receiver sends ACK $x+1$.
	- *A frame is lost*: The sender resends the frame $x$ for time-out.
	- *An ACK is lost*: The sender resends the frame $x$ for time-out.
	
	![[Pasted image 20240301094102.png]]

It is efficient for huge sized messages, although is not possible normally. Its efficiency:
$$E_{sender} = \frac{T_{trans}}{2T_{prop} + T_{trans}}$$

### Pipelining
Consists in **sending several frames** before receiving individual ACKs. It's needed to extend the range of the sequence numbers and to add a buffer (**sliding window**).

For a sequence numbers using $m$ bits, the range of sequence numbers is $[0, 2^{m}-1]$ and a sliding window size of $2^m-1$. The efficiency:
$$E_{sender} = \frac{m\cdot T_{trans}}{2T_{prop} + T_{trans}}$$
### Selective Repeat Protocol (SRP)
Segmentation protocol based on the **retransmission of selective frames**, those which weren't sended are resended if receiving a NAK. 
- The receiver uses *Cumulative Acknowledgment* as it doesn't have to send an ACK for every frame. 
- The size of the *sliding window* is $2^m-1$ (to evade synchronization errors between the sender and the receiver) and will move depending on the ACK received.
- If a later frame than another is received, the receiver will send a NAK for the sender to resend the frame.
![[Pasted image 20240301100912.png]]


# Process to initiate, maintain & close a communication channel
- **Synchronization and framing**, stopping reading the message as it fails.
- **Multiple-access resolution**