package com.zhiyiyo.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 文章表(Article)表实体类
 *
 * @author makejava
 * @since 2022-02-26 15:35:01
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article")
public class Article implements Serializable {

    private static final long serialVersionUID = 894377794856246143L;


    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 所属分类id
     */
    private Long categoryId;

    /**
     * 所属分类名
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 是否置顶（0否，1是）
     */
    private String isTop;

    /**
     * 状态（0已发布，1草稿）
     */
    private String status;

    /**
     * 访问量
     */
    private Long viewCount;

    /**
     * 是否允许评论 1是，0否
     */
    private String isComment;


    private Long createBy;


    private Date createTime;


    private Long updateBy;


    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;


    /**
     * 分类表(Category)表实体类
     *
     * @author makejava
     * @since 2022-02-27 15:05:36
     */
    @SuppressWarnings("serial")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @TableName("category")
    public static class Category implements Serializable {

        private static final long serialVersionUID = 791150426326873279L;


        @TableId(type = IdType.AUTO)
        private Long id;

        /**
         * 分类名
         */
        private String name;

        /**
         * 父分类id，如果没有父分类为-1
         */
        private Long pid;

        /**
         * 描述
         */
        private String description;

        /**
         * 状态0:正常,1禁用
         */
        private String status;


        private Long createBy;


        private Date createTime;


        private Long updateBy;


        private Date updateTime;

        /**
         * 删除标志（0代表未删除，1代表已删除）
         */
        private Integer delFlag;


    }
}

