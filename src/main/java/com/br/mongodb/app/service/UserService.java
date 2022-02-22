package com.br.mongodb.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.mongodb.app.dto.UserDTO;
import com.br.mongodb.app.model.User;
import com.br.mongodb.app.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).get();
    }

    public User saveUser(UserDTO userDTO) {
        User _user = new User();
        _user.setFirstName(userDTO.getFirstName());
        _user.setLastName(userDTO.getLastName());
        return userRepository.save(_user);
    }

    public User updateUser(Optional<User> userData, UserDTO userDTO) {
        User _user = userData.get();
        _user.setId(userDTO.getId());
        _user.setFirstName(userDTO.getFirstName());
        _user.setLastName(userDTO.getLastName());
        return userRepository.save(_user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public Optional<User> getUserOptional(String userId) {
        return Optional.ofNullable(userRepository.findById(userId).orElse(null));
    }
}
