package DatagramTest;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    private static final String SUCCESS_MSG = "Sent Successfully\n";
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        int port = 6666;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            byte[] data = input.getBytes();
            DatagramPacket packet = new DatagramPacket
                    (data, data.length, ip, port);
            socket.send(packet);
            System.out.print(SUCCESS_MSG);
            if (input.equals("q"))
                break;
        }
        socket.close();
    }
}
