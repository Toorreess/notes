# 1. Network Interconnections
To connect LANs or segments of LANs, we use **connecting devices**, which can operate in different layers. There are 5 categories of connecting devices based on the layer in which they operate:

![[Pasted image 20240312105608.png]]

## 1.1 Repeaters and Hubs
Both forward every frame, and do not process the data. The repeaters can regenerate the input signal, and amplifies only the desirable signal to mitigate the signal attenuation.

They have limitations on maximum nodes and distance, and they cannot support multiple LAN technologies (coaxial cable, optical fiber...).

## 1.2 Data Link Level Interconnection: Bridges
Bridges operate in both in physical and the data link layers, storing and forwarding frames. Their objective is to reduce collisions, distributing the stations in smaller physical networks. There are 2 types of bridges.

### 1.2.1 Transparent Bridges
For LANs with the same Data Link layer. It has **filtering** capability for sending the message to the desired network. For this purpose, with self-learning and a dynamic table which maps addresses to ports automatically, it learns which hosts can be reached through which interfaces. Each entry in this table has:
- **MAC Address**: Data Link address.
- **Port**: Hardware interface.
- **TTL** (Time to Live): a timestamp

When a bridge receives a frame, the MAC address is stored; if the frame is already in the table, it will deliver directly at the port. If it isn't in the table, it will **flood**, i.e., send the message to all the interfaces (except the sender one).
![[Pasted image 20240312111752.png]]
### 1.2.2 Translating Bridges
For LANs with different Data Link layers. It has to translate between different frame formats. It also has to recalculate the CRC, the bit order, and put or discard a default value to some frame's fields.

They aren't commonly used, as they are slow and this could be resolved with routers.
![[Pasted image 20240312111856.png]]

## 1.3 Data Link Level Interconnection: Switches
The L2 switches shares with bridges the most relevant attributes.
- A switch interconnects computers and a bridge interconnects LANs.
- A switch has many ports and bridges has few ports
- A switch can be more sophisticated than a bridge.

## 1.4 Network Level Interconnection: Routers
Routers are network-layer devices which can connect LANs and WANs. They use networks addresses and **routing algorithms** to compute **routing tables**. Their purpose is to examine the network layer headers, excluding Ethernet. LANs interconnected with a router are considered different networks.

## 1.5 Higher Level Interconnection: Gateways
A connecting functionality that **operates above the network level**. With them you can prevent access if you are not in the registered network addresses and improve security in applications and transform formats.

## 1.6 Routing
Is the process of determining the best path (route) that packets should follow to arrive to the desired destination. The cost is calculated using a metric which depends on distance, link bandwidth, realiability... The graph representation of a networks make ease the comprehension and algorithms.

The **routing algorithm** is responsible of finding the shortest path from each source to each destination. 

**Forwarding** is deciding for which output interface an incoming packet should be transmitted on.

The classification can be **fixed**, where the routing decision making is made previously and does not change, or **dynamic**, where the routing decision making is adaptable, being aware of the current network traffic and network connectivity.

### 1.6.1 Fixed Routing: Flooding
Every incoming packet is sent on every outgoing link except on the incoming link. All the next stations receive a copy of the incoming packet. 
- It always finds the shortest path quickly.
- It generates infinite number of duplicate packets. Is needed a strategy to dump duplicate packets, with a **hop counter** or by **keeping track** of which packets have been flooded to avoid sending them again.

It's very *simple* to implement, but can be costly in terms of wasted bandwidth and increases the *processing complexity*.

It's used by the self-learning bridges to initialize the forwarding tables.

![[Pasted image 20240404092135.png]]


### 1.6.2 Fixed Routing: Shortest Path
It can be resolved by using *shortest path algorithms* like *Dijkstra's* or *Bellman-Ford*. For this purpose, we generate for every node a routing table with the **optimal route** to any
other node. The routes do not automatically adapt to network changes (topology changes or link replacement).

### 1.6.3 Fixed Routing: Random
Decides for which interface does it send a package with a percentage to take into account the network traffic.

### 1.6.4 Dynamic Routing
The routing table can be manually adapted, but then is not possible to adapt the routing table to the network changing conditions (topology and traffic). It is recommendable to use *dynamic routing protocol*.

In *distributed routing protocols*, only routers are involved in the transmission of routing information. The routes change more quickly in response to changes in topology, or as a result of a periodic update.

### 1.6.5 Hierarchical routing
As the number and size of networks increase, the routing table size also increases and so do the CPU & memory to process the routing tables.

To prevent this problem, nodes are collected into regions (aka domains) and subdomains (set of routers). This requires a hierarchical addressing system for identification:

$$\text{<id. Region><id. host>}$$

The routing tables will be then composed by *other regions* and the *nodes in the same region*. The size of the hierarchical routing table will now be $(M-1) + (K-1)$, where $M$ is the number of nodes in the same domain and $K$ is the number of domains.

![[Pasted image 20240404094537.png]]

![[Pasted image 20240404094606.png]]

The Internet Protocol uses hierarchical routing.


