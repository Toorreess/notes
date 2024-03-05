# Layered Network Architectures
The communication functionality of computer networks is typically grouped in layers, as the reference model of OSI (*Open Systems Interconnection*) or the TCP/IP model of Internet.

![[Pasted image 20240229113419.png]]

## Elements
- **The Layers**.
- **The Service interfaces**.
- **The protocols**.

# Protocols
A *protocol* is a **set of standard rules** that defines **what** is communicated, **how** it is communicated, and **when** it is communicated. They are responsible of:
- The *syntax* or format of the data.
- The *semantics* of the messages and what action is to be taken at the reception of each message.
- The *timing* or order in which messages must be sent and received.

The communication between two computers is possible iff both computers implement the same protocol for each layer. The standard defines the protocols of the network architecture and its implementation is called **protocol stack**.

\*Note: Layer network architectures = Network protocol families = protocol suite

## Classification
- **Connection oriented**: the source first makes a connection with the target before sending information. E.g.: the telephone, TCP.
- **Connectionless**: a connection is not needed to exchange information. E.g.: the postal mail, UDP.

# Standards
## Who standardizes communication technologies?
- **De facto**: Standards that have not been approved by an organized body but have been adopted as standards through widespread use are de facto standards.
- **De Jure**: Standards developed and approved by an official organisation.
	- *Governmental*
	- *Non-Governmental standards* (often non-profit orgs.)

### Standards Bodies
- ISO (International Organization of Standarization)
- IEEE (Institute of Electrical and Electronics Engineers)
- ITU-T (ITU Telecommunication Standarization Sector)
- IETF (Internet Engineering Task Force)
- W3C (World Wide Web Consortim)
## OSI (Open System Interconnection)
The motivation is to standardize the communication technologies to achieve interoperability of computer networks.

### Layers
7. **Application Layer**: Network processes as the @ of an email. Unit exchanged: *APDU*.
6. **Presentation Layer**: Security and *ASN (Abstract Syntax Notation)*. Unit exchanged: *PPDU*.
5. **Session Layer**: for organized multiconferences. Unit exchanged: *SPDU*. 
4. **Transport Layer**: Connect two processors without errors. Unit exchanged: *TPDU*.
3. **Network Layer**: Optimal path between two nodes. Unit exchanged: *packets*.
2. **Data Link Layer**: How are the data shared (point-to-point...). Unit exchanged: *frames*.
1. **Physical Layer**: Protocol and interface. Unit exchanged: *bits*.

It is based in **message encapsulation**. Each layer has a **header**, thus the size of the data will be increasing from application to physical layer in a way that, after the message arrives at the receiver, those headers will be interpreted with the specified protocol and reduced in each layer until arrive to the application layer. In the Data Link layer a fragment will be append to the message.

# TCP/IP Protocol Suite
It defines fewer layers than OSI/RM, which are not necessarily interdependent as in OSI and can be implemented using different network access; it has numerous open source stacks and for many operating systems. 

### Layers
4. **Application Layer**: Client-server paradigm and Standard Internet Services (FTP, HTTP, SMTP).
3. **Transport Layer**: Connection-oriented (TCP, Transmission Control Protocol) and connectionless (UDP, User Datagram Protocol).
2. **Internet Layer**: Connectionless internetworking protocol. (IP, Internet Protocol).
1. **Network Interface Layer**: The physical and Data Link layers.

![[Pasted image 20240229123841.png]]
