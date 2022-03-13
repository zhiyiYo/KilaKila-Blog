package com.zhiyiyo.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * (UserRole)表实体类
 *
 * @author makejava
 * @since 2022-03-13 15:07:50
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -76032783808544756L;

    /**
     * 用户id
     */    
    private Long userId; 
    
    /**
     * 角色id
     */    
    private Long roleId; 
    

}

