// ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=chinmayk/bookmarker-api
// if image name is already added in pom.xml then command will be ./mvnw spring-boot:build-image
// not successful using jib

package com.chinmay.bookmarker.api;

import com.chinmay.bookmarker.domain.Bookmark;
import com.chinmay.bookmarker.domain.BookmarkService;
import com.chinmay.bookmarker.domain.BookmarksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name="page", defaultValue = "1") Integer page){
        return bookmarkService.getBookmarks(page);
    }
}
