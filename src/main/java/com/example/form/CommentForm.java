package com.example.form;

public class CommentForm {
	// 記事を区別するために付けられたid
	private String articleId;
	// コメント者
	private String name;
	// コメント内容
	private String content;
	
	public Integer getIntAtricleId() {
		return Integer.parseInt(articleId);
	}
	
	// getter/setterの定義
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
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
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}
	
	
}
