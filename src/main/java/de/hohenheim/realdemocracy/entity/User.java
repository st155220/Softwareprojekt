package de.hohenheim.realdemocracy.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer userId;

    private String username;
    private String password;
    private boolean enabled;
    private Bundesland bundesland;
    private String ausweisnummer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Integer getUserId() { return userId; }

    public String getAusweisnummer() {return ausweisnummer;}
    public void setAusweisnummer(String ausweisnummer) {this.ausweisnummer = ausweisnummer;}

    public String getUsername() { return username;}
    public void setUsername(String username) { this.username = username;}

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;}

    public boolean isEnabled(){return enabled;}
    public void setEnabled(boolean enabled){this.enabled = enabled;}

    public Bundesland getBundesland() {return bundesland;}
    public void setBundesland(Bundesland bundesland) {this.bundesland = bundesland;}

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles;}
}