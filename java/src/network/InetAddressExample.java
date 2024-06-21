package network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class InetAddressExample {
    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("내 컴퓨터의 IP Address : "+localHost.getHostAddress());

            InetAddress[] allByName = InetAddress.getAllByName("www.naver.com");
            Arrays.stream(allByName).forEach(System.out::println);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
