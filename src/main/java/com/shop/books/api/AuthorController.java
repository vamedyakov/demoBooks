package com.shop.books.api;

import com.shop.books.dto.AuthorTO;
import com.shop.books.services.AuthorService;
import com.shop.books.services.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(AuthorController.PATH)
public class AuthorController {
    final static String PATH = "author";

    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(final AuthorService authorService, final BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<AuthorTO>> getAll(@RequestParam("page") int page,
                                                 @RequestParam("size") int size) {
        return new ResponseEntity<>(authorService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuthorTO> save(@Valid @RequestBody final AuthorTO author) {
        System.out.println("saving author: " + author);
        return new ResponseEntity<>(authorService.save(author), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorTO> getById(@NotNull @PathVariable("id") Integer id) {
        return new ResponseEntity<>(authorService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@NotNull @PathVariable("id") Integer id,
                                       @Valid @RequestBody final AuthorTO author) {
        System.out.println("updating author: " + author);
        authorService.update(author, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NotNull @PathVariable("id") Integer id) {
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/book/{bookId}")
    public ResponseEntity<Void> addBook(
            @PathVariable Integer id,
            @PathVariable Integer bookId
    ) {
        bookService.addAuthor(bookId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
