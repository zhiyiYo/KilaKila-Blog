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
 * 角色表(Role)表实体类
 *
 * @author makejava
 * @since 2022-03-13 15:07:15
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
public class Role implements Serializable {

    private static final long serialVersionUID = -71014478293783317L;

        
    @TableId(type = IdType.AUTO)
    private Long id; 
    
        
    private String name; 
    
    /**
     * 角色权限字符串
     */    
    private String roleKey; 
    
    /**
     * 角色状态（0正常 1停用）
     */    
    private String status; 
    
    /**
     * del_flag
     */    
    private Integer delFlag; 
    
        
    private Long createBy; 
    
        
    private Date createTime; 
    
        
    private Long updateBy; 
    
        
    private Date updateTime; 
    
    /**
     * 备注
     */    
    private String remark; 
    

}

