package com.shop.books.repositories;

import com.shop.books.entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update PublisherEntity p set p.name = ?1 where p.id = ?2")
    int update(final String name, final Integer id);
}
