Mainly used in LAN, it may happen a **collision** if the channel receives two or more signals at the same time (+2 nodes transmitting data).

The **MAC**(Multiple Access Control) protocol coordinate the transmission of the active nodes in a shared channel.
- Determine **when** a station can use the shared channel.
- **What** a station must do when the link is busy.
- **What** a station must do when it is involved in a collision.

The station must sense the medium to determine if the channel is busy (i.e. **carrier sense**). In this case, the station must wait, then transmit and finally determine if there has been a collision.

There are two possible control strategies:
- **Centralized**: e.g. a multiplexer.
- **Distributed**: random access or controlled access.
# Carrier Sense Multiple Access (CSMA) Networks
## ALOHA
Pure ALOHA protocol description:
- All frames from any station are of fixed length (**$L$ bits**).
- A station that has data **can transmit at any time**.
- After transmitting a frame, the sender **must wait for an ACK**.
- If no ACK received, sender **resends** that frame after waiting a random amount of time (**backoff**).
- If a backoff limit is reached, the transmission is aborted.

The ALOHA Maximum channel utilization is 18% (the 82% of frames need retransmission).

## CSMA Protocols
### 1-persistent CSMA
- The station decides whether to transmit or not.
- Waits until medium idle, then transmit immediately with probability 1.
- Unefficient & very high probability of collision.

### Non-persistent CSMA
- The station waits a random time and checks the channel.
- **Reduces** the probability of collisions, but **it wastes bandwidth**.

### p-persistent CSMA
- Before transmitting, waits a time $p$ for evading collisions.

![[Pasted image 20240301104057.png]]

### CSMA-CD (with Collision Detection)
- Implemented in *Ethernet*.
- **Uses one of the CSMA persistence algorithm** for transmission.
- If the station **detects a collision** after transmission (the observer power is higher than the transmitted power):
	- *Stop transmitting* 
	- *Transmits a jam signal* (interference signal of 48 bits) to notify the rest of the stations about the collision.
	- After sending the jam signal it waits a random amount of time (backoff) and starts again.

The *exponential Backoff Algorithm*, before $n$ (16) successive collisions, it waits a random time in the range $[0, d\cdot 2^{m-1}]$, where $m= \text{min}[10,n]$, after $n$ collisions and $d$ being the slot time (constant). If $n = 16$, give up the transmission and report a failure to higher layers.

The waiting increase for every collision fives LIFO effect.

## IEEE 802
![[Pasted image 20240301105517.png]]

- **MAC** Specifies *access method* and *framing format* specific to corresponding LAN protocol. Does not provide any guarantees that data is delivered.
- **LLC** (*Logical Link Control*) provides one single data link control protocol for all IEEE802.x standards. Services:
	1. *Unacknowledged connectionless mode* (no ACKs). Provides no guarantees that a frame arrives its destiny.
	2. *Connection-oriented operational mode*: ensures that the frames received are guaranteed to be in the order they have been sent, but is slow.
	3. *Acknowledged connectionless service*: A frame is transmitted iff an ACK of the previous frame was received.
- Encapsulation of the MAC frames and LLC frames:
![[Pasted image 20240301110128.png]]

Currently not included as part of Ethernet, but it is included as part of Token Ring, WiFi...

## Ethernet (IEEE 802.3)
### Physical signalling
It's *self-clocking*, i.e., there is no synchronization between sender and reciever clocks, and uses **Manchester codification**:
![[Pasted image 20240301110516.png]]

### Physical Layer
- **Bus**, collisions are observed with increasing power in the observed signal.
- **Hub/Star**, collisions occur with simultaneous access from two ports (prevails today).

### Frame Structure
- **Preamble**: 7  bytes pattern 10101010 used to synchronize receiver, sender clock rates.
- **SFD**: 1 byte, signals the beginning of the frame (10101011).
- **Addresses**: 
	- 6 bytes source.
	- 6 bytes destination.
	- The destination address is divided into OUI (3 bytes), where it will be specified in the first bit of the first byte if it is unicast (0) or multicast (1) and in the second bit if it is globally unique (0), or locally managed (1) followed by the NIC (3 bytes).
- **Length/type** (*Ethernet II*), 2 bytes.
- **Data & Padding**: Fills up to 46 bytes.

![[Pasted image 20240301111326.png]]

### Access Method
Standard Ethernet uses 1-persistent CSMA/CD. The **theoretical** calculation of the maximum length that a Ethernet network is: 
$$
\begin{align} 
&\text{Slot Time} = 2\cdot T_{prop} \\
&\text{Max Length} = \text{Propagation speed}\ \cdot (\frac{\text{slot time}}{2})
\end{align}
$$
