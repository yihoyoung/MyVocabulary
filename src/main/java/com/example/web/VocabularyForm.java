package com.example.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class VocabularyForm {
	@NotNull
	@Size(min = 1, max = 50)
	private String wordEn;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String wordKr;
}
