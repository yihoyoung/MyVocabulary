package com.example.web;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Message;
import com.example.domain.RequestMyWord;
import com.example.domain.Vocabulary;
import com.example.service.VocabularyService;
import com.example.service.UserVocaService;


@Controller
@RequestMapping("uservoca")
public class UserVocabularyController {
	
	private static final Log logger = LogFactory.getLog(UserVocabularyController.class);
	@Autowired
	VocabularyService vocabularyService;
	@Autowired
	UserVocaService userVocaService;


	@RequestMapping(value = "create", method = RequestMethod.POST)
	public @ResponseBody Message create(@RequestBody RequestMyWord myword) {
		Message message = new Message();
		int wordId = myword.getId();
		String username = myword.getUsername();
		logger.info("word_id" + wordId);
		logger.info("username" + username);
		
		Vocabulary userVoca = userVocaService.findByWordId(username, wordId);
		if(userVoca == null){
			userVocaService.create(username, wordId);
			message.setMessage("단어장에 추가 되었습니다.");
		}else{
			userVocaService.delete(username,userVoca);
			message.setMessage("단어장에서 삭제 되었습니다.");
		}
		return message;
	}

}
