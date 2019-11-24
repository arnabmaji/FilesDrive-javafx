package io.github.arnabmaji19.model;

public class Session {
    private static Session instance = new Session();

    private String username;
    private String email;
    private String driveId;
    private boolean isSessionAvailable = false;

    private Session(){}

    public void create(User user){
        this.username = user.getName();
        this.email = user.getEmail();
        this.driveId = user.getDriveId();
        isSessionAvailable = true;
    }

    public void clear(){
        this.username = null;
        this.email = null;
        this.driveId = null;
        isSessionAvailable = false;
    }

    public static Session getInstance() {
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDriveId() {
        return driveId;
    }

    public boolean isSessionAvailable() {
        return isSessionAvailable;
    }
}
