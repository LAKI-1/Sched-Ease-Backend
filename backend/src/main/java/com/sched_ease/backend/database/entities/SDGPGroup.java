package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "SDGP_Group")
public class SDGPGroup {

    @Column(name = "SDGP_Group_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "SDGP_Group_No")
    private int groupNo;

    @Column(name = "Course")
    private String course;

    @Column(name = "Registration_Status")
    private boolean registrationStatus = false;

    @OneToOne
    @JoinColumn(name = "Group_Chat_Id")
    private Chat groupChat;

    @ManyToOne
    @JoinColumn(name = "Viva_Session_Id")
    private VivaSession vivaSession;

    @ManyToOne
    @JoinColumn(name = "Supervising_Lecturer_Id")
    private Lecturer supervisingLecturer;

    public SDGPGroup(){}


}
