package com.zhiyiyo.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * (RoleAccess)表实体类
 *
 * @author makejava
 * @since 2022-03-13 15:07:39
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role_access")
public class RoleAccess implements Serializable {

    private static final long serialVersionUID = -39696092441981113L;

    /**
     * 角色ID
     */    
    private Long roleId; 
    
    /**
     * 权限id
     */    
    private Long accessId; 
    

}

