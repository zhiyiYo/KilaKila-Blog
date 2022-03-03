package com.zhiyiyo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyiyo.constants.SystemConstants;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.vo.CommentVo;
import com.zhiyiyo.domain.vo.PageVo;
import com.zhiyiyo.mapper.CommentMapper;
import com.zhiyiyo.domain.entity.Comment;
import com.zhiyiyo.service.CommentService;
import com.zhiyiyo.service.UserService;
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
        // 查询所有根评论
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getArticleId, articleId);
        wrapper.eq(Comment::getRootId, SystemConstants.COMMENT_ROOT);

        Page<Comment> page = new Page<>(pageNum, pageSize);
        this.page(page, wrapper);

        List<Comment> comments = page.getRecords();
        List<CommentVo> commentVos = getCommentVos(comments);

        // 查询所有子评论
        commentVos.forEach(i -> i.setChildren(getChildren(i.getId())));
        return ResponseResult.okResult(new PageVo<>(page.getTotal(), commentVos));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        save(comment);
        return ResponseResult.okResult();
    }

    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getToCommentId, id);
        List<Comment> comments = list(wrapper);
        return getCommentVos(comments);
    }

    private List<CommentVo> getCommentVos(List<Comment> comments) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(comments, CommentVo.class);

        for (CommentVo commentVo : commentVos) {
            // 发表评论的用户名称
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);

            // 被回复的用户名称
            if (!SystemConstants.COMMENT_ROOT.equals(commentVo.getToCommentUserId())) {
                String name = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(name);
            }
        }

        return commentVos;
    }
}

