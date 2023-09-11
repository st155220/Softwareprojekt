package com.skillball.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
//
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    private String email;
    private String username;
    private String password;
    private String difficulty;
    private int durationQuarter;
    private int rsLength;
    private String language;
    private String level;
    private int index;
    private boolean emailConfirmed;
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getDurationQuarter() {
        return durationQuarter;
    }

    public void setDurationQuarter(int durationQuarter) {
        this.durationQuarter = durationQuarter;
    }

    public int getRsLength() {
        return rsLength;
    }

    public void setRsLength(int rsLength) {
        this.rsLength = rsLength;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRole(Role role) {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        this.roles = roles;
    }
}