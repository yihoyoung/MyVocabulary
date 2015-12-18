package com.example.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.service.LoginUserDetailsService;

@Controller
public class LoginController {
	@Autowired
	LoginUserDetailsService userService;
	
	@RequestMapping("loginForm")
	String loginForm(){
		return "loginForm";
	}
	
	

	@RequestMapping("menu")
	String menuForm(){
		return "menu";
	}
	

	@RequestMapping("registForm")
	String registForm(){
		return "registForm";
	}
	

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	ModelAndView regist(Model model, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		String userName = StringUtils.isEmpty(request.getParameter("username")) ? "" : request.getParameter("username");
		String password = StringUtils.isEmpty(request.getParameter("password")) ? "" : request.getParameter("password");
		String result = userService.save(userName, password);
		if(StringUtils.isEmpty(result)){
			view.setViewName("registForm");
			view.addObject("isRegisted", false);
		}else{
			view.setViewName("registed");
		}
		return view;
	}
}
