package com.apirest.apiinformatorio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class State {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;


    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @JsonBackReference("country_state")
    private Country country;


    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    @JsonManagedReference("state_city")
    private List<City> cities;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    @JsonManagedReference("user_state")
    private List<User> users;


    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
