package com.zhiyiyo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.entity.Article;

/**
 * 文章表(Article)表服务接口
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult articleDetail(Long id);
}

