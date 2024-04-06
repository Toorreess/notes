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