package com.bookstore.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book , Integer> {

}
