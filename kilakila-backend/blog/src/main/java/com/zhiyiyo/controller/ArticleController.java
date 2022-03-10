package com.zhiyiyo.controller;

import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        return articleService.hotArticleList();
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @GetMapping("/{id}")
    public ResponseResult articleDetail(@PathVariable("id") Long id) {
        return articleService.articleDetail(id);
    }

    @GetMapping("/count")
    public ResponseResult articleCount(){
        return articleService.articleCount();
    }

    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable Long id){
        return articleService.updateViewCount(id);
    }
}
