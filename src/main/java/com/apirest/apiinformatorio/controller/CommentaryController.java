package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.Commentary;
import com.apirest.apiinformatorio.service.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/comentarios")
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

    @PostMapping
    public ResponseEntity<Commentary> addCommentary(@RequestBody Commentary commentary) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentaryService.addCommentary(commentary));
    }

    @PutMapping("{id}")
    public ResponseEntity<Commentary> updateCommentary(@RequestBody Commentary commentary, @PathVariable("id") Long id) {
        commentary.setId(id);
        return ResponseEntity.ok().body(commentaryService.updateCommentary(commentary));
    }


    public HttpStatus deleteCommentaryById(@PathVariable("id") Long id) {
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/busqueda/{id_post}/{lim}")
    public ResponseEntity<List<Commentary>> getCommentariesPostByLimit(@PathVariable("id_post") Long id_post, @PathVariable("lim") Integer lim) {
        return ResponseEntity.ok().body(commentaryService.getCommentariesPostByLimit(id_post, lim));
    }


}
