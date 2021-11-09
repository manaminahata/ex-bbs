package com.example.domain;

/**
 * コメント者のコメント内容について使用するドメインの作成
 * @author manami
 *
 */
public class Comment {
	// id
	private Integer id;
	// コメント者名
	private String name;
	// コメント内容
	private String content;
	// 書き込みに対する番号
	private Integer articleId;
	
	// getter/setterの定義
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}
	
	
	
}
