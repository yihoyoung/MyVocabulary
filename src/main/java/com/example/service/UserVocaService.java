package com.example.service;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.domain.UserVoca;
import com.example.domain.Vocabulary;
import com.example.repository.UserVocabularyRepository;

import ch.qos.logback.classic.Logger;

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
		int start = pageable.getPageNumber() * pageable.getPageSize();
		int end = start + pageable.getPageSize() - 1;
		Page<Vocabulary> myVoca = new Page<Vocabulary>(){
			
			@Override
			public int getNumber() {
				return pageable.getPageNumber();
			}

			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getNumberOfElements() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public List<Vocabulary> getContent() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean hasContent() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Sort getSort() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isFirst() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isLast() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasPrevious() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Pageable nextPageable() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Pageable previousPageable() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Iterator<Vocabulary> iterator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getTotalPages() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getTotalElements() {
				// TODO Auto-generated method stub
				return 0;
			}
			
		};
		return myVoca;
	}	
	
}
