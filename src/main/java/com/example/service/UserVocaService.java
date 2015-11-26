package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.UserVoca;
import com.example.repository.UserVocabularyRepository;

@Service
@Transactional
public class UserVocaService {
	@Autowired
	UserVocabularyRepository userVocabularyRepository;
	
	public Page<UserVoca> findAll(Pageable pageable){
		return userVocabularyRepository.findAll(pageable);
	}
	
	public UserVoca findOne(Integer id){
		return userVocabularyRepository.findOne(id);
	}
	
	public UserVoca create(UserVoca userVoca){
		return userVocabularyRepository.save(userVoca);
	}
	
	public UserVoca update(UserVoca userVoca){
		return userVocabularyRepository.save(userVoca);
	}
	
	public void delete(Integer id){
		userVocabularyRepository.delete(id);
	}

	public List<UserVoca> findAll() {
		return userVocabularyRepository.findAll();
	}
}
