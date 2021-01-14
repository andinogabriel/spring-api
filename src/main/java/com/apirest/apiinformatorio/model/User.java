package com.apirest.apiinformatorio.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;



import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    @NotBlank(message = "Ingrese su nombre.")
    private String name;

    @NotBlank(message = "Ingrese su apellido.")
    @Column(length = 150)
    private String lastName;

    @Email(message = "Ingrese un email valido.")
    @Column(length = 100, unique = true)
    private String email;

    @NotBlank(message = "Ingrese su password.")
    private String password;

    @Column(length = 100)
    @NotBlank(message = "Reingrese su password.")
    private String rpassword;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate registerDate;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "city_id", referencedColumnName = "id", unique = false, nullable = true) })
    @JsonBackReference("user_city")
    private City city;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "state_id", referencedColumnName = "id", unique = false, nullable = true) })
    @JsonBackReference("user_state")
    private State state;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "country_id", referencedColumnName = "id", unique = false, nullable = true) })
    @JsonBackReference("user_country")
    private Country country;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference("user_comments")
    private List<Commentary> comments;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference("user_post")
    private List<Post> posts;


    public List<Post> getPosts() {
        return posts;
    }

    public List<Commentary> getComments() {
        return comments;
    }

    public void setComments(List<Commentary> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rpassword='" + rpassword + '\'' +
                ", city=" + city +
                ", state=" + state +
                ", country=" + country +
                '}';
    }
}
