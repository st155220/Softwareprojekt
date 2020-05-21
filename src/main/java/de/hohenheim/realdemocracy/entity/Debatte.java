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
    private String stichtag;
    private int anzahl_Zustimmungen;
    private int anzahl_Ablehnungen;

    public Integer get_Debatte_Id(){
        return debatte_Id;
    }

    public Kategorie get_Kategorie(){ return kategorie;}
    public void setKategorie(Kategorie kategorie){this.kategorie = kategorie;}

    public Thema get_Thema(){
        return thema;
    }
    public void setThema(Thema thema){this.thema = thema;}

    public Bundesland get_Bundesland(){
        return bundesland;
    }
    public void setBundesland(Bundesland bundesland){this.bundesland = bundesland;}

    public String get_Titel(){
        return titel;
    }
    public void setTitel(String titel){this.titel = titel;}

    public String get_Problemstellung(){
        return problemstellung;
    }
    public void setProblemstellung(String problemstellung){this.problemstellung = problemstellung;}

    public String get_Loesungsstrategie(){
        return loesungsstrategie;
    }
    public void setLoesungsstrategie(String loesungsstrategie){this.loesungsstrategie = loesungsstrategie;}

    public String get_Stichtag(){
        return stichtag;
    }
    public void setStichtag(String stichtag){this.stichtag = stichtag;}

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
