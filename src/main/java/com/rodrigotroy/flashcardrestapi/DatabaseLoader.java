package com.rodrigotroy.flashcardrestapi;

import com.rodrigotroy.flashcardrestapi.entities.User;
import com.rodrigotroy.flashcardrestapi.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * $ Project: flashcard-rest-api
 * User: rodrigotroy
 * Date: 30-03-23
 * Time: 16:06
 */
@Configuration
public class DatabaseLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("John Doe",
                                         "john.doe@example.com"));
            userRepository.save(new User("Jane Doe",
                                         "jane.doe@example.com"));
            userRepository.save(new User("Alice",
                                         "alice@example.com"));
            userRepository.save(new User("Bob",
                                         "bob@example.com"));

            for (int i = 0; i < 10; i++) {
                userRepository.save(new User("User " + i,
                                             "user" + i + "@example.com"));
            }

        };
    }
}
