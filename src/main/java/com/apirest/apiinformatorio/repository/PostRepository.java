package com.apirest.apiinformatorio.repository;

import com.apirest.apiinformatorio.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM Post AS p WHERE p.title LIKE %:wordTitle%", nativeQuery = true)
    public List<Post> findPostsByWordTitle(@Param("wordTitle") String wordTitle);

    @Query("select p from Post p where p.published = 0")
    public List<Post> getPostsNotPublished();

}
