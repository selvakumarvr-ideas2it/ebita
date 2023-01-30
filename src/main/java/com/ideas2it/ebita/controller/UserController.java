package com.ideas2it.ebita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ideas2it.ebita.entity.User;
import com.ideas2it.ebita.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
        userRepo.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userRepo.getUser(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepo.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable String id) {
        return userRepo.deleteUser(id);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userRepo.updateUser(user);
    }

}
