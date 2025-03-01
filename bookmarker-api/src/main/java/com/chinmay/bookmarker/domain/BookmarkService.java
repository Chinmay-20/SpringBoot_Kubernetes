package com.chinmay.bookmarker.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
// import java.util.List; 
@Service
@Transactional
@RequiredArgsConstructor 
// instead of creating a custom constructor we used lombok required constructor which will generate constructor for final variables
public class BookmarkService {
    private final BookmarkRepository repository;
    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true) // hibernate does some performance optimization doing only read only operations
    public BookmarksDTO getBookmarks(Integer page){

        // int pageNo = page < 1 ? 0 : page -1;
        // Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        // return repository.findAll(pageable).getContent(): we are using repository findall and loading all book marks
        // it is not a good idea to return entities to frontend as response because if there is any change in identity it will affect response
        // don't return direct entities to response 
        // we load entities to make any modifications and process back to database but here we are reading data. better case is read data in DTO
        // use DTO projection (new section) 
         
        int pageNo = page < 1 ? 0 : page-1;
        Pageable pageable = PageRequest.of(pageNo,10, Sort.Direction.DESC, "id");
        Page<BookmarkDTO> bookmarkPage = repository.findBookmarks(pageable); // reading entities in form of DTOs not loading all entities directly 

        return new BookmarksDTO(bookmarkPage) ;
    }
    @Transactional(readOnly = true)
    public BookmarksDTO searchBookmarks(String query, Integer page) {
        int pageNo = page < 1 ? 0 : page-1;
        Pageable pageable = PageRequest.of(pageNo,10, Sort.Direction.DESC, "id");
        //Page<BookmarkDTO> bookmarkPage = repository.searchBookmarks(query, pageable);
        Page<BookmarkDTO> bookmarkPage = repository.searchBookmarks(query, pageable);
        return new BookmarksDTO (bookmarkPage);
    }

    public BookmarkDTO createBookmark(CreateBookmarkRequest request) {
        Bookmark bookmark = new Bookmark(null, request.getTitle(), request.getUrl(), Instant.now());
        Bookmark savedBookmark = repository.save(bookmark);
        return bookmarkMapper.toDTO(savedBookmark);

    }
}

// we are returning bookmarks dto for all entries in databsse and returning it 
// right now we have only few properties but if there are more properties and we want to display subset of properties in that case use DTOs 
// it will be heavy computation to load and return 
// solution DTO projection can help you to load desired data. 
