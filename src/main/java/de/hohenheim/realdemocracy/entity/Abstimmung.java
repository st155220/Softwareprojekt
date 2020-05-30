package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Abstimmung {

    @Id
    @GeneratedValue
    private Integer abstimmungId;

    private Integer debatteId;
    private Integer userId;

    public Integer getAbstimmungId(){return abstimmungId;}

    public Integer getDebatteId(){return debatteId;}
    public void setDebatteId(int debatteId){this.debatteId = debatteId;}

    public Integer getUserId(){return userId;}
    public void setUserId(int userId){this.userId = userId;}

}
