package de.hohenheim.realdemocracy.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer user_Id;

    private String e_mail;
    private String passwort;
    private Bundesland bundesland;

    private String ausweisnummer;

    public String get_Ausweisnummer() {return ausweisnummer;}
    public void set_Ausweisnummer(String ausweisnummer) {this.ausweisnummer = ausweisnummer;}

    public Integer get_User_Id() { return user_Id; }

    public String get_E_Mail() { return e_mail; }
    public void set_E_Mail(String username) { this.e_mail = username; }

    public String getPasswort() { return passwort; }
    public void set_Passwort(String passwort) { this.passwort = passwort; }

    public Bundesland get_Bundesland() {return bundesland;}
    public void set_Bundesland(Bundesland bundesland) {this.bundesland = bundesland;}

    public static boolean email_Format_Passt(String email){
        int step_counter = 0;
        for (char c : email.toCharArray()){
            if (step_counter == 0 && c != '@' && c!='.'){
                step_counter++;
            }else if(step_counter == 1 && c=='@'){
                step_counter++;
            }else if(step_counter == 2 && c != '@' && c!='.'){
                step_counter++;
            }else if(step_counter == 3 && c == '.'){
                step_counter++;
            } else if(step_counter == 4 && c != '@' && c!='.'){
                step_counter++;
            }
        }
        return step_counter == 5;
    }

    public static boolean passwort_Format_Passt(String passwort){
        boolean sonderzeichen = false;
        boolean zahl = false;
        boolean grossbuchstabe = false;
        boolean kleinbuchstabe = false;

        for (char c: passwort.toCharArray()){
            if(c == '!' || c == '$' ||  c == '%' ||  c == '&' ||  c == '/' ||  c == '?' ||  c == '+' ||  c == '-' ||  c == '*' ||  c == '#' || c == '=' || c == '.'){
                sonderzeichen = true;
            }
            if(Character.isDigit(c)){
                zahl = true;
            }
           if(Character.isLetter(c) && Character.isUpperCase(c)){
               grossbuchstabe = true;
           }
            if(Character.isLetter(c) && Character.isLowerCase(c)){
                kleinbuchstabe = true;
            }
        }
        return passwort.length() >= 10 && sonderzeichen && zahl && grossbuchstabe && kleinbuchstabe;
    }
}