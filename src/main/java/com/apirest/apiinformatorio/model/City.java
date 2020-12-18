package com.apirest.apiinformatorio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    @JsonBackReference("state_city")
    private State state;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonManagedReference("user_city")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(State state) {
        this.state = state;
    }

}
