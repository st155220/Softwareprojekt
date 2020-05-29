package de.hohenheim.realdemocracy.entity;

import javax.persistence.*;

@Entity
public class Debatte {

    @Id
    @GeneratedValue
    private Integer debatte_Id;

    private Sektor sektor;
    @ManyToOne
    private Politician ersteller;
    private Bundesland bundesland;
    private String titel;
    private String problemstellung;
    private String loesungsstrategie;
    private String stichtag;
    private int anzahl_Zustimmungen;
    private int anzahl_Ablehnungen;

    public Debatte(){anzahl_Zustimmungen = 0; anzahl_Ablehnungen=0;}

    public Integer get_Debatte_Id(){
        return debatte_Id;
    }

    public Politician get_Ersteller(){
        return ersteller;
    }
    public void setErsteller(Politician ersteller){this.ersteller = ersteller;}

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

    public void add_Zustimmung(){ anzahl_Zustimmungen++; }
    public void add_Ablehnung(){
        anzahl_Ablehnungen++;
    }

}
