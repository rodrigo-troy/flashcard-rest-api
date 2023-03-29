package com.rodrigotroy.flashcardrestapi.repositories;

import com.rodrigotroy.flashcardrestapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * $ Project: flashcard-rest-api
 * User: rodrigotroy
 * Date: 29-03-23
 * Time: 18:29
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

