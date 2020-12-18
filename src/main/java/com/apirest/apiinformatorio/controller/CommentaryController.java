package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.Commentary;
import com.apirest.apiinformatorio.model.Post;
import com.apirest.apiinformatorio.model.User;
import com.apirest.apiinformatorio.service.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/post/comentarios")
@RestController
public class CommentaryController {

    @Autowired
    private CommentaryService commentaryService;

    @GetMapping
    public ResponseEntity<List<Commentary>> getCommentaries() {
        return ResponseEntity.ok().body(commentaryService.getCommentaries());
    }

    @RequestMapping("{id}")
    public ResponseEntity<Commentary> getCommentaryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(commentaryService.getCommentaryById(id));
    }


}
