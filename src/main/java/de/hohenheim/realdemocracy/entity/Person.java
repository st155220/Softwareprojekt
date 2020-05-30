package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    private String ausweisnummer;

    private String nachname;
    private Bundesland bundesland;
    private boolean istWahlbereichtigt;

    public void setAusweisnummer(String ausweisnummer){this.ausweisnummer = ausweisnummer;}
    public String getAusweisnummer(){return ausweisnummer;}

    public void setNachname(String nachname){this.nachname = nachname;}
    public String getNachname(){return nachname;}

    public void setBundesland(Bundesland bundesland){this.bundesland = bundesland;}
    public Bundesland getBundesland(){return bundesland;}

    public void setIstWahlbereichtigt(boolean istWahlbereichtigt){this.istWahlbereichtigt = istWahlbereichtigt;}
    public boolean istWahlbereichtigt(){return istWahlbereichtigt;}


}
