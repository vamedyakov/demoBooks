package com.shop.books.mappers;

import com.shop.books.dto.AuthorTO;
import com.shop.books.entities.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {
    AuthorEntity toEntity(final AuthorTO book);

    AuthorTO toTransferObject(final AuthorEntity book);
}
