package com.zhiyiyo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyiyo.constants.SystemConstants;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.entity.Article;
import com.zhiyiyo.domain.entity.Category;
import com.zhiyiyo.domain.vo.CategoryCountVo;
import com.zhiyiyo.mapper.CategoryMapper;
import com.zhiyiyo.service.ArticleService;
import com.zhiyiyo.service.CategoryService;
import com.zhiyiyo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-02-27 15:05:37
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryCountList() {
        // 从数据库中查询非草稿的文章的目录 id
        List<Article> articles = articleService.listNormalArticle();
        Set<Long> categoryIds = articles.stream().map(Article::getCategoryId).collect(Collectors.toSet());

        // 从数据库中查询目录
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Category::getId, categoryIds);
        queryWrapper.eq(Category::getStatus, SystemConstants.Category_STATUS_NORMAL);
        List<Category> categories = list(queryWrapper);
        List<CategoryCountVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryCountVo.class);

        // 统计每种分类的数量
        Map<Long, Integer> categoryIdCountMap = new HashMap<>();
        for (Article article : articles) {
            Long categoryId = article.getCategoryId();
            Integer count = categoryIdCountMap.get(categoryId);
            categoryIdCountMap.put(categoryId, count == null ? 1 : count + 1);
        }

        for (CategoryCountVo categoryVo : categoryVos) {
            categoryVo.setCount(categoryIdCountMap.get(categoryVo.getId()));
        }

        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public Category getOrAddCategoryByName(String name) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getName, name);
        Category category = getOne(wrapper);

        if (category == null) {
            category = new Category();
            category.setName(name);
            save(category);
        }

        return category;
    }
}

