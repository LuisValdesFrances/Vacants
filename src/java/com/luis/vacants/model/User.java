package com.luis.vacants.model;

/**
 *
 * @author Luis
 */
public class User {
    private String email;
    private String name;
    private String pass;
    private String profile;
    private String status = "active";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", name=" + name + ", password=" + pass + ", profile=" + profile + ", status=" + status + '}';
    }
    
    
}
