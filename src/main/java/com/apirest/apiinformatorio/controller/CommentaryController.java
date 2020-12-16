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

    @PostMapping("{post_id}/comment")
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

    @GetMapping("/busqueda")
    public ResponseEntity<List<Commentary>> getCommentariesPostByLimit(@RequestParam(value = "id_post") Long id_post, @RequestParam(value = "lim", required = false) Integer lim) {
        if(lim == null) {
            lim = commentaryService.getCommentaries().size();
        }
        return ResponseEntity.ok().body(commentaryService.getCommentariesPostByLimit(id_post, lim));
    }



}
