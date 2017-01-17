package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	@GetMapping("/hello")
	
	public String hello(String name,Integer age,Model model){
		//System.out.println(name);//?key=value
		model.addAttribute("name", name);//view로 값전달
		model.addAttribute("age",age);
		
		return "hello"; //template folder의 hello.html을 의미함. 컨벤션이 알아서 인식
	}
}
