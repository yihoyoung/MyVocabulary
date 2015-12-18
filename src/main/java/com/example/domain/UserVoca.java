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
	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id")
	private Vocabulary voca;
	
	
	public static UserVoca saveUserVoca(User user, Vocabulary voca){
		UserVoca userVoca = new UserVoca();
		userVoca.setUser(user);
		userVoca.setVoca(voca);
		return userVoca;
	}
}
