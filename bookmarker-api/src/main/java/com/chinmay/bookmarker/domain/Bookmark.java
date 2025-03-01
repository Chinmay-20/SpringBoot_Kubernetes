package com.chinmay.bookmarker.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "bookmarks")
// generate setters and getters using Lombok
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class  Bookmark {
    @Id
    @SequenceGenerator(name = "bm_id_seq_gen", sequenceName = "bm_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bm_id_seq_gen")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String url;
    private Instant createdAt;
}

// # used hibernates auto table generation support 
// # not a good idea to manage database schema in production grid
// we have a property title which maps to column title in database 
// if we change title to something else (name) and start application again it will not rename title to name, it will create a new column name
// so use database migration tool to use in business applications
// in spring boot there are two widely used flyway and liquibase  we will use flyway