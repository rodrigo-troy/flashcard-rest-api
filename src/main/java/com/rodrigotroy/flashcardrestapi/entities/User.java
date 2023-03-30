package com.rodrigotroy.flashcardrestapi.entities;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * $ Project: flashcard-rest-api
 * User: rodrigotroy
 * Date: 29-03-23
 * Time: 18:19
 */
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    public User() {

    }
}

