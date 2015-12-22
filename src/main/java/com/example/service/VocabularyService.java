package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.domain.ViewOfVoca;
import com.example.domain.Vocabulary;
import com.example.repository.UserRepository;
import com.example.repository.VocabularyRepository;

@Service
@Transactional
public class VocabularyService {
	@Autowired
	VocabularyRepository vocabularyRepository;

	@Autowired
	UserRepository userRepository;

	public Page<Vocabulary> findAll(Pageable pageable) {
		return vocabularyRepository.findAll(pageable);
	}

	public Vocabulary findOne(Integer id) {
		return (Vocabulary) vocabularyRepository.findOne(id);
	}

	public Vocabulary create(Vocabulary vocabulary) {
		return (Vocabulary) vocabularyRepository.save(vocabulary);
	}

	public Vocabulary update(Vocabulary vocabulary) {
		return (Vocabulary) vocabularyRepository.save(vocabulary);
	}

	public void delete(Integer id) {
		vocabularyRepository.delete(id);
	}

	public Page<ViewOfVoca> findAll(String userName, Pageable pageable) {
		User user = userRepository.findOne(userName);
		List<Vocabulary> myVocas = user.getVocas();

		Page<Vocabulary> searchedVoca = vocabularyRepository.findAll(pageable);

		List<ViewOfVoca> allVoca2 = new ArrayList<ViewOfVoca>();
		for (Vocabulary voca : searchedVoca.getContent()) {
			ViewOfVoca myVoca = new ViewOfVoca();
			myVoca.setDescription(voca.getDescription());
			myVoca.setExample(voca.getExample());
			myVoca.setId(voca.getId());
			myVoca.setPhonetic(voca.getPhonetic());
			myVoca.setWordEn(voca.getWordEn());
			myVoca.setWordKr(voca.getWordKr());
			myVoca.setWordType(voca.getWordType());
			if (myVocas.contains(voca)) {
				myVoca.setMyword(true);
			} else {
				myVoca.setMyword(false);
			}
			allVoca2.add(myVoca);
		}

		PageImpl<ViewOfVoca> pVoca = new PageImpl<ViewOfVoca>(allVoca2, pageable, allVoca2.size());

		return pVoca;
	}

	public Page<ViewOfVoca> findByWordType(String wordType, String userName, Pageable pageable) {
		User user = userRepository.findOne(userName);
		List<Vocabulary> myVocas = user.getVocas();
		List<Vocabulary> allVoca = vocabularyRepository.findVocabularyByWordType(wordType);

		List<ViewOfVoca> allVoca2 = new ArrayList<ViewOfVoca>();
		int page = 0;
		int count = 0;
		for (Vocabulary voca : allVoca) {
			if (page > pageable.getPageNumber()) {
				break;
			}
			if (count == pageable.getPageSize()) {
				page++;
				count = 0;
			}
			if (page == pageable.getPageNumber()) {
				ViewOfVoca myVoca = new ViewOfVoca();
				myVoca.setDescription(voca.getDescription());
				myVoca.setExample(voca.getExample());
				myVoca.setId(voca.getId());
				myVoca.setPhonetic(voca.getPhonetic());
				myVoca.setWordEn(voca.getWordEn());
				myVoca.setWordKr(voca.getWordKr());
				myVoca.setWordType(voca.getWordType());
				if (myVocas.contains(voca)) {
					myVoca.setMyword(true);
				} else {
					myVoca.setMyword(false);
				}
				allVoca2.add(myVoca);
			}
			count++;
		}

		PageImpl<ViewOfVoca> pVoca = new PageImpl<ViewOfVoca>(allVoca2, pageable, allVoca.size());

		return pVoca;
	}
}
