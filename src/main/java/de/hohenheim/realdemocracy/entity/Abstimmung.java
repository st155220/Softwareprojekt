package de.hohenheim.realdemocracy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Abstimmung {

    @Id
    @GeneratedValue
    private Integer abstimmung_Id;

    private Integer debatte_Id;
    private Integer user_Id;

    public Integer get_Debatte_Id(){return debatte_Id;}
    public void set_Debatte_Id(int debatte_Id){this.debatte_Id = debatte_Id;}

    public Integer get_User_Id(){return user_Id;}
    public void set_User_Id(int user_Id){this.user_Id = user_Id;}

}
