package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.exception.UserNotFoundException;
import com.apirest.apiinformatorio.model.City;
import com.apirest.apiinformatorio.model.Post;
import com.apirest.apiinformatorio.model.User;
import com.apirest.apiinformatorio.service.PostService;
import com.apirest.apiinformatorio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("api/v1/usuario")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @RequestMapping("{id}")
    public ResponseEntity<User> getUserById (@PathVariable("id") Long id) {
        boolean isUserExist = userService.isUserExist(id);
        if(isUserExist) {
            return ResponseEntity.ok().body(userService.getUserById(id));
        } else {
            throw new UserNotFoundException();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id ,@RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @DeleteMapping("/eliminar/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return HttpStatus.NO_CONTENT;
    }

    @PostMapping("{id}/crear-post")
    public ResponseEntity<?> addPost(@PathVariable("id") Long id, @NotNull @RequestBody Post post) {
        try {
            post.setUser(userService.getUserById(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(postService.addPost(post));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Usuario con ID: " + id + " no existe.");
        }
    }

    @RequestMapping("/ciudad")
    public ResponseEntity<List<User>> findByCityEquals(@RequestParam("city") City city) {
        return ResponseEntity.ok().body(userService.findByCityEquals(city));
    }

    @RequestMapping(value = "/fecha-creacion")
    public ResponseEntity<List<User>> findByRegisterDateAfter(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return ResponseEntity.ok().body(userService.findByRegisterDateAfter(date));
    }

}
