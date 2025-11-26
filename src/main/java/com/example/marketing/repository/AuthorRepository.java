package com.example.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.marketing.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
