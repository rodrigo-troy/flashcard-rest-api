package com.rodrigotroy.flashcardrestapi.controllers;

import com.rodrigotroy.flashcardrestapi.entities.Flashcard;
import com.rodrigotroy.flashcardrestapi.entities.User;
import com.rodrigotroy.flashcardrestapi.repositories.FlashcardRepository;
import com.rodrigotroy.flashcardrestapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    private final FlashcardRepository flashcardRepository;

    public UserController(UserRepository userRepository,
                          FlashcardRepository flashcardRepository) {
        this.userRepository = userRepository;
        this.flashcardRepository = flashcardRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        LOG.debug("Getting all users");

        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users,
                                    HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                 "User not found"));
        return new ResponseEntity<>(user,
                                    HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return new ResponseEntity<>(newUser,
                                    HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody User userDetails) {
        User existingUser = userRepository.findById(id)
                                          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                         "User not found"));

        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());

        User updatedUser = userRepository.save(existingUser);
        return new ResponseEntity<>(updatedUser,
                                    HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User existingUser = userRepository.findById(id)
                                          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                         "User not found"));

        userRepository.delete(existingUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to get flashcards for a specific user
    @GetMapping("/{id}/flashcards")
    public ResponseEntity<List<Flashcard>> getUserFlashcards(@PathVariable Long id) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                 "User not found"));

        List<Flashcard> flashcards = user.getFlashcards();
        return new ResponseEntity<>(flashcards,
                                    HttpStatus.OK);
    }

    // Endpoint to add a flashcard to a specific user
    @PostMapping("/{id}/flashcards")
    public ResponseEntity<Flashcard> createUserFlashcard(@PathVariable Long id,
                                                         @RequestBody Flashcard flashcard) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                 "User not found"));

        flashcard.setUser(user);
        Flashcard savedFlashcard = flashcardRepository.save(flashcard);
        return new ResponseEntity<>(savedFlashcard,
                                    HttpStatus.CREATED);
    }

}
