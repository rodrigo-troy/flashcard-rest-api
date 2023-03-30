package com.rodrigotroy.flashcardrestapi.controllers;

import com.rodrigotroy.flashcardrestapi.entities.Flashcard;
import com.rodrigotroy.flashcardrestapi.repositories.FlashcardRepository;
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
 * Date: 30-03-23
 * Time: 17:42
 */
@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {
    private static final Logger LOG = LoggerFactory.getLogger(FlashcardController.class);

    private final FlashcardRepository flashcardRepository;

    public FlashcardController(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    @GetMapping
    public ResponseEntity<List<Flashcard>> getAllFlashcards() {
        List<Flashcard> flashcards = flashcardRepository.findAll();
        return new ResponseEntity<>(flashcards,
                                    HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flashcard> getFlashcardById(@PathVariable Long id) {
        Flashcard flashcard = flashcardRepository.findById(id)
                                                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                                "Flashcard not found"));
        return new ResponseEntity<>(flashcard,
                                    HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Flashcard> createFlashcard(@RequestBody Flashcard flashcard) {
        Flashcard savedFlashcard = flashcardRepository.save(flashcard);
        return new ResponseEntity<>(savedFlashcard,
                                    HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flashcard> updateFlashcard(@PathVariable Long id,
                                                     @RequestBody Flashcard flashcardDetails) {
        Flashcard existingFlashcard = flashcardRepository.findById(id)
                                                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                                        "Flashcard not found"));

        existingFlashcard.setSpanish(flashcardDetails.getSpanish());
        existingFlashcard.setEnglish(flashcardDetails.getEnglish());
        existingFlashcard.setBold(flashcardDetails.getBold());
        existingFlashcard.setDefinition(flashcardDetails.getDefinition());

        Flashcard updatedFlashcard = flashcardRepository.save(existingFlashcard);
        return new ResponseEntity<>(updatedFlashcard,
                                    HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Long id) {
        Flashcard existingFlashcard = flashcardRepository.findById(id)
                                                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                                        "Flashcard not found"));

        flashcardRepository.delete(existingFlashcard);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
