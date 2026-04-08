package TCPTest;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("frp-cup.com", 20269);
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        new Thread(new ClientReaderThread(socket)).start();
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("q"))
                break;
            dos.writeUTF(userInput);
            dos.flush();
            ///System.out.print("Write Successfully\n");
        }
        dos.close();
        socket.close();
        scanner.close();
    }
}

abstract class ClientThread implements Runnable {
    public static final String SPLIT_LINE = "--------------------\n";
    protected Socket socket;
    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public abstract void run();
}

class ClientReaderThread extends ClientThread {
    public ClientReaderThread(Socket socket){
        super(socket);
    }

    @Override
    public void run() {
        try{
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            while (true) {
                String otherInput =  dis.readUTF();
                String userName = otherInput.split(":")[0];
                System.out.print(otherInput + "\n");
                System.out.print(SPLIT_LINE);
            }

        } catch (IOException e) {

        }
    }
}


