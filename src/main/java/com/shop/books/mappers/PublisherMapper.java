package com.shop.books.mappers;

import com.shop.books.dto.PublisherTO;
import com.shop.books.entities.PublisherEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PublisherMapper {
    PublisherEntity toEntity(final PublisherTO dto);

    PublisherTO toTransferObject(final PublisherEntity entity);
}
