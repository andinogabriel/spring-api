package com.apirest.apiinformatorio.repository;

import com.apirest.apiinformatorio.model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

}
