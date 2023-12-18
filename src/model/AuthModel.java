package model;

public class AuthModel {

    private String id;
    private String username;
    private String password;
    private int role;

    public AuthModel(String id, String username, String password, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }


}
