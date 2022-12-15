package com.zhiyiyo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyiyo.constants.SystemConstants;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.entity.User;
import com.zhiyiyo.domain.vo.CommentVo;
import com.zhiyiyo.domain.vo.PageVo;
import com.zhiyiyo.enums.AppHttpCodeEnum;
import com.zhiyiyo.mapper.CommentMapper;
import com.zhiyiyo.domain.entity.Comment;
import com.zhiyiyo.service.CommentService;
import com.zhiyiyo.service.UserService;
import com.zhiyiyo.utils.Assert;
import com.zhiyiyo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;

    @Override
    public ResponseResult getCommentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 查询所有评论
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getArticleId, articleId);
        Page<Comment> page = new Page<>(pageNum, pageSize);
        this.page(page, wrapper);

        List<Comment> comments = page.getRecords();

        // 为评论添加用户信息
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(comments, CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            User user = userService.getById(commentVo.getCreateBy());
            commentVo.setUserName(user.getNickName());
            commentVo.setIsAdmin(SystemConstants.ADMIN_USER.equals(user.getType()));
            commentVo.setAvatar(user.getAvatar());
        }

        // 查询所有子评论
        return ResponseResult.okResult(new PageVo<>(page.getTotal(), commentVos));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        save(comment);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateComment(Comment comment) {
        boolean success = updateById(comment);
        Assert.isTrue(success, AppHttpCodeEnum.RESOURCE_NOT_EXIST);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteCommand(Long id) {
        boolean success = removeById(id);
        Assert.isTrue(success, AppHttpCodeEnum.RESOURCE_NOT_EXIST);
        return ResponseResult.okResult();
    }

}

