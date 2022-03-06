package com.zhiyiyo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyiyo.constants.SystemConstants;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.entity.Category;
import com.zhiyiyo.domain.vo.*;
import com.zhiyiyo.mapper.ArticleMapper;
import com.zhiyiyo.domain.entity.Article;
import com.zhiyiyo.mapper.TagMapper;
import com.zhiyiyo.service.ArticleService;
import com.zhiyiyo.service.CategoryService;
import com.zhiyiyo.utils.BeanCopyUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章表(Article)表服务实现类
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public ResponseResult hotArticleList() {
        // 查询出非草稿、没有被删除的文章，并按照热度降序排序前 10 文章
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getViewCount);
        // wrapper.last("limit 10");

        Page<Article> page = new Page<>(1, 5);
        this.page(page, wrapper);

        List<Article> records = page.getRecords();
        return ResponseResult.okResult(BeanCopyUtils.copyBeanList(records, HotArticleVo.class));
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 构造查询条件
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getIsTop);
        wrapper.eq(categoryId != null, Article::getCategoryId, categoryId);

        // 从数据库中分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        this.page(page, wrapper);
        List<Article> articles = page.getRecords();

        // 设置文章分类名
        for (Article article : articles) {
            String categoryName = categoryService.getById(article.getCategoryId()).getName();
            article.setCategoryName(categoryName);
        }

        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        return ResponseResult.okResult(new PageVo<ArticleListVo>(page.getTotal(), articleListVos));
    }

    @Override
    public ResponseResult articleDetail(Long id) {
        // 从数据库中查询文章
        Article article = getById(id);
        ArticleDetailsVo articleDetailsVO = BeanCopyUtils.copyBean(article, ArticleDetailsVo.class);

        // 设置分类名称
        Category category = categoryService.getById(article.getCategoryId());
        if (category != null) {
            articleDetailsVO.setCategoryName(category.getName());
        }

        return ResponseResult.okResult(articleDetailsVO);
    }

    @Override
    public ResponseResult articleCount() {
        long article = count();
        long category = categoryService.count();
        long tag = tagMapper.selectCount(null);
        return ResponseResult.okResult(new ArticleCountVo(article, category, tag));
    }
}

