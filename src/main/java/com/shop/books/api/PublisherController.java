package com.shop.books.api;

import com.shop.books.dto.PublisherTO;
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
@RequestMapping(PublisherController.PATH)
public class PublisherController {
    final static String PATH = "publisher";

    private final PublisherService publisherService;
    private final BookService bookService;

    public PublisherController(final PublisherService publisherService, final BookService bookService) {
        this.publisherService = publisherService;
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<PublisherTO>> getAll(@RequestParam("page") int page,
                                                    @RequestParam("size") int size) {
        return new ResponseEntity<>(publisherService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublisherTO> save(@Valid @RequestBody PublisherTO publisher) {
        System.out.println("saving  publisher: " + publisher);
        return new ResponseEntity<>(publisherService.save(publisher), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherTO> getById(@NotNull @PathVariable("id") Integer id) {
        return new ResponseEntity<>(publisherService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@NotNull @PathVariable("id") Integer id,
                                       @Valid @RequestBody PublisherTO publisher) {
        System.out.println("updating publisher: " + publisher);
        publisherService.update(publisher, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NotNull @PathVariable("id") Integer id) {
        publisherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/book/{bookId}")
    public ResponseEntity<Void> addBook(
            @PathVariable Integer id,
            @PathVariable Integer bookId
    ) {
        bookService.addPublisher(bookId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
