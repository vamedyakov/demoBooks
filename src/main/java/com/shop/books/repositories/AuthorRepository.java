package com.shop.books.repositories;

import com.shop.books.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update AuthorEntity p set p.name = ?1 where p.id = ?2")
    int update(final String name, final Integer id);

}