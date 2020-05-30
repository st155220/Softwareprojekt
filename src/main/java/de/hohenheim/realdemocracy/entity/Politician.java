package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;

@Entity
public class Politician extends User{

    private Sektor sektor;
    private String nachname;
    private String vorname;

    public Sektor getSektor(){
        return sektor;
    }
    public void setSektor(Sektor sektor) {this.sektor = sektor;}

    public String getNachname(){
        return nachname;
    }
    public void setNachname(String nachname) {this.nachname = nachname;}

    public String getVorname(){
        return vorname;
    }
    public void setVorname(String vorname) {this.vorname = vorname;}
}
