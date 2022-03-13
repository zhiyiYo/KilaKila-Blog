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
 * 权限表(Access)表实体类
 *
 * @author makejava
 * @since 2022-03-13 15:06:32
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("access")
public class Access implements Serializable {

    private static final long serialVersionUID = -54158634882013443L;

        
    @TableId(type = IdType.AUTO)
    private Long id; 
    
    /**
     * 权限名
     */    
    private String accessName; 
    
    /**
     * 权限标识
     */    
    private String permission;
    
    /**
     * 权限状态（0正常 1停用）
     */    
    private String status; 
    
        
    private Long createBy; 
    
        
    private Date createTime; 
    
        
    private Long updateBy; 
    
        
    private Date updateTime; 
    
    /**
     * 是否删除（0未删除 1已删除）
     */    
    private Integer delFlag; 
    
    /**
     * 备注
     */    
    private String remark; 
    

}

