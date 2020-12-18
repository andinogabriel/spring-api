package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.Commentary;
import com.apirest.apiinformatorio.model.Post;
import com.apirest.apiinformatorio.service.CommentaryService;
import com.apirest.apiinformatorio.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.constraints.NotNull;
import javax.ws.rs.InternalServerErrorException;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentaryService commentaryService;

    @RequestMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok().body(postService.getPosts());
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

    @GetMapping("/palabra")
    public ResponseEntity<List<Post>> findByTitleContaining(@RequestParam("word") String wordTitle) {
        return ResponseEntity.ok().body(postService.findByTitleContaining(wordTitle));
    }

    @GetMapping("/no-publicados")
    public ResponseEntity<List<Post>> findByPublishedFalse() {
        return ResponseEntity.ok().body(postService.findByPublishedFalse());
    }

    //Agregar un comentario al post
    @PostMapping("{id}/comentario")
    public ResponseEntity<?> addCommentaryToPost(@PathVariable("id") Long id, @NotNull @RequestBody Commentary commentary) {
        try {
            commentary.setPost(postService.getPostById(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(commentaryService.addCommentary(commentary));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, Post con ID: " + id + " no encontrado");
        }
    }

    //Mostrar los comentarios de un post y si se pasa un limite n mostrar los ultimos n comentarios
    @GetMapping("{id_post}/comentarios")
    public ResponseEntity<?> getCommentariesPostByLimit(@PathVariable(value = "id_post") Long id_post, @RequestParam(value = "lim", required = false) Integer lim) {
        try {
            if (lim == null) {
                lim = commentaryService.findByPostOrderByIdDesc(postService.getPostById(id_post)).size();
            }
            return ResponseEntity.ok().body(commentaryService.getCommentariesPostByLimit(id_post, lim));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, Post con ID: " + id_post + " no encontrado");
        }
    }

    //Actualizar comentario de un post
    @PutMapping("{id_post}/actualizar-comentario")
    public ResponseEntity<?> updateCommentary(@PathVariable("id_post") Long id_post, @RequestBody Commentary commentary, @RequestParam Long id_comentario) {
        try {
            commentary.setId(id_comentario);
            commentary.setPost(postService.getPostById(id_post));
            return ResponseEntity.ok().body(commentaryService.updateCommentary(commentary));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, Post con ID: " + id_post + " no encontrado");
        }

    }

    //Eliminar comentario de un post
    @DeleteMapping("{id_post}/comentarios/eliminar")
    public ResponseEntity<?> deleteCommentaryById(@PathVariable("id_post") Long id_post, @RequestParam("id_comentario") Long id_comentario) {
        Optional<Post> optionalPost = Optional.ofNullable(postService.getPostById(id_post));
        Optional<Commentary> optionalCommentary = Optional.ofNullable(commentaryService.getCommentaryById(id_comentario));

        try {
            if(optionalPost.isPresent()){
                if(optionalCommentary.isPresent()){
                    commentaryService.deleteCommentaryById(id_comentario);
                    return ResponseEntity.ok().body("Comentario con ID: " + id_comentario + " eliminado satisfactoriamente.");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error, comentario con ID: " + id_comentario + " no encontrado");
                }
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error, Post con ID: " + id_post + " no encontrado");
            }
        } catch (HttpServerErrorException.InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error, Post con ID: " + id_post + " no encontrado");
        }
    }

}