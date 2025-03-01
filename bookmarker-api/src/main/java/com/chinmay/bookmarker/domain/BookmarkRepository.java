package com.chinmay.bookmarker.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// import java.awt.print.Book;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("select new com.chinmay.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b")
    Page<BookmarkDTO> findBookmarks(Pageable pageable);

    @Query("""
            select new com.chinmay.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b
            where lower(b.title) like lower(concat('%', :query, '%'))
            """)
    Page<BookmarkDTO> searchBookmarks(String query, Pageable pageable);

    Page<BookmarkVM> findByTitleContainsIgnoreCase(String query, Pageable pageable); //Spring Data JPA
}
