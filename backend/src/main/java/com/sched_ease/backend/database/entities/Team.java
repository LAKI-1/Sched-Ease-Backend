package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Team_Id")
    private Long id;

    @Column(name = "Team_Type")
    private String teamType;  // "CS" or "SE"

    @OneToMany
    @JoinColumn(name = "Team_Id")
    private List<Student> teamMembers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public List<Student> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<Student> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
