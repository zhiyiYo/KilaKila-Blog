package com.zhiyiyo.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 文章-标签表(ArticleTag)表实体类
 *
 * @author makejava
 * @since 2022-03-04 16:45:48
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_tag")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 737059071294711996L;

    /**
     * 文章 ID
     */
    private Long articleId;

    /**
     * 标签 ID
     */
    private Long tagId;

    /**
     * 标签的出现次数
     */
    @TableField(exist = false)
    private Integer count = 0;

    public ArticleTag(Long articleId, Long tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}

