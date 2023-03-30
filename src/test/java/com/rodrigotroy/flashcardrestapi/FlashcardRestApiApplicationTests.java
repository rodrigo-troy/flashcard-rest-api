package com.rodrigotroy.flashcardrestapi;

import com.rodrigotroy.flashcardrestapi.entities.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FlashcardRestApiApplicationTests {
    private static final Logger LOG = LoggerFactory.getLogger(FlashcardRestApiApplicationTests.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    void testGetAllUsers() {
        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {
        };
        ResponseEntity<List<User>> response = restTemplate.exchange("/api/users",
                                                                    HttpMethod.GET,
                                                                    null,
                                                                    responseType);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            for (User user : response.getBody()) {
                LOG.info("User: " + user);
            }

        }

        assertEquals(HttpStatus.OK,
                     response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetUser() {
        // Replace '1L' with an existing user ID in your test database
        Long userId = 1L;
        ResponseEntity<User> response = restTemplate.getForEntity("/api/users/" + userId,
                                                                  User.class);
        assertEquals(HttpStatus.OK,
                     response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userId,
                     response.getBody().getId());
    }

    @Test
    void testGetUserNotFound() {
        // Replace '999L' with a non-existent user ID
        Long userId = 999L;
        ResponseEntity<String> response = restTemplate.getForEntity("/api/users/" + userId,
                                                                    String.class);
        assertEquals(HttpStatus.NOT_FOUND,
                     response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("User not found",
                     response.getBody());
    }

    @Test
    void testCreateUser() {
        User newUser = new User();
        newUser.setName("John Doe");
        newUser.setEmail("john.doe@example.com");


        ResponseEntity<User> response = restTemplate.postForEntity("/api/users",
                                                                   newUser,
                                                                   User.class);
        assertEquals(HttpStatus.CREATED,
                     response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(newUser.getName(),
                     response.getBody().getName());
        assertEquals(newUser.getEmail(),
                     response.getBody().getEmail());
    }
}
