package com.zhiyiyo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.entity.Tag;

public interface TagService extends IService<Tag> {
    ResponseResult getTagCountList();
    Tag getOrAddTagByName(String name);
}
