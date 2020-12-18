package com.apirest.apiinformatorio.repository;

import com.apirest.apiinformatorio.model.City;
import com.apirest.apiinformatorio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    //SELECT * FROM User AS u WHERE u.city = :city
    public List<User> findByCityEquals(City city);

    //SELECT * FROM User AS u WHERE u.register_date > :register_date
    public List<User> findByRegisterDateAfter(LocalDate date);

}
