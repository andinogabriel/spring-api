package com.apirest.apiinformatorio.repository;

import com.apirest.apiinformatorio.model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

    @Query(value = "SELECT * FROM Commentary WHERE post_id=:id_post ORDER BY id DESC LIMIT :lim", nativeQuery = true)
    public List<Commentary> getCommentariesPostByLimit(@Param("id_post") Long id_post, @Param("lim") Integer lim);

}
