package com.zhiyiyo.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 评论表(Comment)表实体类
 *
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = -80038233433541586L;

        
    @TableId(type = IdType.AUTO)
    private Long id; 
    
    /**
     * 评论类型（0代表文章评论，1代表友链评论）
     */    
    private String type; 
    
    /**
     * 文章id
     */    
    private Long articleId; 
    
    /**
     * 根评论id
     */    
    private Long rootId; 
    
    /**
     * 评论内容
     */    
    private String content; 
    
    /**
     * 所回复的目标评论的userid
     */    
    private Long toCommentUserId; 
    
    /**
     * 回复目标评论id
     */    
    private Long toCommentId; 
    
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime; 
    
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */    
    private Integer delFlag; 
    

}

