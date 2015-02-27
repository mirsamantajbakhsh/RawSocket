/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rawsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.JMemoryPacket;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.JProtocol;

/**
 *
 * @author Mir Saman Tajbakhsh
 */
public class RawSocket {

    static Pcap pcap;
    static int snaplen = 64 * 1024;           // Capture all packets, no truncation  
    static int flags = Pcap.MODE_PROMISCUOUS; // capture all packets  
    static int timeout = 10 * 1000;           // 10 seconds in millis  
    static StringBuilder errbuff = new StringBuilder();

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        //printListOfMACs();
        pcap = Pcap.openLive("DEVICE_MAC_NAME", snaplen, flags, timeout, errbuff);

        //Sending packets through raw packet
        /*while (true) {
         SendPacket();
         Thread.sleep(200);
         }*/
        
        
        //Listening packets through raw packet
        //jpcapListener jl = new jpcapListener();
        //pcap.loop(-1, jl, errbuff);
    }

    private static void printListOfMACs() throws IOException {
        List<PcapIf> alldevs = new ArrayList<>(); // Will be filled with NICs  
        StringBuilder errbuf = new StringBuilder();     // For any error msgs  

        int r = Pcap.findAllDevs(alldevs, errbuf);
        if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
            System.err.printf("Can't read list of devices, error is %s", errbuf.toString());
            return;
        }

        for (PcapIf pif : alldevs) {
            System.out.println(bytesToHex(pif.getHardwareAddress()));
            System.out.println(pif.getName());
        }
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static void SendPacket() {
        JPacket jp = new JMemoryPacket(JProtocol.ETHERNET_ID, "PACKET_HEX_BYTES");
        pcap.sendPacket(ByteBuffer.wrap(jp.getByteArray(0, jp.size())));
    }

}
