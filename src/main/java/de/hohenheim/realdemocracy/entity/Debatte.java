package de.hohenheim.realdemocracy.entity;

import javax.persistence.*;

@Entity
public class Debatte {

    @Id
    @GeneratedValue
    private Integer debatteId;

    private Sektor sektor;
    private Bundesland bundesland;
    private String titel;
    private String problemstellung;
    private String loesungsstrategie;
    private String stichtag;
    private int anzahlZustimmungen;
    private int anzahlAblehnungen;
    @ManyToOne
    private Politician ersteller;

    public Integer getDebatteId(){
        return debatteId;
    }

    public Bundesland getBundesland(){
        return bundesland;
    }
    public void setBundesland(Bundesland bundesland){this.bundesland = bundesland;}

    public String getTitel(){
        return titel;
    }
    public void setTitel(String titel){this.titel = titel;}

    public String getProblemstellung(){
        return problemstellung;
    }
    public void setProblemstellung(String problemstellung){this.problemstellung = problemstellung;}

    public String getLoesungsstrategie(){
        return loesungsstrategie;
    }
    public void setLoesungsstrategie(String loesungsstrategie){this.loesungsstrategie = loesungsstrategie;}

    public String getStichtag(){
        return stichtag;
    }
    public void setStichtag(String stichtag){this.stichtag = stichtag;}

    public int getAnzahlZustimmungen(){
        return anzahlZustimmungen;
    }
    public int getAnzahlAblehnungen(){
        return anzahlAblehnungen;
    }

    public void addZustimmung(){ anzahlZustimmungen++; }
    public void addAblehnung(){
        anzahlAblehnungen++;
    }

    public Politician getErsteller(){
        return ersteller;
    }
    public void setErsteller(Politician ersteller){this.ersteller = ersteller;}

}
