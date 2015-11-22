package com.example;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenPassword {
	
	@Test
	public void testBCrypt(){
		System.out.println(new BCryptPasswordEncoder().encode("demo"));
	}
}
