# 1. Internet Transport Layer Protocols (UDP & TCP)
## 1.1 Transport Layer
The transport layer is the last one to add functionalities in the transfer and data addressing. This layer is not present/processed in the routers. Its main goals are:
- To provide logical *communication between app processes* running on different hosts.
- *Relies on and/or enhances* the error control services provided by *network layer*.
- Provides to the upper layer an *end-to-end reliable communication service*.

The services of this layer are:
- **Transport addressing**: to identify processes inside a computing device.
- **Encapsulation and Decapsulation**: hand out the user data in several messages.
- **Multiplexing**: Multiplexing many transport connections in a single network connection to increase efficiency (and demultiplexing).
- **Types of transport protocols**
	- *Connection-oriented protocols - TCP*
		- Reliable and in-order delivery of messages.
		- Congestion control.
		- Flow control.
	- *Connectionless protocols - UDP*
		- Unreliable and unordered delivery of messages.

### 1.1.1 Transport addressing
At the transport layer a *field* will be reserved for the port on which the service is stored. It's an unsigned short (16 bits) with the numeric range \[0-65535\] that allows the *Client/Server paradigm in Internet*:
- **The client sends a request (initiator)**, using a port that can be an arbitrary number.
- **The server (listener) returns a response** through the port number received.

![[Pasted image 20240514110601.png]]

\[0-1024\] are the well-known ports; \[1025-49151\] are the ports registered; and \[49142-65535\] are the dynamics or privates ports.

![[Pasted image 20240514111004.png]]

The ports opened by the client are usually ephemeral ports (>1024). A process is identified by a triplet **<protocol, IP address, port number>**.

### 1.1.2 Encapsulation
From the application layer the message is sent to the transport layer where a header, on arrival, will be removed to pass the message to the application layer.

### 1.1.3 Multiplexing (M:1) & Demultiplexing (1:M)
It passes several process messages to the IP layer without distinguishing whether they belong to the same process or not.

### 1.1.4 Reliable data transfer
Controls that the message is not *corrupted*, *out of order*, *lost* or *duplicated*, in connection-oriented protocols (TCP). When a packet is lost, the most common thing is to retransmit it.

### 1.1.5 Flow control & Congestion control
- **Flow control:** The processing capacity of the receiver must be synchronized with the sending capacity of the sender.
- **Congestion control:** Network resources must be distributed to avoid congestion of the network through some mechanism.

## 1.2 UDP
It is a connectionless service that does not improve that provided by the IP; However, it cannot be replaced by the IP as the port number is required. For sending data with less preprocessing. 

The service functions are:
- `sendto(source_port, target_port, data)` 
- `recvfrom(source port, target port, data)`

![[Pasted image 20240514114558.png]]

### 1.2.1 UDP: Datagram
![[Pasted image 20240514114648.png]]
Checksum includes the UDP header, and source and destiny IPs.

### 1.2.2 UDP: Encapsulation & decapsulation

![[Pasted image 20240514114955.png]]

### 1.2.3 UDP: Funcionality
- **Multiplexing:** When a UDP segment is received the destination port is checked and directed to the socket with that port.
- **Queuing:** Some operating systems have two queues implemented for outgoing and receiving messages.


On a server you cannot have more than one client on the same port. It is faster and requires less processing and transmission time. It is used for **small message**, fault-tolerant protocols, as well as **multicast/broadcast** messages.

## 1.3 TCP
It offers a full-duplex connection-oriented service (sending and receiving) for **point-to-point** connections, with trust (checking for errors and congestion) and delivery of the byte stream (without bounding the message).

TCP messages are called **segments** and are 20 to 60 bytes in size. Its format is:

![[Pasted image 20240521105340.png]]

