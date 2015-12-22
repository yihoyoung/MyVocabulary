package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.domain.UserVoca;
import com.example.domain.Vocabulary;
import com.example.repository.UserVocabularyRepository;

import com.example.repository.UserRepository;

@Service
@Transactional
public class UserVocaService {
	@Autowired
	UserVocabularyRepository userVocabularyRepository;
	
	@Autowired
	VocabularyService vocabularyService;
	
	@Autowired
	UserRepository UserRepository;
	
	public Page<UserVoca> findAll(User user, Pageable pageable){
		return (Page<UserVoca>) userVocabularyRepository.findAll(pageable);
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
	public void delete(String userName, Vocabulary userVoca){
		User user = UserRepository.findOne(userName);
		user.deleteUserVoca(userVoca);
	}

	public List<UserVoca> findAll() {
		return userVocabularyRepository.findAll();
	}
	
	public Vocabulary create(String userName, Integer wordId){
		User user = UserRepository.findOne(userName);
		Vocabulary Vocabulary = vocabularyService.findOne(wordId);
		user.addUserVoca(Vocabulary);
		return Vocabulary;
	}
	

	
	public Vocabulary findByWordId(String userName, Integer wordId){
		User user = UserRepository.findOne(userName);
		Vocabulary vocabulary = vocabularyService.findOne(wordId);
		
		List<Vocabulary> list = user.getVocas();
		if(list.contains(vocabulary)){
			return vocabulary;
		}else{
			return null;
		}
	}
	

	
	public Page<Vocabulary> findByUser(String userName, Pageable pageable){
		User user = UserRepository.findOne(userName);
		List<Vocabulary> allVoca = user.getVocas();
		
		PageImpl<Vocabulary> pVoca = new PageImpl<Vocabulary>(allVoca, pageable, allVoca.size());
		return pVoca;
	}	
	
}
