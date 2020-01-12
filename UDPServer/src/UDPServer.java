import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
    private static final int PORT = 9090;

    private DatagramSocket udpSocket;

    public UDPServer(int port) throws SocketException {
//        this.port = port;
        this.udpSocket = new DatagramSocket(port);
    }

    private void listen() throws Exception {
        String message;
        while (true) {
            byte[] buf = new byte[256];
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);

            udpSocket.receive(datagramPacket);
            message = new String(datagramPacket.getData()).trim();

            System.out.println("Message from " + datagramPacket.getAddress().getHostAddress() + ": " + message);
        }
    }

    public static void main(String[] args) throws Exception {
        UDPServer udpServer = new UDPServer(PORT);
        System.out.println("-- Running UDP Server at " + InetAddress.getLocalHost() + ":" + udpServer.udpSocket.getLocalPort() +" --");
        udpServer.listen();
    }
}