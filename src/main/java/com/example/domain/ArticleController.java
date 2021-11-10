package com.example.domain;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentForm setUpComment() {
		return new CommentForm();
	}
	
	/**
	 * 掲示板をブラウザに表示させる
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		
		// ArticleのcommentListにコメントを格納する
		for (Article article : articleList) {
			article.setCommentList(commentRepository.findByArticleId(article.getId()));
		}
		
		model.addAttribute("articleList", articleList);
		return "chat";
	}
	
	/**
	 * 記事を投稿するメソッドを定義
	 * 投稿後元の画面に戻る
	 * @param form
	 * @return redirect:/article
	 */
	@RequestMapping("/insert")
	public String insertArticle(ArticleForm form) {
		// 新たに投稿を追加したいのでインスタンス化する
		Article article = new Article();
		
		// フォームのデータのままでは使えないのでドメインにコピーする
		BeanUtils.copyProperties(form, article);
		
		// リポジトリのinsertメソッドを呼び出す
		articleRepository.insert(article);
		
		return "redirect:/article";
	}
	
	/**
	 * コメントを投稿するメソッドを定義
	 * 投稿後元の画面に戻る
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/comment")
	public String insertComment(CommentForm form, Model model) {
		
		System.out.println(form);
		// 新たにコメントを追加したいのでインスタンス化する
		Comment comment = new Comment();
		
		// フォームのデータをドメインにコピーする
		BeanUtils.copyProperties(form, comment);
		
		comment.setArticleId(form.getIntAtricleId());
		
		commentRepository.insert(comment);
		
		return "redirect:/article";
	}
	
	/**
	 * idを引数に該当の記事ごと削除する
	 * @param articleId
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteById(Integer id) {
		
		articleRepository.deleteById(id);
		commentRepository.deleteByArticleId(id);
		
		return "redirect:";
	}
	
}
