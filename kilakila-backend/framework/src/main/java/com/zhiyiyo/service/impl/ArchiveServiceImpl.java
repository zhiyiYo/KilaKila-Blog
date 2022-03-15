package com.zhiyiyo.service.impl;

import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.vo.ArchiveCountVo;
import com.zhiyiyo.domain.vo.ArchiveVo;
import com.zhiyiyo.domain.vo.PageVo;
import com.zhiyiyo.mapper.ArchiveMapper;
import com.zhiyiyo.mapper.ArticleMapper;
import com.zhiyiyo.service.ArchiveService;
import com.zhiyiyo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private ArchiveMapper archiveMapper;

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getArchiveCountList(Integer pageNum, Integer pageSize) {
        Long total = archiveMapper.selectArchiveTotalCount();
        List<ArchiveCountVo> archiveCountVos = archiveMapper.selectArchiveCount((pageNum - 1) * pageSize, pageSize);
        return ResponseResult.okResult(new PageVo<>(total, archiveCountVos));
    }

    @Override
    public ResponseResult getArchiveList(Integer pageNum, Integer pageSize) {
        List<ArchiveVo> archiveVos = archiveMapper.selectArchiveList((pageNum - 1) * pageSize, pageSize);
        return ResponseResult.okResult(new PageVo<>(articleService.getNormalArticleCount(), archiveVos));
    }
}
