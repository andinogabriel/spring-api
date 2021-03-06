package com.apirest.apiinformatorio.service;

import com.apirest.apiinformatorio.exception.ResourceNotFoundException;
import com.apirest.apiinformatorio.model.City;
import com.apirest.apiinformatorio.model.User;
import com.apirest.apiinformatorio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
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

    public boolean isUserExist(Long id) {
        return userRepository.existsById(id);
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
        if(userRepository.findById(user.getId()).isPresent()) {
            User userToUpDate = new User();
            userToUpDate.setId(user.getId());
            userToUpDate.setName(user.getName());
            userToUpDate.setLastName(user.getLastName());
            userToUpDate.setEmail(user.getEmail());
            userToUpDate.setCountry(user.getCountry());
            userToUpDate.setState(user.getState());
            userToUpDate.setCity(user.getCity());
            userRepository.save(userToUpDate);
            return userToUpDate;
        } else {
            throw new ResourceNotFoundException("Usuario con id: "+user.getId()+" no encontrado.");
        }
    }

    public List<User> findByCityEquals(City city) {
        return userRepository.findByCityEquals(city);
    }

    public List<User> findByRegisterDateAfter(LocalDate date) {
        return userRepository.findByRegisterDateAfter(date);
    }


}