- **Source & Destination Ports:** 16 bits.
- **Sequence number (32) & ACK (32):** Unlike the data link layer, when a message is sent it is assigned the sequence number corresponding to the bytes it is transporting, the ACK will be the next byte to be delivered.
- **HLEN (4):** Header length in 32 bits words, between 5 (20 bytes) and 15 (60 bytes).
- **Windows size (16):** will indicate to the server/client the capacity it has to send in its next message. In case there is a change in window capacity, the server/client will be notified.
- **Checksum (16):** Sum of the header and IP addresses.
- **Reserved bits (6):** to align the fields.
- **Options:** depends on the maximum data size, MSS (Maximum Segment Size).
- **Flags (6):**
	- *URG:* Urgent pointer is valid. Points to the last byte of urgent data.
	- *ACK:* Acknowledgment is valid.
	- *PSH:* Request for push.
	- *RST:* Reset the connection.
	- *SYN:* Synchronize sequence numbers.
	- *FIN:* Terminate the connection.

### 1.3.1 Buffering
There are two buffers, one input and one output buffer of 1024 bytes that function as circular arrays. 

Messages will be sent when the buffer is full. You can avoid this feature with a flag to send the message directly.

### 1.3.2 Closing the connection
A TCP connection can be closed in several ways:
- 3-way-in, client sends fin, server sends fin+ack, client sends ack.
- Half close, the client communicates that it is not going to send more data (only acks) and the server will be the one to close the other part of the connection.

### 1.3.3 TCP states diagram
The states in which the connections are are saved in the TCB.

### 1.3.4 Flow control
Silly window syndrome occurs when a capacity is negotiated and then it is completely filled, resulting in the next message being to close the window.

**Sliding Window**
In the sending window there is a negotiated length and two stripes in it, the bytes that have been sent, but that have not been confirmed and those that can be sent. If a timer runs out it must be resend and the window moved. Instead of intermittently sending messages with a window size of 0, it waits for a size sufficient to transmit: $$T = \min(MSS,\ \frac{\text{reception}_{\text{buffer}}}{2})$$

**Nagle's Algorithm**
The objective is to reduce the number of segments:
- If there is no data waiting, write data.
- If there is unconfirmed data, wait for:
	- Confirmation of the data
	- The size of the window and available messages is larger than the MSS.

### 1.3.5 Error control
The method used for error detection is based on ACK checksum and acknowledgments (for all messages except other ACKs) and retransmission as a solution. The retransmission can be triggered:
- Based on estimates dynamically for connection with times.
- Receiving 3 duplicate ACKs, due to a timer that is too short. In non-original versions of TCP, if a segment was not received correctly it was discarded. In the most modern ones, those that arrive are saved in a buffer while they are requested to be sent again (ACK). Finally, it was decided that if the receiver receives 3 ACKs it must retransmit it, this method is called **fast retransmit**.

### 1.3.6 Congestion control
To try to ensure fair use of network resources, TCP implements some mechanisms to avoid retransmission of messages with accumulative and observing congestion problems upon expiration of retransmission timers.
- Slow start, sending window has MSS size and exponential growth.
- (Mandatory) Congestion avoidance, this exponential growth will decelerate once it passes a threshold, becoming additive: $cwnd = cwnd + (MSS\cdot MSS) / cwnd$; other implementations may decide to do faster recovery than additive. When a loss is detected the window size is reset.

## 1.4 STCP
The **Stream Control Transmission Protocol** combines the features of TCP and UDP. It is oriented for the exchange of messages (UDP), as a reliable connection protocol (TCP). It allows the use of several streams (video and audio, for example) and multihoming (association with several IPs without having to start a new connection).

# 2. Distributed Programming

## 2.1 Distributed Systems & Basic Socket Programming

### 2.1.1 Java
The client creates a socket object (SYN / SYN+ACK), getInputStream (input buffer), getOutputStream (output buffer). With the write and read methods messages will be sent and received.

The server, in an infinite loop, will accept new requests and read and write messages. When there is no more data to transmit, the connection is closed with close().

### 2.1.2 C
In C, functions such as socket, binf, listen, accept (server)/ connect (client), recv, send, recv, close are used.

## 2.2 DNS Resolver Functions
The Internet address is encapsulated in the InetAddres class and can be obtained with getters.

