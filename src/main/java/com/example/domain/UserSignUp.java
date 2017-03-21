package com.example.domain;

/**
 * Created by Administrator on 2017-03-09.
 */
public class UserSignUp {
   public final String firstname;
   public final String mail;
   public final String lastname;
   public final String username;
   public final String password;


    public UserSignUp(String firstname, String mail, String lastname, String username, String password){
        this.firstname = firstname;
        this.mail = mail;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }
}
