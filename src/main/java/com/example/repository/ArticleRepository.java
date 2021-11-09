package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

@Repository
public class ArticleRepository {
	// DBとの接続を行うため定義する
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 他の人からのコメントはarticlesのidとcommentsのarticle＿idを結合して表示させるように設定する
	 */
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
	};
	
	/**
	 * 投稿者の投稿を全件検索する
	 * @return
	 */
	public List<Article> findAll() {
		String findAllSql = "SELECT * FROM articles";
		List<Article> articleList = template.query(findAllSql, ARTICLE_ROW_MAPPER);
		return articleList;
	}
	
	/**
	 * レコードを追加する（投稿者のデータを追加する）
	 * @param article
	 */
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		String insertSql = "INSERT INTO article(name, content) VALUES :name, :content";
		template.update(insertSql, param);
	}
	
	/**
	 * 該当idのレコードを削除する
	 * @param id
	 */
	public void deleteById(int id) {
		String deleteByIdSql = "DELETE FROM articles WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteByIdSql, param);
	}
}
