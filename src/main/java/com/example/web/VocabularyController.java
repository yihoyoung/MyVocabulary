package com.example.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Vocabulary;
import com.example.service.VocabularyService;
import com.example.service.LoginUserDetails;


@Controller
@RequestMapping("voca")
public class VocabularyController {
	@Autowired
	VocabularyService vocabularyService;

	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
	String list(Model model, HttpServletRequest request) {
		String pageS = request.getParameter("page") == null ? "0" : request.getParameter("page");
		String requestPage = request.getParameter("rquestpage") == null ? "0" : request.getParameter("rquestpage");
		String wordType = request.getParameter("wordType") == null ? "" : request.getParameter("wordType");
		int page = Integer.parseInt(pageS);
		int nRequestPage = 0;
		if(StringUtils.isNumeric(requestPage)){
			nRequestPage = Integer.parseInt(requestPage);
		}
		if(nRequestPage > 0){
			page = nRequestPage -1;
		}
	    Pageable pageable = new PageRequest(page, 20);
	    
	    Page<Vocabulary> vocabularies = null;
	    if(StringUtils.isEmpty(wordType)){
			vocabularies = vocabularyService.findAll(pageable);
	    }else{
	    	vocabularies = vocabularyService.findByWordType(wordType, pageable);
	    }
		model.addAttribute("vocabularies", vocabularies);
		model.addAttribute("isLast", vocabularies.isLast());
		model.addAttribute("isFirst", vocabularies.isFirst());
		model.addAttribute("pageNumber", vocabularies.getNumber());
		model.addAttribute("totalPage", vocabularies.getTotalPages());
		
		return "vocabularies/list";
	}

	@ModelAttribute
	VocabularyForm setUpForm() {
		return new VocabularyForm();
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated VocabularyForm form, BindingResult result,
			Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return list(model, null);
		}

		Vocabulary Vocabulary = new Vocabulary();
		BeanUtils.copyProperties(form, Vocabulary);
		vocabularyService.create(Vocabulary);

		return "redirect:/voca";
	}

	@RequestMapping(value = "edit", params = "form", method = RequestMethod.GET)
	String editForm(@RequestParam Integer id, VocabularyForm form) {
		Vocabulary Vocabulary = vocabularyService.findOne(id);
		BeanUtils.copyProperties(Vocabulary, form);
		return "vocabularies/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	String edit(@RequestParam Integer id, @Validated VocabularyForm form,
			BindingResult result, @AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return editForm(id, form);
		}

		Vocabulary Vocabulary = new Vocabulary();
		BeanUtils.copyProperties(form, Vocabulary);
		Vocabulary.setId(id);
		vocabularyService.update(Vocabulary);
		return "redirect:/voca";
	}

	@RequestMapping(value = "edit", params = "goToTop")
	String goToTop() {
		return "redirect:/voca";
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	String edit(@RequestParam Integer id){
		vocabularyService.delete(id);
		return "redirect:/voca";
	}

}