# 2. The Internet Protocol (IPv4)
Used to source-to-destination delivery of packets across multiple networks. Use a *routing table* to forward packets, and a *routing algorithm* for path selection and modification.

It's needed a Network level addressing, and it's structured in a datagram format (equivalent to IP message or data).

The Internet Protocol or IP RFC 791 is the most relevant protocol of the Internet network layer in the TCP/IP model.

![[Pasted image 20240404095254.png]]

## 2.1 Service & Protocol
It provides a packet delivery service that it's:
- *Connectionless* (i.e. datagram service)
- *Unreliable*, as datagrams can be **lost**, **duplicated**, **out-of-order** or **corrupted**.
- *Best-effort delivery*

IP does not perform error control to fix errors, but only to detect them. (when an error is detected the packet is discarded). Thus, the upper layers must provide reliable service. The IP service definition is:
- **Send:** *N_DATA.request(Source, Destination, Data, QoS)*
- **Recv:** *N_DATA.indication(Source, Destination, Data, QoS)*

The IP functionality is:
- It defines the format of packets, called **IP datagrams**.
- It defines Internet addressing scheme (e.g. **IP addresses**).
- It **delivers messages** received from the **transport layer** encapsulated in datagrams.
- It **forwards datagrams** using a routing table.
- **Fragmentation and reassembly** of IP datagrams.
- **Congestion control**, discard of packets.

![[Pasted image 20240404101056.png]]

The datagram format: composed by *IP header* (the minimum size is 20 bytes, divided in 5 blocks of 32 bits), and *data* (also called *payload*).

![[Pasted image 20240404101516.png]]

- **Version:** *4 bits*, IP protocol version number.
- **Header Length:** *4 bits*, HLEN in 32-bit words (or 4 bytes words). Variable length of the header (20-60 bytes) -> HLEN 5-15.
- **Type of Service (ToS):** *8 bits*, tells what it must be priorize: transmission rate, latency, throughput, cost, etc.
- **Total Datagram Length:** *16 bits*, Max value of 65535 bytes ($2^{16}-1$)

If a datagram exceeds the max length of a message, the *MTU* (Max Transport Unit) must be fragmented. We would need then the next fields:
- **Identification:** *16 bits*, identifies distinctively the datagram.
- **Flags:** *3 bits* (one is not used): 
	- *D, 1 bit*: don't fragment.
	- *M, 1 bit*: more fragments (the last fragment puts a 0).
- **Fragment Offset:** *13 bits*, shows the relative position of this fragment with respect to the whole datagram.

Next section:
- **Time to live:** *8 bits*, to limit the lifetime of datagrams. It stores a timestamp decremented by each visited router. If after decrement the TTL it drops to 0, then the router discards the datagram
- **Protocol:** *8 bits*, identifies the upper layer to define to which protocol the data belong (1:ICMP; 6:TCP; 17:UDP).
- **Header Checksum:** *16 bits*, for error detection. Is recalculated in each hop.

After source & destination addresses, it is found the IP options (up to 40 bits):
![[Pasted image 20240409104942.png]]
## 2.2 IP Addressing
An IP address identify a computing device’s interface by an unique & universal address. Each hardware interface of a computing device must have an IP address to be connected to Internet. 
- The IPv4 addresses have a length of 32 bits ($2^{32} = 4\ 294\ 967\ 296$ ).

IP follows a *hierarchical routing* approach: $<net\_id><host\_id>$. IPv4 uses routing tables to forward incoming datagram:
- Routers uses $net\_id$ of an incoming datagram to index the forwarding table (to hop into different networks).
- Routers uses $host\_id$ when the datagram has reached the destination network.

IPv4 addresses the following classful addressing scheme:

![[Pasted image 20240409110301.png]]

The notations for IPv4 addressing are:
- *Binary*, where an address is written as a block of 32 bits.
- *Dotted-decimal notation*, where is written numbers in octet-grouped base-10 separated by dots.
	- Each decimal value range from 0 to 255.
	- Each field is the translation to decimal of binary bytes.

There is some IPv4 special addresses:

![[Pasted image 20240409110814.png]]


### 2.2.1 Assignment
The assignment of address blocks to regions is responsibility of *ICANN* (Internet Corporation for Assigned Names and Numbers). All the interfaces of a LAN must have the same $net\_id$.

To obtain the $net\_id$, the router applies a bits mask to identify the $net\_id$, although it will depend on the class the $net\_id$ belongs to.

![[Pasted image 20240409111144.png]]

Each class is divided into fixed number of blocks with each block having a fixed size, but when installing a network, we could be wasting network addresses. To mitigate the wasting of IPv4 addresses, there are some techniques:

#### Subnetting
Subnetting consists in divide a network into several subnets, connected by one or more routers. This allows to manage the privacy, as well as an independent management of subnets & a more efficient use of the addressing space.

![[Pasted image 20240409111609.png]]

We can identify the subnets using several bits of the $host\_id$, thus being the $subnet\_id$ composed by the $net\_id$ & $n$ bits of the $host\_id$. With $n$ bits, we can define:
$$IPs = 2^{\text{Host\_id}_{bits} - \text{Subnet}_{bits}} -2$$


