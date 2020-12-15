package com.apirest.apiinformatorio.service;

import com.apirest.apiinformatorio.exception.ResourceNotFoundException;
import com.apirest.apiinformatorio.model.User;
import com.apirest.apiinformatorio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);

    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new ResourceNotFoundException("Usuario con id: "+id+" no encontrado.");
        }
    }

    public void deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            String message = "Usuario: "+id+" eliminado.";
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("\nUsuario con id: "+id+" no encontrado.\n");
        }
    }


    public User updateUser(User user) {
        Optional<User> optionalUser = this.userRepository.findById(user.getId());
        if(optionalUser.isPresent()) {
            User userToUpDate = optionalUser.get();
            userToUpDate.setName(user.getName());
            userToUpDate.setLastName(user.getLastName());
            userToUpDate.setEmail(user.getEmail());
            userToUpDate.setPassword(user.getPassword());
            userToUpDate.setCountry(user.getCountry());
            userToUpDate.setState(user.getState());
            userToUpDate.setCity(user.getCity());
            userRepository.save(userToUpDate);
            return userToUpDate;
        } else {
            throw new ResourceNotFoundException("Usuario con id: "+user.getId()+" no encontrado.");
        }
    }

    public List<User> getUsersForCity(String city) {
        return userRepository.getUsersForCity(city);
    }

    public List<User> getUsersFromRegisterDate(LocalDate date) {
        return userRepository.getUserForRegisterDate(date);
    }


}
