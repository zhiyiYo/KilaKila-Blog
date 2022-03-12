package com.zhiyiyo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyiyo.domain.entity.ArticleTag;
import com.zhiyiyo.mapper.ArticleTagMapper;
import com.zhiyiyo.service.ArticleTagService;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
