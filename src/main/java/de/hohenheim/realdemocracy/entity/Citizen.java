package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;

@Entity
public class Citizen extends User {

    private String ausweisnummer;
    private Bundesland bundesland;
    //private List<Debatte> abgestimmte_Debatten;

    public String get_Ausweisnummer() {return ausweisnummer;}
    public void set_Ausweisnummer(String ausweisnummer) {this.ausweisnummer = ausweisnummer;}

    public Bundesland get_Bundesland() {return bundesland;}
    public void set_Bundesland(Bundesland bundesland) {this.bundesland = bundesland;}

}
