package TCPTest;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws Exception {
        try(ServerSocket serverSocket = new ServerSocket(8888)) {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    ServerThread newThread = new ServerThread(socket);
                    new Thread(newThread).start();
                } catch (RuntimeException e) {
                    System.out.print("Error: " + e.getMessage());
                    break;
                }
            }
        }
    }
}

class ServerThread implements Runnable {
    private Socket socket;
    private static final String ERROR_MSG = "Something is wrong\n";
    private static final String OFFLINE_MSG = "User: %s -> Offline\n";
    private static final String SPLIT_LINE = "--------------------\n";
    ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
            try {
                InputStream is =  socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                while (true) {
                    String output = dis.readUTF();
                    System.out.print(output + "\n");
                    System.out.print(socket.getRemoteSocketAddress() + "\n");
                    System.out.print(SPLIT_LINE);
                }
            } catch (EOFException e) {
                System.out.printf(OFFLINE_MSG,
                        socket.getRemoteSocketAddress());
            } catch (IOException e) {
                throw new RuntimeException(ERROR_MSG);
            }

    }
}
