package com.example.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2017-03-09.
 */
public class UserSignUp {
    @Size(min = 1)
    private String firstname;
    @NotNull
    private String mail;
    @NotNull
    private String lastname;
    @NotNull
    private String username;
    @NotNull
    private String password;


    public UserSignUp() {

    }
    public UserSignUp(String firstname, String mail, String lastname, String username, String password){
        this.firstname = firstname;
        this.mail = mail;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMail() {
        return mail;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
