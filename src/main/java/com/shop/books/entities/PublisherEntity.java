package com.shop.books.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publisher")
@Getter
@Setter
public class PublisherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "publisher")
    private Set<BookEntity> book = new HashSet<>();

    @Override
    public String toString() {
        return "publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
