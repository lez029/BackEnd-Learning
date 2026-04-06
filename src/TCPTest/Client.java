package TCPTest;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("frp-cup.com", 51247);
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("q"))
                break;
            dos.writeUTF(input);
            dos.flush();
            System.out.print("Write Successfully\n");
        }
        dos.close();
        socket.close();
        scanner.close();
    }
}
