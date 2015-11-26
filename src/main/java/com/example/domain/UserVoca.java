package com.example.domain;


import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "users_word")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVoca {
	@Id
	private Integer id;
	
	@Column(name="username", nullable = false)
	private String username;
	
	@Column(name="word_id", nullable = false)
	private Integer wordId;
	
}
