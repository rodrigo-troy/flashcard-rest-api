package com.rodrigotroy.flashcardrestapi.controllers;

import com.rodrigotroy.flashcardrestapi.entities.User;
import com.rodrigotroy.flashcardrestapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * $ Project: flashcard-rest-api
 * User: rodrigotroy
 * Date: 29-03-23
 * Time: 18:30
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        LOG.debug("Getting all users");

        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getCoffeeOrder(@PathVariable Long id) {
        LOG.debug("Getting user with id: {}",
                  id);

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        LOG.debug("Creating user: {}",
                  user);
        return userRepository.save(user);
    }
}
