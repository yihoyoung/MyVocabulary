package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.User;
import com.example.domain.UserVoca;
import com.example.domain.Vocabulary;

public interface UserVocabularyRepository extends JpaRepository<UserVoca, Integer> {
	@Query("SELECT x FROM UserVoca x where x.user = :user and x.voca = :voca")
	UserVoca findVocabulary(User user, Vocabulary voca);
	
	
	@Query("SELECT x FROM UserVoca x where x.user = :user")
	Page<Vocabulary> findUserVocabularyByUser(User user, Pageable pageable);
}
