package com.zhiyiyo.job;

import com.zhiyiyo.constants.SystemConstants;
import com.zhiyiyo.domain.entity.Article;
import com.zhiyiyo.service.ArticleService;
import com.zhiyiyo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RedisJob {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCache redisCache;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateViewCount() {
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.REDIS_ARTICLE_VIEW_COUNT_KEY);
        List<Article> articles = viewCountMap.entrySet().stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        articleService.updateBatchById(articles);
    }
}
