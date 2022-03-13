package com.zhiyiyo.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 分类表(Category)表实体类
 *
 * @author makejava
 * @since 2022-02-27 15:11:49
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 853190758604791410L;

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

