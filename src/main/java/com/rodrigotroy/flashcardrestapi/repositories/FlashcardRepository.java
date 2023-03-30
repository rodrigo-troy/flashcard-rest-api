package com.rodrigotroy.flashcardrestapi.repositories;

import com.rodrigotroy.flashcardrestapi.entities.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * $ Project: flashcard-rest-api
 * User: rodrigotroy
 * Date: 30-03-23
 * Time: 17:38
 */
@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
}
