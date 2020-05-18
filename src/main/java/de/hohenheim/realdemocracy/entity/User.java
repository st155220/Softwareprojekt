package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class User {

    @Id
    @GeneratedValue
    private Integer user_Id;

    private String e_Mail;
    private String passwort;

    public Integer get_User_Id() { return user_Id; }

    public String get_EMail() { return e_Mail; }

    public void set_E_Mail(String username) { this.e_Mail = username; }

    public String getPassword() { return passwort; }

    public void set_Password(String password) { this.passwort = password; }

}