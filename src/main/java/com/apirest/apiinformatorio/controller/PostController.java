package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.Post;
import com.apirest.apiinformatorio.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok().body(postService.getPosts());
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.addPost(post));
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePostById(@NotNull @RequestBody Post post, @PathVariable("id") Long id) {
        post.setId(id);
        return ResponseEntity.ok().body(postService.updatePost(post));
    }


    @DeleteMapping("{id}")
    public HttpStatus deletePostById(@PathVariable("id") Long id) {
        postService.deletePostById(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/palabra/{wordTitle}")
    public ResponseEntity<List<Post>> findPostsByWordTitle (@PathVariable(value = "wordTitle") String wordTitle) {
        return ResponseEntity.ok().body(postService.findPostsByWordTitle(wordTitle));
    }

    @GetMapping("/no-publicados")
    public ResponseEntity<List<Post>> getPostsNotPublished() {
        return ResponseEntity.ok().body(postService.getPostsNotPublished());
    }

}
