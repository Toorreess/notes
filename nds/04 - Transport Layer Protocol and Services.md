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


# 2. Distributed Programming