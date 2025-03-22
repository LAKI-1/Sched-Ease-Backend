package com.sched_ease.backend.dto;

import com.google.gson.JsonObject;
import com.sched_ease.backend.database.entities.ExternalAdministrator;
import com.sched_ease.backend.database.entities.SDGPLecturer;
import com.sched_ease.backend.database.entities.SDGPStudent;
import com.sched_ease.backend.database.entities.Student;

public class UserResponse {

    private final Long id;
    private final String name;
    private final String email;
    private String avatar = null;
//    private final String role;

    public UserResponse(SDGPStudent sdgpStudent) {
        this.id = sdgpStudent.getId();
        this.name = sdgpStudent.getName();
        this.email = sdgpStudent.getEmail();
    }

    public UserResponse(SDGPLecturer sdgpLecturer) {
        this.id = sdgpLecturer.getId();
        this.name = sdgpLecturer.getName();
        this.email = sdgpLecturer.getEmail();
    }

    public UserResponse(ExternalAdministrator exAdmin) {
        this.id = exAdmin.getId();
        this.name = exAdmin.getName();
        this.email = exAdmin.getEmail();
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        json.addProperty("name", name);
        json.addProperty("email", email);
        json.addProperty("avatar", avatar);
//        json.addProperty("tutorial_group", tutorialGroup.getId());
        return json;
    }
}