## 2.3 Routing
Each host & router must have a *routing table* for sending datagrams between routers. The *format of routing tables* are pairs $<N,H>$, where $N$ is the $net\_id$ & $H$ is the IP address of the next router (at a distance of a hop).

![[Pasted image 20240409112617.png]]

However, we have to consider the subnets. Thus, it is needed an additional field:

![[Pasted image 20240409112715.png]]

Due to routers are connected to several networks, it is needed to add de network's interface ID (e.g.: hme0, le1, ...):

![[Pasted image 20240409112831.png]]

There are some possible cases for routing:

- The target IP **belongs** to the same net/subnet. A *direct delivery* is made. This is encapsulated in the Data Link Layer (MAC), including itself.
- The target IP **doesn't belong** to the same network. In this case, two thing can be made:
	1. *Indirect delivery*, the datagram must go through several physical networks to reach de destination host. 
	2. *Default route*, is used if the destination network address is not found in the routing table.

![[Pasted image 20240409113502.png]]


## 2.4 Fragmentation
If the datagram length is larger than the MTU (Maximum Transmission Unit), it should be applied a **fragmentation** (fragment the data in fragments of length $\leq$ MTU). If a datagram is fragmented into two or more frames, this is indicated in the header.
![[Pasted image 20240409114042.png]]

- D: if the datagram must be fragmented and this flag is set to 1, then the datagram is discarded.
- M: the original datagram has this flag set to 0 (and the last fragment of a fragmented datagram).
- An offset that indicates the relative position of this fragment with respect to the whole datagram.

## 2.5 Other protocols
### 2.5.1 Address Resolution
#### 2.5.1.1 ARP (Address Resolution Protocol)
It maps IP addresses to physical addresses and viceversa. An *ARP cache table* is used, limited in space and time. It stores $\text{<IP address; physical address; TTL>}$ 

![[Pasted image 20240409114908.png]]

At the "if the IP corresponds to the target" condition, the next hop stores the $(IPsender,\ PAsender)$ in its ARP table. The ARP packets have a 0x0806 format that are encapsulated in the data field of the frames.

#### 2.5.1.2 DHCP (Dynamic Host Configuration Protocol)
Allows the host to dynamically obtain its IP address from network server when it joins network. It can renew its lease on address in use & allows reusing addresses (only holds address while connected).

![[Pasted image 20240409115545.png]]

#### 2.5.1.3 Group Multicast
IPv4 defines the **multicast address** (class D). With this, the network has then a mechanism to support applications that transmit the same date at one time to multiple receivers.

### 2.5.2 Error & Control: ICMP
The task is to *report* problems that a router or a host may encounter when it processes an IP datagram.

![[Pasted image 20240409120230.png]]

An error report is not sent when:
- An ICMP error-reporting message is discarded.
- The datagram discarded is an intermediate fragment.
- The target address of the datagram is a multicast address or any other special address (e.g. 127.0.0.1).

The query messages help to obtain information about network functioning. This messages are used by routing protocols to find out useful information about routing.

![[Pasted image 20240409120403.png]]


### 2.5.3 Dynamic Routing
Route change adapting to current context: in response to changes in topology, or as a result of a periodic update.

The dynamic routing is decided depending on how is the information:
- **Global**: all routers have information about link cost of the global network (*Link state algorithms*)
- **Decentralised**: Each router knows link cost to neighbours, and they exchange of information with them. (*Distance vector algorithms*)

#### 2.5.3.1 Link State Routing
The information's age is added for comparing the LSPs (Link State Package) and keep the most recent version of the network topology.

Packages are sent in response to changes in topology, or as a result of a periodic update. Because we have a copy of the topology, algorithms like Dijkstra can be applied to update the routing tables.
#### 2.5.3.2 Distance Vector Algorithm
Is the *least-cost route* between any two nodes is the route with minimum distance; Each node maintains a vector (table) *of minimum distances to every node*. Each node shares information with their neighbors: periodically (e.g. every 30 seconds), or when there is a change.

![[Pasted image 20240409121711.png]]

![[Pasted image 20240409121725.png]]


#### 2.5.3.3 Routing in AS
An *autonomous system* (AS) is a group of networks and routers under the authority of a single administration. The changes in an AS can be propagated with exterior router protocols.

- **RIP (Routing Information Protocol)**
Is an *intradomain* routing protocol, based on the *distance vector* routing protocol. Messages can hop up to 15 times or infinity (defined by 16), and each link has cost 1; each advertisement message lists of up to 25 destination subnets of the same AS.

- **BGP (Border Gateaway Protocol)**
Is an *interdomain* routing protocol that defines a *path vector* protocol. Two routers exchange messages with different destination network prefixes.

# 3. IP Next Generation
## 3.1 Short-term Solutions
### 3.1.1 VLSM (Variable Length Subnet Mask)
Define *variable length subnets* to do not waste addresses with a successive division of a network prefix. Use the *longest prefix principle* so if in a routing table addresses are summarized in one with a higher prefix and a change in the network requires a new entry to not generate errors, in the routing the entry for that IP address with the highest prefix will be chosen.

![[Pasted image 20240409123103.png]]
