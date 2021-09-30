package com.shop.books.mappers;

import com.shop.books.dto.BookTO;
import com.shop.books.entities.BookEntity;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    BookEntity toEntity(final BookTO book);

    BookTO toTransferObject(final BookEntity book);
}
