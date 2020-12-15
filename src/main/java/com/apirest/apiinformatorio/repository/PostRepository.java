package com.apirest.apiinformatorio.repository;

import com.apirest.apiinformatorio.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
