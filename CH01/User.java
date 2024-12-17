package com.springmvctutorial.springboot_springmvc_first_app.model;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;

    public java.lang.String getFirstName() {
        return firstName;
    }

    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }

    public java.lang.String getLastName() {
        return lastName;
    }

    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public java.lang.String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(java.lang.String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
