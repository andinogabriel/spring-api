package com.apirest.apiinformatorio.repository;

import com.apirest.apiinformatorio.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //SELECT * FROM Post AS p WHERE p.title LIKE %:wordTitle%
    public List<Post> findByTitleContaining(String wordTitle);

    //SELECT * FROM Post AS p WHERE p.published = 0
    public List<Post> findByPublishedFalse();



}
