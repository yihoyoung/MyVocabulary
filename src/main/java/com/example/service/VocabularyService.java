package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.domain.Vocabulary;
import com.example.repository.VocabularyRepository;

@Service
@Transactional
public class VocabularyService {
	@Autowired
	VocabularyRepository vocabularyRepository;
	
	public Page<Vocabulary> findAll(Pageable pageable){
		return vocabularyRepository.findAll(pageable);
	}
	
	public Vocabulary findOne(Integer id){
		return (Vocabulary) vocabularyRepository.findOne(id);
	}
	
	public Vocabulary create(Vocabulary vocabulary){
		return (Vocabulary) vocabularyRepository.save(vocabulary);
	}
	
	public Vocabulary update(Vocabulary vocabulary){
		return (Vocabulary) vocabularyRepository.save(vocabulary);
	}
	
	public void delete(Integer id){
		vocabularyRepository.delete(id);
	}

	public List<Vocabulary> findAll() {
		return vocabularyRepository.findAll();
	}
	
	public Page<Vocabulary> findByWordType(String wordType, Pageable pageable){
		return (Page<Vocabulary>) vocabularyRepository.findVocabularyByWordType(wordType, pageable);
	}
}
