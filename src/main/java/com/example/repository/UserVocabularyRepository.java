package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.UserVoca;

public interface UserVocabularyRepository extends JpaRepository<UserVoca, Integer> {

}
