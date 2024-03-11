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


# Token Passing Networks (FDDI)
A control frame called **token** passed from one station to next sequentially. Each station must *wait for the token* to start transmission. Stations are physically or logically arranged in a *ring topology*.

![[Pasted image 20240307090447.png]]

The **Fiber Distributed Data Interface** (FDDI) consist of a second (auxiliary) ring which operates in the reverse direction compared with the main ring, but is used only if the main ring fails.

# Wireless Networks (WiFi & Bluetooth)
The **transmission media** is **radio waves**. The wireless network taxonomy can classify by:

- **Use of a base station**:
	- A infrastructure is used (base station or access point), e.g., Wifi or 3G.
	- No infrastructure (*ad hoc*), e.g., Bluetooth or VANET.

- **Hops that must be done**:
	- *Single hop*: send a package directly.
	- *Multiple hops*: a package is sent through nodes.
## WiFi (IEEE 802.11)
The **BSS** (*Basic Service Set*) is composed by stationary or mobile wireless stations, with or without an access point (base station). WiFi can use an access point (AP) or not (*ad hoc*).

- The *Ad hoc mode architecture* has an independent BSS (**IBSS**).
	- All the stations operates in a equal mode.
	- Any station within the range of the WiFi network can join this network
- The *Infrastructure mode architecture* is:
	- **ESS** (Extended Service Set), made up of two or more BSS with APs.
	- Identification of WiFi networks:
		- **SSID**, the *public name* of the network.
		- **BSSID** (BSS SSID), the ID of the BSS you belong to (MAC address of the AP)
		- **ESSID** (ESS SSID), the ID of the ESS. In practice, ESSID= SSID.

**ISM** (**I**ndustrial, **S**cientific & **M**edical) bands radio waves are used. The physical layer is composed commonly by two channels:
- **2.4 GHz**: covers broader distances, compatible with many devices, but overcrowded.
- **5 GHz**: higher BW, less interferences, but only available for short distances and not compatible for all devices.

### MAC Layers
MAC Layers are divided in two sublayers:

#### The Distributed Coordination Function (DCF)
It defines a collision avoidance (CA) mechanism, instead of a collision detection (CD) mechanism. 

Collisions cannot be detected: in a AP, an antenna cannot be used for transmitting and receiving at the same time, for that is normally used 2 antenna, one for each role. 

In wireless the collisions must be detected by the receiver, since the collision depends on the neighbouring stations of the receiver.

*CSMA/CA*:
- Before sending a frame it informs the receiver it wants to transmit by sending a **RTS** (*Request to Send*) frame.
- The receiver indicates it is ready to receive data by sending a **CTS** (*Clear to Send*) frame.
- Collision avoidance is achieved by the **NAV** (*Network Allocation Vector*) timer.
	- Stations listening to the RTS waits for a time the sending stations needs to occupy the channel.
- The *backoff* algorithm starts when the data is sent, to control that this arrives.
- The station waits for a period of time to sense the medium and for transmitting the the signal if the channel is idle.
	- **DIFS** (*Distributed InterFrame Space*), the source station. E.g. 50 $\micro$s.
	- **SIFS** (*Short InterFrame Space*), the destination station e.g. 10 $\micro$s.

It is common to fragment the data to minimize the the penalty of retransmissions.
![[Pasted image 20240307095945.png]]
![[Pasted image 20240307095956.png]]
![[Pasted image 20240307100011.png]]

#### The Point Coordination Function (PCF)
The Access Point (AP) defines a centralised contention-free **polling access method**, it waits a time PIFS (*PCF InterFrame Space*) and asks an station if it has something to transmit, contention-free (**CF**) end, the other stations start their NAV (waiting). After that, it asks to the next station and so on, sequencially in a loop. The *PCF* has priority over *DCF*.

### Frame Format
![[Pasted image 20240307100902.png]]
![[Pasted image 20240307101019.png]]
![[Pasted image 20240307101129.png]]

![[Pasted image 20240307100915.png]]

### Exercise
![[Pasted image 20240307101239.png]]

## Bluetooth (IEE 802.15.1)
The most popular technology of **WPAN**. It is designed for short-range ad hoc connections. There is two types of Bluetooth networks:
- **Piconet**: small network, with a master (primary) station, the rest are slaves (secondary) stations.
- **Scatternet**: a combination of piconets, where a station can be member of two piconets (the slave of one can be the master on another).

The protocol architecture:
![[Pasted image 20240307102132.png]]

### Physical Layer (Radio Layer)
It defines a 2.4-GHz ISM band divided into 79 channels of 1 MHz each. BLE communicates over 40 channels of 2 MHz.

To avoid interferences, it uses the **Frequency-Hopping Spread Spectrum** (*FHSS*). Each device uses a frequency for only 625 $\micro$s before it hopes to another frequency pseudo-randomly (using a seed).