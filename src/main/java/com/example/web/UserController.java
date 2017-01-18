package com.example.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {
	//private static final Logger log=LoggerFactory.getLogger(UserController.class);

	private List<User> users = new ArrayList<>();
	
	@PostMapping("/create")
	public String create(User user){
		//User user = new User(userId,password,name,email);
		users.add(user);
		for (User user1 : users) {
			//log.debug(""+user1);
			//log.info(""+user1);
		}
		
		return "redirect:/user/list";
	}
	@GetMapping("/list") //접근할 url
	public String list(Model model){
		model.addAttribute("users",users);
		
		return "/user/list"; //가져올 html 파일
	}
/*	@GetMapping("/form") //접근할 url
	public String list2(Model model){
		model.addAttribute("users",users);
		
		return "/user/form"; //가져올 html 파일
	}*/
	
}
