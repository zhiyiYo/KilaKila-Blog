package com.zhiyiyo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.dto.ArticleDTO;
import com.zhiyiyo.domain.dto.ArticleQueryDTO;
import com.zhiyiyo.domain.entity.Article;

import java.text.ParseException;
import java.util.List;

/**
 * 文章表(Article)表服务接口
 */
public interface ArticleService extends IService<Article> {
    List<Article> listNormalArticle();
    Long getNormalArticleCount();

    ResponseResult getHotArticleList();

    ResponseResult getArticleList(ArticleQueryDTO articleQueryDTO);

    ResponseResult getArticleDetail(Long id);

    ResponseResult getArticleCount();

    List<Article> listViewCount();

    ResponseResult updateViewCount(Long id);

    ResponseResult getPreviousNextArticle(Long id);

    ResponseResult addArticle(ArticleDTO article);

    ResponseResult editArticle(ArticleDTO article);
}

