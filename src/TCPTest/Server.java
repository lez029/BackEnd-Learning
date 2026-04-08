package TCPTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    /// public static Map<Socket, String> userMap = new HashMap<>();
    public static Map<Socket, DataOutputStream> clientMap = new ConcurrentHashMap<>();
    protected static Set<Socket> onlineSocketSet =
            java.util.concurrent.ConcurrentHashMap.newKeySet();
    public static void main(String[] args) throws Exception {
        try(ServerSocket serverSocket = new ServerSocket(8888)) {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    ServerThread newThread = new ServerThread(socket);
                    clientMap.put(socket, new DataOutputStream(socket.getOutputStream()));
                    onlineSocketSet.add(socket);
                    new Thread(newThread).start();
                } catch (RuntimeException e) {
                    System.out.print("Error: " + e.getMessage());
                    break;
                }
            }
        }
    }
}

class ServerThread  implements Runnable {
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
                    String userInput = dis.readUTF();
                    System.out.print(userInput + "\n");
                    System.out.print(socket.getRemoteSocketAddress() + "\n");
                    System.out.print(SPLIT_LINE);
                    sendMSGToAll(userInput);
                }
            } catch (EOFException e) {
                System.out.printf(OFFLINE_MSG,
                        socket.getRemoteSocketAddress());
                Server.onlineSocketSet.remove(socket);
                Server.clientMap.remove(socket);
            } catch (IOException e) {
                throw new RuntimeException(ERROR_MSG);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.print(ERROR_MSG);
                }
            }
    }

    private void sendMSGToAll(String msg) throws IOException {
        for (Socket s : new ArrayList<>(Server.onlineSocketSet)) {
            try{
                String formatMSG;
                if (this.socket == s)
                    formatMSG = String.format("[Me]:\n%s", msg);
                else
                    formatMSG = String.format("[%s]:\n%s", s.getRemoteSocketAddress() ,msg);
                DataOutputStream dos = Server.clientMap.get(s);
                if (dos == null)
                    continue;
                dos.writeUTF(formatMSG);
                dos.flush();
            }  catch (EOFException e) {
                Server.clientMap.get(s).close();
            } catch (IOException e) {
                Server.onlineSocketSet.remove(s);
                DataOutputStream dos = Server.clientMap.get(s);
                if (dos != null)
                    dos.close();
                Server.clientMap.remove(s);
            }
        }


    }
}
