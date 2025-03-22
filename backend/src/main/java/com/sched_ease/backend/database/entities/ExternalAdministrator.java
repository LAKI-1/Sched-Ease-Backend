package com.sched_ease.backend.database.entities;


import com.google.gson.JsonObject;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "External_Administrator")
public class ExternalAdministrator {

    @Column(name = "External_Administrator_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "External_Administrator_Name")
    private String name;

    @OneToMany(mappedBy = "administrator", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<TimeTable> timeTables = new ArrayList<>();

    @Column(name = "External_Administrator_Email")
    private String email;

    public ExternalAdministrator() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }

    public String getEmail() {
        return email;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        json.addProperty("name", name);
        json.addProperty("email", email);
        return json;
    }

    @Override
    public String toString() {
        return "ExternalAdministrator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
