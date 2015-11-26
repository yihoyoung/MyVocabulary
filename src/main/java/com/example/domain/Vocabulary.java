package com.example.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "vocabulary")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vocabulary {
	@Id
	@Column(name="word_id")
	@GeneratedValue
	private Integer id;
	
	@Column(name="word_type", nullable = false)
	private String wordType;
	
	@Column(name="word_en", nullable = false)
	private String wordEn;
	
	@Column(name="word_kr", nullable = false)
	private String wordKr;
	
	@Column(name="phonetic")
	private String phonetic;
	
	@Column(name="description")
	private String description;
	
	@Column(name="example")
	private String example;
}
