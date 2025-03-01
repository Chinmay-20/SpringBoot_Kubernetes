// ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=chinmayk/bookmarker-api
// if image name is already added in pom.xml then command will be ./mvnw spring-boot:build-image
// not successful using jib

package com.chinmay.bookmarker.api;

import com.chinmay.bookmarker.domain.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// import java.util.List;

@RestController
@RequestMapping("/api/bookmarks") // common prefix is
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;


    // Request param is for adding pagination support

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "query", defaultValue = "") String query){
        
        

        if (query==null || query.trim().length() == 0){
            return bookmarkService.getBookmarks(page);
        }
        return bookmarkService.searchBookmarks(query, page);
    }

    // after successful creation we want to return success code 201. by default after successful creation spring boot return success code 200
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //for status code 201
    public BookmarkDTO createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
        // take request payload and bind to object. we cannot bind to bookmark entity (spring data jpa entity)
        // because it is not a good idea to tie response to data base structure, we may change database structure
        // BookmarkDTO is not tied to database structure, but it contains whole bookmark data,
        // and to create resource we dont require complete data, we only require URL
        // using same bookmark dto for creation and updation may cause confusion while validating data
        // id is not useful while creating while it is required for validating
        // requestbody and valid to convert request according to creatbookmarkrequest and validate it
        // explaination for return type as well

        return bookmarkService.createBookmark(request);
        // it gives error and whole implementation details if reponse is error may occur if title or url not specified in request body
        // use problem spring web plugin to overcome this issue and return readable error message. tried but didn't worked. see another video mentioned in part 10

    }
}
