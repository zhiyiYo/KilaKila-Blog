package com.zhiyiyo.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 友链(Link)表实体类
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("link")
public class Link implements Serializable {

    private static final long serialVersionUID = 553690364813080660L;

        
    @TableId(type = IdType.AUTO)
    private Long id; 
    
        
    private String name; 
    
        
    private String logo; 
    
        
    private String description; 
    
    /**
     * 网站地址
     */    
    private String address; 
    
    /**
     * 审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
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

