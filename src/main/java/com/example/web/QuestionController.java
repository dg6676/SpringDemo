package com.example.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;
import com.example.utils.HttpSessionUtils;

@Controller
public class QuestionController {

	//private List<Question> questions =new ArrayList<>();
	@Autowired
	private QuestionRepository questionRepository;
	private static final Logger log=LoggerFactory.getLogger(UserController.class);
	@GetMapping("/")
	public String index(Model model){
		//model.addAttribute("questions",questions);
		log.debug("index");
		model.addAttribute("questions",questionRepository.findAll());
		return "index";
	}
	@PostMapping("/questions")
	public String create(Question question,HttpSession session){
		
		User loginUser=HttpSessionUtils.getUserFromSession(session);
		question.setWriter(loginUser);
		questionRepository.save(question);
		//questions.add(question);
/*		for (Question que : questions) {
			System.out.println("hello"+que);
			//log.debug(""+user1);
			//log.info(""+user1);
		}*/
		return "redirect:/";
	}
	
/*	@GetMapping("/index")
	public String index2(Model model){
		//model.addAttribute("questions",questions);
		model.addAttribute("questions",questionRepository.findAll());
		return "index";
	}*/
	@GetMapping("/qna/form")
	public String quest(Model model){
		//model.addAttribute("questions",questions);
		
		return "/qna/form";
	}
	@GetMapping("/questions/{id}")
	public String showdetail(@PathVariable long id,Model model){
		
		model.addAttribute("question",questionRepository.findOne(id));
		
		return "/qna/show";
	}
	@GetMapping("/questions/{id}/form")
	public String questModify(@PathVariable long id,Model model,HttpSession session){
		
		User loginUser=HttpSessionUtils.getUserFromSession(session);
		Question temp=questionRepository.findOne(id);
		if(temp.getUser().equals(loginUser)){
			model.addAttribute("question",questionRepository.findOne(id));
			return "/qna/form";
		}
		
		
		return "";
	}
	
	
	
	
}
