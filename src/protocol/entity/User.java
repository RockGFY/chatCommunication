package protocol.entity;

import java.io.Serializable;

/**
 * Represents a user object
 */
public class User
        extends AbstractEntity
        implements IEntity, Serializable {

    private String name;
    private String password;

    public static User createUser(String name, String password) {
        return new User(name, password);
    }

    public static User createEmptyUser() {
        return new User("", "");
    }

    private User() {}

    private User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
