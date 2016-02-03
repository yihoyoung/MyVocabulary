package com.example.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.example.AppConfig;
import com.example.ShoppingMallApplication;
import com.example.domain.User;
import com.example.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ShoppingMallApplication.class, AppConfig.class })
@WebAppConfiguration
@IntegrationTest("server.port=8888")
@Transactional
public class ControllerTest {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WebApplicationContext wac;
	private User user;
	private MockMvc mock;

	@Before
	public void setUp() throws Exception {
		this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
		user = new User();
		user.setUsername("James3");
	}

	@Test
	public void createUserTest() throws Exception{
		ResultActions resultActions =
                mock.perform(MockMvcRequestBuilders.post("/regist")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("username", user.getUsername())
                        .param("password", "test"));
 
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("username",user.getUsername()));
    }

}
