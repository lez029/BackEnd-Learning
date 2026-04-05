import java.net.InetAddress;

public class InetAddressTest {
    public static void main(String[] args) throws Exception {
        InetAddress ipAddress = InetAddress.getLocalHost();
        String hostName = ipAddress.getHostName();
        String hostAddress = ipAddress.getHostAddress();
        if (ipAddress.isReachable(500)) {
            System.out.printf("Host Name: %s\n", hostName);
            System.out.printf("Host Address: %s\n", hostAddress);
        }

        ipAddress = InetAddress.getByName("www.baidu.com");
        hostName = ipAddress.getHostName();
        hostAddress = ipAddress.getHostAddress();
        if (ipAddress.isReachable(500)) {
            System.out.printf("Host Name: %s\n", hostName);
            System.out.printf("Host Address: %s\n", hostAddress);
        }
    }
}
