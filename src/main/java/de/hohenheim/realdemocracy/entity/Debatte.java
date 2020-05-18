package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Debatte {

    @Id
    @GeneratedValue
    private Integer debatte_Id;

    private Kategorie kategorie;
    private Thema thema;
    //private Politician ersteller;
    private Bundesland bundesland;
    private String titel;
    private String problemstellung;
    private String loesungsstrategie;
    private Date stichtag;
    private int anzahl_Zustimmungen;
    private int anzahl_Ablehnungen;

    public Debatte(Kategorie kategorie, Thema thema, Politician ersteller, Bundesland bundesland, String titel,
                   String problemstellung, String loesungsstrategie, Date stichtag){
        this.kategorie = kategorie;
        this.thema = thema;
        this.bundesland = bundesland;
        this.titel = titel;
        this.problemstellung = problemstellung;
        this.loesungsstrategie = loesungsstrategie;
        this.stichtag = stichtag;
        this.anzahl_Zustimmungen = 0;
        this.anzahl_Ablehnungen = 0;
    }

    public Integer get_Debatte_Id(){
        return debatte_Id;
    }

    public Kategorie get_Kategorie(){ return kategorie;}

    public Thema get_Thema(){
        return thema;
    }

    public Bundesland get_Bundesland(){
        return bundesland;
    }

    public String get_Titel(){
        return titel;
    }

    public String get_Problemstellung(){
        return problemstellung;
    }

    public String get_Loesungsstrategie(){
        return loesungsstrategie;
    }

    public Date get_Stichtag(){
        return stichtag;
    }

    public int get_Anzahl_Zustimmungen(){
        return anzahl_Zustimmungen;
    }

    public int get_Anzahl_Ablehnungen(){
        return anzahl_Ablehnungen;
    }

    public void add_Zustimmung(){
        anzahl_Zustimmungen++;
    }

    public void add_Ablehnung(){
        anzahl_Ablehnungen++;
    }

}
