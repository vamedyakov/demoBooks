package com.shop.books.services.impl;

import com.shop.books.dto.BookTO;
import com.shop.books.entities.AuthorEntity;
import com.shop.books.entities.BookEntity;
import com.shop.books.entities.PublisherEntity;
import com.shop.books.mappers.BookMapper;
import com.shop.books.repositories.AuthorRepository;
import com.shop.books.repositories.BookRepository;
import com.shop.books.repositories.PublisherRepository;
import com.shop.books.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(
            final BookRepository bookRepository,
            final AuthorRepository authorRepository,
            final PublisherRepository publisherRepository,
            final BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookTO save(BookTO book) {
        final BookEntity entity = bookMapper.toEntity(book);
        final BookEntity savedEntity = bookRepository.save(entity);
        return bookMapper.toTransferObject(savedEntity);
    }

    @Override
    public BookTO findById(Integer id) {
        final BookEntity entity = bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return bookMapper.toTransferObject(entity);
    }

    @Override
    public List<BookTO> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).get()
                .map(bookMapper::toTransferObject)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void addAuthor(Integer id, Integer authorId) {
        BookEntity entity = bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        AuthorEntity entityAuthor = authorRepository.findById(authorId)
                .orElseThrow(EntityNotFoundException::new);

        Set<AuthorEntity> bookAuthors = entity.getAuthor();
        bookAuthors.add(entityAuthor);

        entity.setAuthor(bookAuthors);
        bookRepository.save(entity);
    }

    @Override
    public void addPublisher(Integer id, Integer publisherId) {
        BookEntity entity = bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        PublisherEntity entityPublisher = publisherRepository.findById(publisherId)
                .orElseThrow(EntityNotFoundException::new);

        Set<PublisherEntity> bookPublishers = entity.getPublisher();
        bookPublishers.add(entityPublisher);

        entity.setPublisher(bookPublishers);
        bookRepository.save(entity);
    }
}
