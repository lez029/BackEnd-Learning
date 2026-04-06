package DatagramTest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) throws Exception {
        try (DatagramSocket server = new DatagramSocket(6666)) {
            while (true) {
                byte[] buffer = new byte[64 * 1024];
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                server.receive(datagramPacket);
                int length = datagramPacket.getLength();
                String result = new String(buffer, 0 ,length);
                if (result.equals("q"))
                    break;
                System.out.print(result + "\n");
                System.out.print("-----------------" + "\n");
            }
        }
    }
}
