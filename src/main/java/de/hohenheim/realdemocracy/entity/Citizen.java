package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;

@Entity
public class Citizen extends User {

    private String ausweisnummer;
    //private List<Debatte> abgestimmte_Debatten;

    public String get_Ausweisnummer() {return ausweisnummer;}
    public void set_Ausweisnummer(String ausweisnummer) {this.ausweisnummer = ausweisnummer;}
}
