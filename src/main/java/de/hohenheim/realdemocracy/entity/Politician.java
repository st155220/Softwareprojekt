package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;

@Entity
public class Politician extends User{

    private Sektor sektor;
    private String nachname;
    private String vorname;

    public Sektor get_Sektor(){
        return sektor;
    }
    public void set_Sektor(Sektor sektor) {this.sektor = sektor;}

    public String get_Nachname(){
        return nachname;
    }
    public void set_Nachname(String nachname) {this.nachname = nachname;}

    public String get_Vorname(){
        return vorname;
    }
    public void set_Vorname(String vorname) {this.vorname = vorname;}
}
