package com.shop.books.api;

import com.shop.books.dto.BookTO;
import com.shop.books.services.AuthorService;
import com.shop.books.services.BookService;
import com.shop.books.services.PublisherService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(BookController.PATH)
public class BookController {
    final static String PATH = "books";

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<BookTO>> getAll(@RequestParam("page") int page,
                                               @RequestParam("size") int size) {
        return new ResponseEntity<>(bookService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookTO> save(@Valid @RequestBody final BookTO book) {
        System.out.println("saving book: " + book);
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookTO> getById(@NotNull @PathVariable("id") Integer id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@NotNull @PathVariable("id") Integer id,
                                       @Valid @RequestBody final BookTO book) {
        System.out.println("updating book: " + book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NotNull @PathVariable("id") Integer id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/author/{authorId}")
    public ResponseEntity<Void> addAuthor(
            @PathVariable Integer id,
            @PathVariable Integer authorId
    ) {
        bookService.addAuthor(id, authorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/publisher/{publisherId}")
    public ResponseEntity<Void> addPublisher(
            @PathVariable Integer id,
            @PathVariable Integer publisherId
    ) {
        bookService.addPublisher(id, publisherId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
