package com.shop.books.services.impl;

import com.shop.books.dto.AuthorTO;
import com.shop.books.entities.AuthorEntity;
import com.shop.books.mappers.AuthorMapper;
import com.shop.books.repositories.AuthorRepository;
import com.shop.books.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(
            final AuthorRepository authorRepository,
            final AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorTO save(AuthorTO author) {
        final AuthorEntity entity = authorMapper.toEntity(author);
        final AuthorEntity savedEntity = authorRepository.save(entity);
        return authorMapper.toTransferObject(savedEntity);
    }

    @Override
    public AuthorTO findById(Integer id) {
        final AuthorEntity entity = authorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return authorMapper.toTransferObject(entity);
    }

    @Override
    public List<AuthorTO> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable).get()
                .map(authorMapper::toTransferObject)
                .collect(Collectors.toList());
    }

    @Override
    public void update(AuthorTO author, Integer id) {
        authorRepository.update(author.getName(), id);
    }

    @Override
    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }
}
