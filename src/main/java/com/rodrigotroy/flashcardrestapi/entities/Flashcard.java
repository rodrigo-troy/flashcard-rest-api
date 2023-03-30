package com.rodrigotroy.flashcardrestapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: flashcard-rest-api
 * User: rodrigotroy
 * Date: 30-03-23
 * Time: 17:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flashcard")
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "spanish_phrases", joinColumns = @JoinColumn(name = "flashcard_id"))
    @Column(name = "spanish_phrase", nullable = false)
    private List<String> spanish;

    @ElementCollection
    @CollectionTable(name = "english_phrases", joinColumns = @JoinColumn(name = "flashcard_id"))
    @Column(name = "english_phrase", nullable = false)
    private List<String> english;

    @ElementCollection
    @CollectionTable(name = "bold_phrases", joinColumns = @JoinColumn(name = "flashcard_id"))
    @Column(name = "bold_phrase", nullable = false)
    private List<String> bold;

    @Column
    private String definition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
