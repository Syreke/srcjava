package kz.kaznitu.srs4.my_srs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameOfTeam;
    private String league;

    public Team() {
    }

    public Team(int id,String nameOfTeam, String league) {
        this.id=id;
        this.nameOfTeam = nameOfTeam;
        this.league = league;
    }

    public Team(String nameOfTeam, String league) {
        this.nameOfTeam = nameOfTeam;
        this.league = league;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfTeam() {
        return nameOfTeam;
    }

    public void setNameOfTeam(String nameOfTeam) {
        this.nameOfTeam = nameOfTeam;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }
}
