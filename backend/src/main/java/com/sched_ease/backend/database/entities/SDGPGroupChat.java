package com.sched_ease.backend.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "SDGP_Group_Chat_Id")
@Table(name = "SDGP_Group_Chat")
public class SDGPGroupChat extends Chat{

    @OneToOne(mappedBy = "sDGPGroupChat")
    private SDGPGroup sDGPGroup;

    public SDGPGroupChat() {}
}
