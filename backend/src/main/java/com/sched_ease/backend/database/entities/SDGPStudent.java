package com.sched_ease.backend.database.entities;


import com.google.gson.JsonObject;
import com.sched_ease.backend.dto.UserResponse;
import jakarta.persistence.*;

@Entity
//@PrimaryKeyJoinColumn(name = "student_id") // Uses the same ID as Student
@Table(name = "sdgp_student")
public class SDGPStudent extends Student{

    @Column(name = "SDGP_Leader_Flag")
    private Boolean leaderFlag = false;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "sdgp_group_chat_id", nullable = true)
    private SDGPGroup SDGPGroup;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "leader_supervisor_chat_id", nullable = true)
    private LeadersSupervisorChat leadersSupervisorChat;

    public SDGPStudent(Student student) {
        //super(student.getId(), student.getName(), student.getCourse(), student.getEmail(), student.getSemester(), student.getYear(), student.getTutorialGroup());

//        this.id = student.getId();
//        this.leaderFlag = false;

        super(student.getId(), student.getName(), student.getCourse(), student.getEmail(), student.getSemester(), student.getYear(), student.getTutorialGroup());
        this.leaderFlag = false;
    }

    public SDGPStudent(Student student, boolean leaderFlag) {
        //super(student.getId(), student.getName(), student.getCourse(), student.getEmail(), student.getSemester(), student.getYear(), student.getTutorialGroup());

//        this.id = student.getId();
//        this.leaderFlag = false;

        super(student.getId(), student.getName(), student.getCourse(), student.getEmail(), student.getSemester(), student.getYear(), student.getTutorialGroup());
        this.leaderFlag = leaderFlag;
    }

    public SDGPStudent() {
        //super();
    }

//    public SDGPStudent(Student student){
//        super(student.getName(), student.getCourse(), student.getEmail(), student.getSemester(), student.getYear(), student.getTutorialGroup());
//    }

    public Boolean getLeaderFlag() {
        return leaderFlag;
    }

    public void setLeaderFlag(Boolean leaderFlag) {
        this.leaderFlag = leaderFlag;
    }

    public SDGPGroup getSDGPGroup() {
        return SDGPGroup;
    }

    public void setSDGPGroup(SDGPGroup SDGPGroup) {
        this.SDGPGroup = SDGPGroup;
    }

    public LeadersSupervisorChat getLeaderSupervisorChat() {
        return leadersSupervisorChat;
    }

    public void setLeaderSupervisorChat(LeadersSupervisorChat leaderSupervisorChat) {
        this.leadersSupervisorChat = leaderSupervisorChat;
    }

    @Override
    public String toString() {
        return "student={" +super.toString() +
                "}, leaderFlag=" + leaderFlag +
                ", SDGPGroup=" + SDGPGroup +
                ", leadersSupervisorChat=" + leadersSupervisorChat;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.add("student", super.toJson());  // Convert superclass properties to JSON
        json.addProperty("leaderFlag", leaderFlag);
        return json;
    }




}
