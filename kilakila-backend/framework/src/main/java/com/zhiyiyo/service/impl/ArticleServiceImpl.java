package com.zhiyiyo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyiyo.constants.SystemConstants;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.entity.ArticleTag;
import com.zhiyiyo.domain.entity.Category;
import com.zhiyiyo.domain.entity.Tag;
import com.zhiyiyo.domain.vo.*;
import com.zhiyiyo.mapper.ArticleMapper;
import com.zhiyiyo.domain.entity.Article;
import com.zhiyiyo.mapper.ArticleTagMapper;
import com.zhiyiyo.mapper.TagMapper;
import com.zhiyiyo.service.ArticleService;
import com.zhiyiyo.service.CategoryService;
import com.zhiyiyo.utils.BeanCopyUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章表(Article)表服务实现类
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Article> listNormalArticle() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        return list(wrapper);
    }

    @Override
    public ResponseResult getHotArticleList() {
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
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
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
    public ResponseResult getArticleDetail(Long id) {
        // 从数据库中查询文章
        Article article = getById(id);
        ArticleDetailsVo articleDetailsVO = BeanCopyUtils.copyBean(article, ArticleDetailsVo.class);

        // 设置分类名称
        Category category = categoryService.getById(article.getCategoryId());
        if (category != null) {
            articleDetailsVO.setCategoryName(category.getName());
        }

        // 设置标签
        LambdaQueryWrapper<ArticleTag> articleTagWrapper = new LambdaQueryWrapper<>();
        articleTagWrapper.eq(ArticleTag::getArticleId, id);
        List<ArticleTag> articleTags = articleTagMapper.selectList(articleTagWrapper);
        List<Long> tagIds = articleTags.stream().map(ArticleTag::getTagId).collect(Collectors.toList());

        if (tagIds.size() > 0) {
            LambdaQueryWrapper<Tag> tagWrapper = new LambdaQueryWrapper<>();
            tagWrapper.in(Tag::getId, tagIds);
            List<Tag> tags = tagMapper.selectList(tagWrapper);
            articleDetailsVO.setTags(BeanCopyUtils.copyBeanList(tags, TagVo.class));
        }

        return ResponseResult.okResult(articleDetailsVO);
    }

    @Override
    public ResponseResult getArticleCount() {
        long article = count();
        long category = categoryService.count();
        long tag = tagMapper.selectCount(null);
        return ResponseResult.okResult(new ArticleCountVo(article, category, tag));
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        Article article = getById(id);
        article.setViewCount(article.getViewCount() + 1);
        updateById(article);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getPreviousNextArticle(Long id) {
        // 查询当前的文章
        Article article = getById(id);
        PreviousNextArticleVo previousNextArticleVo = new PreviousNextArticleVo();

        // 查询上一篇文章
        QueryWrapper<Article> previousWrapper = new QueryWrapper<>();
        previousWrapper.lt("create_time", article.getCreateTime());
        previousWrapper.orderByDesc("create_time").last("limit 1");
        Article previousArticle = getOne(previousWrapper);
        if (previousArticle != null) {
            previousNextArticleVo.setPrevious(BeanCopyUtils.copyBean(previousArticle, HotArticleVo.class));
        }

        // 查询下一篇文章
        QueryWrapper<Article> nextWrapper = new QueryWrapper<>();
        nextWrapper.gt("create_time", article.getCreateTime());
        nextWrapper.orderByAsc("create_time").last("limit 1");
        Article nextArticle = getOne(nextWrapper);
        if (nextArticle != null) {
            previousNextArticleVo.setNext(BeanCopyUtils.copyBean(nextArticle, HotArticleVo.class));
        }

        return ResponseResult.okResult(previousNextArticleVo);
    }
}

