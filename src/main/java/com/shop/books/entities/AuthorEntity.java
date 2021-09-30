package com.shop.books.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "author")
    private Set<BookEntity> book = new HashSet<>();

    @Override
    public String toString() {
        return "author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
