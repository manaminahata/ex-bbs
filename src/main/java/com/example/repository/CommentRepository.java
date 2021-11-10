package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

@Repository
public class CommentRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};
	
	/**
	 * article_idを引数にとり、該当のデータを検索する
	 * @param articleId
	 * @return
	 */
	public List<Comment> findByArticleId(int articleId) {
		String findByArticleIdSql = "SELECT * FROM comments WHERE article_id=:articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(findByArticleIdSql, param, COMMENT_ROW_MAPPER);
		return commentList;
	}
	
	/**
	 * commentにレコードの追加
	 * @param commnet
	 */
	public void insert(Comment comment) {
		String insertSql = "INSERT INTO comments(name, content, article_id) VALUES (:name, :content, :articleId)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		System.out.println(comment);
		
		template.update(insertSql, param);
	}
	
	/**
	 * レコードの削除
	 * @param articleId
	 */
	public void deleteByArticleId(int articleId) {
		String deleteByArticleIdSql = "DELETE FROM comments WHERE article_id=:articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(deleteByArticleIdSql, param);
	}
	
}
