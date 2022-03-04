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
 * 标签表(Tag)表实体类
 *
 * @author makejava
 * @since 2022-03-04 16:45:18
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = -67606537992531700L;

        
    @TableId(type = IdType.AUTO)
    private Long id; 
    
    /**
     * 标签名
     */    
    private String name; 
    
        
    private Long createBy; 
    
        
    private Date createTime; 
    
        
    private Long updateBy; 
    
        
    private Date updateTime; 
    
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */    
    private Integer delFlag; 
    

}

