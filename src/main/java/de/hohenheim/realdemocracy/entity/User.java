package de.hohenheim.realdemocracy.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer user_Id;

    private String e_Mail;
    private String passwort;
    private Bundesland bundesland;

    private String ausweisnummer;

    public String get_Ausweisnummer() {return ausweisnummer;}
    public void set_Ausweisnummer(String ausweisnummer) {this.ausweisnummer = ausweisnummer;}

    public Integer get_User_Id() { return user_Id; }

    public String get_E_Mail() { return e_Mail; }
    public void set_E_Mail(String username) { this.e_Mail = username; }

    public String getPasswort() { return passwort; }
    public void set_Passwort(String passwort) { this.passwort = passwort; }

    public Bundesland get_Bundesland() {return bundesland;}
    public void set_Bundesland(Bundesland bundesland) {this.bundesland = bundesland;}
}