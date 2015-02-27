/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rawsocket;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;

/**
 *
 * @author Mir Saman Tajbakhsh
 */
public class jpcapListener implements JPacketHandler<StringBuilder> {

    byte[] sIP = new byte[4]; // Should be outside the callback method for efficiency  
    byte[] dIP = new byte[4];

    @Override
    public void nextPacket(JPacket jp, StringBuilder t) {
        final Ip4 ip4 = new Ip4();
        final Tcp tcp = new Tcp();
        final Http http = new Http();
        final Ethernet eth = new Ethernet();

        if (jp.hasHeader(eth.ID)) {
            jp.getHeader(eth);

            String sourceMAC = org.jnetpcap.packet.format.FormatUtils.mac(eth.source());
            String destinationMAC = org.jnetpcap.packet.format.FormatUtils.mac(eth.destination());

            System.out.println(sourceMAC + " -> " + destinationMAC);
        }
    }

}
