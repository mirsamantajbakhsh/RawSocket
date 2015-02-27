# RawSocket
A simple code for raw socket in Java using JNetPCAP.

In this example only frames (Ethernet layer) are sent and received.

# Sender
The sender functionality sends an Ethernet frames with hex string as its payload. You may fill the hex string by means of some popular network tools such as Wireshark or tcpdump.

# Listener
The lestener functionality receives all Ethernet frames and prints the source and destination MAC address of the frame. (src -> dst).

# Additional Library
The JNetPcap library is used in this sample project. You may find the configuration and more details in their official site (http://jnetpcap.com/)
