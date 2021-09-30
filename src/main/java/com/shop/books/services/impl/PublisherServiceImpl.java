package com.shop.books.services.impl;

import com.shop.books.dto.PublisherTO;
import com.shop.books.entities.PublisherEntity;
import com.shop.books.mappers.PublisherMapper;
import com.shop.books.repositories.PublisherRepository;
import com.shop.books.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Autowired
    public PublisherServiceImpl(
            final PublisherRepository publisherRepository,
            final PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @Override
    public PublisherTO save(PublisherTO publisher) {
        final PublisherEntity entity = publisherMapper.toEntity(publisher);
        final PublisherEntity savedEntity = publisherRepository.save(entity);
        return publisherMapper.toTransferObject(savedEntity);
    }

    @Override
    public PublisherTO findById(Integer id) {
        final PublisherEntity entity = publisherRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return publisherMapper.toTransferObject(entity);
    }

    @Override
    public List<PublisherTO> findAll(Pageable pageable) {
        return publisherRepository.findAll(pageable).get()
                .map(publisherMapper::toTransferObject)
                .collect(Collectors.toList());
    }

    @Override
    public void update(PublisherTO publisher, Integer id) {
        publisherRepository.update(publisher.getName(), id);
    }

    @Override
    public void delete(Integer id) {
        publisherRepository.deleteById(id);
    }
}
