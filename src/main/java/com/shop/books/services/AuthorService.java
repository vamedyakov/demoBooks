package com.shop.books.services;

import com.shop.books.dto.AuthorTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {
    AuthorTO save(final AuthorTO author);

    AuthorTO findById(final Integer id);

    List<AuthorTO> findAll(final Pageable pageable);

    void update(final AuthorTO publisher, Integer id);

    void delete(final Integer id);
}
