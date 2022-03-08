package com.zhiyiyo.service;

import com.zhiyiyo.domain.ResponseResult;

public interface ArchiveService {
    ResponseResult getArchiveCountList(Integer pageNum, Integer pageSize);
}
