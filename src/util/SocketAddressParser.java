package util;

import java.net.SocketAddress;

public class SocketAddressParser {

    public static String getAddress(SocketAddress socketAddress) {
        var addressStr = socketAddress.toString();
        return addressStr.substring(1).split(":")[0];
    }

}
