package com.example.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.AppConfig;
import com.example.ShoppingMallApplication;
import com.example.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ShoppingMallApplication.class, AppConfig.class})
@WebAppConfiguration
@Transactional
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	private User user;
	
	@Before
	public void setUp() throws Exception{
		user = new User();
		user.setUsername("James2");
	}
	
	@Test
	public void testCreateUser() throws Exception{
		User createUser = userRepository.save(user);
		Assert.assertEquals(createUser, user);
	}
	
}
