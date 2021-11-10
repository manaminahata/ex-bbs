package com.example.form;

public class ArticleForm {
	// 投稿者名
	private String name;
	// 投稿内容
	private String content;
	
	// getter/setterの定義
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}
	
	
	
}
