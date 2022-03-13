package com.zhiyiyo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.dto.ArticleDTO;
import com.zhiyiyo.domain.entity.Article;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 */
public interface ArticleService extends IService<Article> {
    List<Article> listNormalArticle();

    ResponseResult getHotArticleList();

    ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult getArticleCount();

    ResponseResult updateViewCount(Long id);

    ResponseResult getPreviousNextArticle(Long id);

    ResponseResult addArticle(ArticleDTO article);

    ResponseResult editArticle(ArticleDTO article);
}

