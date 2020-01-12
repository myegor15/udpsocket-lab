import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    private static final String SERVER_ADDRESS = "192.168.0.110";
    private static final int SERVER_PORT = 9090;

    private DatagramSocket udpSocket;
    private InetAddress serverAddress;
    private int port;
    private Scanner scanner;

    private UDPClient(String destinationAddress, int port) throws IOException {
        this.serverAddress = InetAddress.getByName(destinationAddress);
        this.port = port;
        this.udpSocket = new DatagramSocket(this.port);
        this.scanner = new Scanner(System.in);
    }

    private int start() throws IOException {
        String inputText;
        while (true) {
            inputText = scanner.nextLine();

            DatagramPacket datagramPacket = new DatagramPacket(inputText.getBytes(), inputText.getBytes().length, serverAddress, port);
            udpSocket.send(datagramPacket);
        }
    }

    public static void main(String[] args) throws IOException {
        UDPClient udpClient = new UDPClient(SERVER_ADDRESS, SERVER_PORT);
        System.out.println("-- Running UDP Client at " + InetAddress.getLocalHost() + " --");
        udpClient.start();
    }
}
