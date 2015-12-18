package com.example.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@ToString(exclude = "customers")
public class User {
	@Id
	private String username;
	@JsonIgnore
	private String encodedPassword;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Customer> customers;
	
	@ManyToMany
	@JoinTable(name="users_word", 
				joinColumns={@JoinColumn(name="username", referencedColumnName="username")},
				inverseJoinColumns={@JoinColumn(name="word_id", referencedColumnName="word_id")})
	private List<Vocabulary> vocas;
	
	public void addUserVoca(Vocabulary u){
		vocas.add(u);
	}
	
	public void deleteUserVoca(Vocabulary u){
		for(Vocabulary voca : vocas){
			if(voca == u){
				vocas.remove(voca);
				return;
			}
		}
	}
}
