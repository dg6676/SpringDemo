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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.domain.UserRepository;
import com.example.utils.HttpSessionUtils;

@Controller
@RequestMapping("/users")
public class UserController {
	//private static final Logger log=LoggerFactory.getLogger(UserController.class);

	//private List<User> users = new ArrayList<>();
	
	@Autowired //userRepository에 자동으로 할당(구현체를 만들어주고 인스턴스를 할당해줌)
	private UserRepository userRepository; 
	
	
	@GetMapping("/list") //접근할 url
	public String list(Model model){
		//System.out.println("fffffffffffff");
		//model.addAttribute("users",users);
		model.addAttribute("users",userRepository.findAll());
		
		return "user/list"; //가져올 html 파일  //template 경로의 파일을 지정시,폴더 앞 슬래쉬를 제거해줘야한다. 
	}
	@GetMapping("/new") //접근할 url  
	public String userform(Model model){
		//model.addAttribute("users",users);	
		return "/user/form"; //가져올 html 파일
	}
	@PostMapping("/checklogin")
	public String login(String userId,String password,HttpSession session){
		User user=userRepository.findByUserId(userId);//db에서 userId탐색 
/*	     if(user==null||!password.equals(user.getPassword())) //로그인 실패 "redirect:/user/login.html"
	    	 {
	    	 System.out.println(password);
	    	 return "redirect:/user/login.html";
	    	 }*/  //안좋은 코드. 객체지향적이지 못함
		
		if(user==null||!user.matchPassword(password))//로그인 실패
			return "redirect:/users/login";
		
		 session.setAttribute("loginUser", user);
	     return "redirect:/";
	}
	
	@PostMapping("/")
	public String create(User user){
		userRepository.save(user);
		//User user = new User(userId,password,name,email);
		//users.add(user);
	/*	for (User user1 : users) {
			//log.debug(""+user1);
			//log.info(""+user1);
		}*/
		return "redirect:/users";
	}

	
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable long id,Model model,HttpSession session){
		
		/*Object temp=session.getAttribute("loginUser");
		if(temp==null){
			throw new IllegalStateException("로그인하지 않은 사용자");
		}		
		User loginUser=(User)temp;
		if(!loginUser.matchId(id)){
			throw new IllegalStateException("다른 사용자 정보는 수정할 수 없습니다");
		}*/
		checkOwner(id, session);  //밑 메소드에서 중복되는 부분을 메소드로 자동으로 만듬. Reflactor->expose Method
		model.addAttribute("user",userRepository.findOne(id));
		
		return "/user/updateForm";
	}

	private void checkOwner(long id, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)){
			throw new IllegalStateException("로그인하지 않은 사용자");
		}
		User loginUser=HttpSessionUtils.getUserFromSession(session);
		if(!loginUser.matchId(id)){
			throw new IllegalStateException("다른 사용자 정보는 수정할 수 없습니다");
		}
	}
	@PutMapping("/{id}/update")
	public String updateUser(@PathVariable long id,User user,HttpSession session){
/*		Object temp=session.getAttribute("loginUser");
		
		if(temp==null){
			throw new IllegalStateException("로그인하지 않은 사용자");
		}		
		User loginUser=(User)temp;
		if(!loginUser.matchId(id)){
			throw new IllegalStateException("다른 사용자 정보는 수정할 수 없습니다");
		}*/
		checkOwner(id, session);  
		User dbuser=userRepository.findOne(id);
		dbuser.updateData(user);
		
		/*if(dbuser.updateData(user)){
			userRepository.save(dbuser); //굳이 이걸 안넣어도 저장이된다.
			//userRepository.save(user);//user에는 id가 없기때문에 오류가 나거나 insert 쿼리를 날림.
			//디비의 모든 데이터를 전송하고 저장하기때문에 리소스가 낭비됨
			//또한 가입시 작성한 데이터 갯수와 수정 데이터 갯수가 다를수있기때문에 데이터 손실가능성있음.
			//따라서 객체로 데이터를 디비에서 찾은다음 그 객체에 데이터를 업뎃하고 저장하는 방식이 좋다.
			
		}*/
			
		return "redirect:/user/list";
	}
	@GetMapping("/{id}")
	public String showdetail(){
		return "";
	}
	@GetMapping("/login")
	public String loginform(){
		
		return "user/login";
	}
}
