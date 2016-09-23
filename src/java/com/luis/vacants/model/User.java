package com.luis.vacants.model;

/**
 *
 * @author Luis
 */
public class User {
    private String email;
    private String name;
    private String username;
    private String password;
    private String profile;
    private String status = "active";

    public User(String email) {
        this.email = email;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "User{" + "email=" + email + ", name=" + name + ", username=" + username + ", password=" + password + ", profile=" + profile + ", status=" + status + '}';
    }
    
    
}
