package com.apirest.apiinformatorio.repository;

import com.apirest.apiinformatorio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
