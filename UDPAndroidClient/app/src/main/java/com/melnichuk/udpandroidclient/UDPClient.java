package com.melnichuk.udpandroidclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    private DatagramSocket udpSocket;
    private InetAddress serverAddress;
    private int port;

    public UDPClient(String destinationAddress, int port) throws IOException {
        this.serverAddress = InetAddress.getByName(destinationAddress);
        this.port = port;
        this.udpSocket = new DatagramSocket(this.port);
    }

    public void send(String inputText) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(inputText.getBytes(), inputText.getBytes().length, serverAddress, port);
        udpSocket.send(datagramPacket);
    }
}
