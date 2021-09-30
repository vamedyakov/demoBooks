package com.shop.books.services;

import com.shop.books.dto.BookTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookTO save(final BookTO book);

    BookTO findById(final Integer id);

    List<BookTO> findAll(final Pageable pageable);

    void delete(final Integer id);

    void addAuthor(final Integer id, final Integer authorId);

    void addPublisher(final Integer id, final Integer publisherId);
}
