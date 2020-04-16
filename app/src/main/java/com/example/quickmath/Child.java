package com.example.quickmath;

public class Child extends User {

    private String username;

    public Child() {    }

    public Child(String firstName, String lastName, String username, String password, String role) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(username);
        this.username = username;
        this.setPassword(password);
        this.setRole(role);


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
