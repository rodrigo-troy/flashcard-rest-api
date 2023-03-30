package com.rodrigotroy.flashcardrestapi;

import com.rodrigotroy.flashcardrestapi.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * $ Project: flashcard-rest-api
 * User: rodrigotroy
 * Date: 30-03-23
 * Time: 17:37
 */

@Component
@Order(2)
public class JsonLoader {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {

        };
    }
}
