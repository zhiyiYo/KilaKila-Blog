package com.zhiyiyo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryCountList();
    Category getOrAddCategoryByName(String name);
}

