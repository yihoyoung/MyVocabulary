package com.example.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Vocabulary;

public interface VocabularyRepository<T, ID extends Serializable> extends JpaRepository<Vocabulary, Integer> {

	@Query("SELECT x FROM Vocabulary x where x.wordType = ?1")
	List<Vocabulary> findVocabularyByWordType(String wordType);
	

	@Query("SELECT x FROM Vocabulary x where x.wordType = ?1")
	Page<Vocabulary> findVocabularyByWordType(String wordType, Pageable pageable);
}
