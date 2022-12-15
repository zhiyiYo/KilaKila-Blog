package com.zhiyiyo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.entity.Comment;

/**
 * 评论表(Comment)表服务接口
 */
public interface CommentService extends IService<Comment> {

    ResponseResult getCommentList(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);

    ResponseResult updateComment(Comment comment);

    ResponseResult deleteCommand(Long id);
}

