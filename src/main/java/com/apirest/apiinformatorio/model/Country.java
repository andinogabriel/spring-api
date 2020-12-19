package com.apirest.apiinformatorio.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("country_state")
    private List<State> states;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    @JsonManagedReference("user_country")
    private List<User> users;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<State> getStates() {
        return states;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return name;
    }
}
