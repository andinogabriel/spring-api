package com.apirest.apiinformatorio.repository;

import com.apirest.apiinformatorio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //Consulta para obtener una lista de usuarios de una determinada ciudad pasada por parametro
    @Query("select u from User u where u.city = :city")
    public List<User> getUsersForCity(@Param("city") String city);


}
