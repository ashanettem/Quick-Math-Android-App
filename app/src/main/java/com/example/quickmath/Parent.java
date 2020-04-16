package com.example.quickmath;

public class Parent extends User {

    private String childEmail;

    public Parent() {    }

    public Parent(String firstName, String lastName, String email, String password, String role, String childEmail) {
        super(firstName, lastName, email, password, role);
        this.childEmail = childEmail;
    }

    public String getChildEmail() {
        return childEmail;
    }

    public void setChildEmail(String childEmail) {
        this.childEmail = childEmail;
    }
}
