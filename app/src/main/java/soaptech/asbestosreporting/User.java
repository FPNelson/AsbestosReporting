package soaptech.asbestosreporting;

/**
 * Class representing a user in the database for logging into the application.
 */
public class User {
    private long id;
    private String name, password;

    /**
     * Constructs a User object with an ID, Name, and Password.
     * @param id ID of this user
     * @param name Username of this user
     * @param password Password of this user
     */
    public User(long id, String name, String password) {
        this(name, password);
        this.id = id;
    }

    /**
     * Constructs a User object with a Name and Password.
     * @param name Username of this user
     * @param password Password of this user
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Get the ID of this user.
     * @return The ID of this user.
     */
    public long getId() {
        return id;
    }

    /**
     * Get the username of this user.
     * @return The username of this user.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the username of this user.
     * @param name The new username for this user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the password of this user.
     * @return The password of this user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of this user.
     * @param password The new password for this user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
