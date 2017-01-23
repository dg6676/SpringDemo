package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {

	@Id @GeneratedValue
	private long id;
	
	@Column(length=15,nullable=false,unique=true)
	private String writer;
	private String title;
	private String contents;
	
	
	public Question(){}

	public Question(String writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Question [writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}
	
	
	
}
