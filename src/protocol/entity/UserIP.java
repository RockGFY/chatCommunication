package protocol.entity;

import java.io.Serializable;

/**
 * Represents an object gets pass around with only names and ips.
 */
public class UserIP
        extends AbstractEntity
        implements IEntity, Serializable {

    private String userName;
    private String address;

    public static UserIP createUserIP(
            String userName,
            String address) {
        return new UserIP(userName, address);
    }

    private UserIP() {
    }

    private UserIP(String userName, String address) {
        this.userName = userName;
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
