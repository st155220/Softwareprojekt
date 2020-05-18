package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;

@Entity
public class Politician extends User{

    private String sektor;
    private String nachname;
    private String vorname;

    public Politician(String sektor, String nachname, String vorname){
        this.sektor = sektor;
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public String get_Sektor(){
        return sektor;
    }

    public String get_Nachname(){
        return nachname;
    }

    public String get_Vorname(){
        return vorname;
    }

}
