package com.zhiyiyo.runner;

import com.zhiyiyo.constants.SystemConstants;
import com.zhiyiyo.domain.entity.Article;
import com.zhiyiyo.service.ArticleService;
import com.zhiyiyo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RedisCommandLineRunner implements CommandLineRunner {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Override
    public void run(String... args) {
        List<Article> articles = articleService.listViewCount();
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(
                        article -> article.getId().toString(),
                        article -> article.getViewCount().intValue()
                ));
        redisCache.setCacheMap(SystemConstants.REDIS_ARTICLE_VIEW_COUNT_KEY, viewCountMap);
    }
}
