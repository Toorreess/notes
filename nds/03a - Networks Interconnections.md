To connect LANs or segments of LANs, we use **connecting devices**, which can operate in different layers. There are 5 categories of connecting devices based on the layer in which they operate:

![[Pasted image 20240312105608.png]]

# Repeaters and Hubs
Both forward every frame, and do not process the data. The repeaters can regenerate the input signal, and amplifies only the desirable signal to mitigate the signal attenuation.

They have limitations on maximum nodes and distance, and they cannot support multiple LAN technologies (coaxial cable, optical fiber...).

# Data Link Level Interconnection: Bridges
Bridges operate in both in physical and the data link layers, storing and forwarding frames. Their objective is to reduce collisions, distributing the stations in smaller physical networks. There are 2 types of bridges.

## Transparent Bridges
For LANs with the same Data Link layer. It has **filtering** capability for sending the message to the desired network. For this purpose, with self-learning and a dynamic table which maps addresses to ports automatically, it learns which hosts can be reached through which interfaces. Each entry in this table has:
- **MAC Address**: Data Link address.
- **Port**: Hardware interface.
- **TTL** (Time to Live): a timestamp

When a bridge receives a frame, the MAC address is stored; if the frame is already in the table, it will deliver directly at the port. If it isn't in the table, it will **flood**, i.e., send the message to all the interfaces (except the sender one).
![[Pasted image 20240312111752.png]]
## Translating Bridges
For LANs with different Data Link layers. It has to translate between different frame formats. It also has to recalculate the CRC, the bit order, and put or discard a default value to some frame's fields.

They aren't commonly used, as they are slow and this could be resolved with routers.
![[Pasted image 20240312111856.png]]

# Data Link Level Interconnection: Switches
The L2 switches shares with bridges the most relevant attributes.
- A switch interconnects computers and a bridge interconnects LANs.
- A switch has many ports and bridges has few ports
- A switch can be more sophisticated than a bridge.

# Network Level Interconnection: Routers
Routers are network-layer devices which can connect LANs and WANs. They use networks addresses and **routing algorithms** to compute **routing tables**. Their purpose is to examine the network layer headers, excluding Ethernet. LANs interconnected with a router are considered different networks.

# Higher Level Interconnection: Gateways
A connecting functionality that **operates above the network level**. With them you can prevent access if you are not in the registered network addresses and improve security in applications and transform formats.

# Routing
Is the process of determining the best path (route) that packets should follow to arrive to the desired destination. The cost is calculated using a metric which depends on distance, link bandwidth, realiability... The graph representation of a networks make ease the comprehension and algorithms.

The **routing algorithm** is responsible of finding the shortest path from each source to each destination. 

**Forwarding** is deciding for which output interface an incoming packet should be transmitted on.

The classification can be **fixed**, where the routing decision making is made previously and does not change, or **dynamic**, where the routing decision making is adaptable, being aware of the current network traffic and network connectivity.

## Routing Fixed: Flooding
