package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Citizen extends User {

    private String ausweisnummer;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Debatte> abgestimmte_Debatten;

    public Citizen(){this.abgestimmte_Debatten = new HashSet<>(); }

    public String get_Ausweisnummer() {return ausweisnummer;}
    public void set_Ausweisnummer(String ausweisnummer) {this.ausweisnummer = ausweisnummer;}

    public Set<Debatte> get_Abgestimmte_Debatten(){return abgestimmte_Debatten;}
    public void add_Abgestimmte_Debatte(Debatte debatte){abgestimmte_Debatten.add(debatte);}
}
