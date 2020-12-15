package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.User;
import com.apirest.apiinformatorio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

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
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id ,@RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping("/ciudad/{city}")
    public ResponseEntity<List<User>> getUsersForCity(@PathVariable("city") String city) {
        return ResponseEntity.ok().body(userService.getUsersForCity(city));
    }

    @RequestMapping(value = "/fecha-creacion/{date}")
    public ResponseEntity<List<User>> getUsersFromRegisterDate(@PathVariable(value = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return ResponseEntity.ok().body(userService.getUsersFromRegisterDate(date));
    }

}
