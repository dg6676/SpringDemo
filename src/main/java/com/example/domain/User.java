package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	//자바 빈
	@Id @GeneratedValue //자동으로 값증가하는 역할.데이터베이스 필드로 인식
	private long id;//uniq id를 줌. 
	
	@Column(length=15,nullable=false,unique=true) //테이블의 각 필드를 세부적으로 설정함.
	private String userId;//userId도 변경가능성이 있다.
						  //카멜컨벤션(대문자가 끼어있는경우) -> 대문자 기준으로 테이블에서 _을 추가함.user_id
	@Column(length=20,nullable=false,unique=true)
	private String password;
	
	@Column(length=15,nullable=false,unique=true)
	private String name;
	
	@Column(length=15,nullable=false,unique=true)
	private String email;
	
	public User(){
		
	}
	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	
	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getPassword() {
		return password;
	}
	
	public boolean matchPassword(String password){
		if(this.password.equals(password))
		return true;
		else
		return false;
	}
	public boolean updateData(User user){
		if(user.matchPassword(this.password)){
			this.name=user.name;
			this.email=user.email;
	//인스턴스가 다르고 클래스가 같으면 private 필드도 접근가능.		
			return true;
		}
			
		return false;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + "]";
	}
	public boolean matchId(long id2) {
		// TODO Auto-generated method stub
		return this.id==id;
	}
	
	

}
