package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Concurrent_Viva")
public class ConcurrentViva {

    @Column(name = "Concurrent_Viva_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Module")
    private String module;

    @Column(name = "Assessment")
    private String assessment;

    @ManyToOne
    @JoinColumn(name = "Viva_Id")
    private Viva viva;

    @OneToOne
    @JoinColumn(name = "Hall_Id")
    private Hall hall;

    public ConcurrentViva() {}

    public Long getId() {
        return this.id;
    }
}
