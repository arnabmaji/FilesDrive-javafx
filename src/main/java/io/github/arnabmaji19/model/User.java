package io.github.arnabmaji19.model;

import org.bson.types.ObjectId;

public class User {
    private ObjectId id;
    private String name;
    private String email;
    private String driveId;
    private String password;

    public User(){}

    public User(ObjectId id, String name, String email, String driveId, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.driveId = driveId;
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDriveId() {
        return driveId;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
