package com.shop.books.services;

import com.shop.books.dto.PublisherTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublisherService {
    PublisherTO save(final PublisherTO publisher);

    PublisherTO findById(final Integer id);

    List<PublisherTO> findAll(final Pageable pageable);

    void update(final PublisherTO publisher, Integer id);

    void delete(final Integer id);
}
