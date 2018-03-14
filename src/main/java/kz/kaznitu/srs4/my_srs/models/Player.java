package kz.kaznitu.srs4.my_srs.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lastname;
    private String firstname;
    @OneToMany
    private List<Info> infos;
    @OneToMany
    private List<Team> teams;

    public Player() {
    }

    public Player(int id, String lastname, String firstname, List<Info> infos, List<Team> teams) {
        this.id=id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.infos = infos;
        this.teams=teams;
    }

    public Player(String lastname, String firstname, List<Info> infos, List<Team> teams) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.infos = infos;
        this.teams=teams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Info> getInfos() {
        return infos;
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public boolean hasInfo(Info info){
        for(Info containedSkill:getInfos()){
            if(containedSkill.getId() == info.getId()){
                return true;
            }
        }
        return false;
    }

    public boolean hasTeam(Team team){
        for(Team containedSkill:getTeams()){
            if(containedSkill.getId() == team.getId()){
                return true;
            }
        }
        return false;
    }
}
