package com.zhiyiyo.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 用户表(User)表实体类
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 390745714345645196L;

    /**
     * 主键
     */    
    @TableId(type = IdType.AUTO)
    private Long id; 
    
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName; 
    
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    /**
     * 个性签名
     */
    @Length(max = 64, message = "个性签名的长度不能超过 64 个字符")
    private String signature;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password; 
    
    /**
     * 用户类型：0代表普通用户，1代表管理员
     */    
    private String type; 
    
    /**
     * 账号状态（0正常 1停用）
     */    
    private String status; 
    
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email; 
    
    /**
     * 手机号
     */    
    private String phonenumber; 
    
    /**
     * 用户性别（0男，1女，2未知）
     */    
    private String sex; 
    
    /**
     * 头像
     */    
    private String avatar; 
    
    /**
     * 创建人的用户id
     */    
    private Long createBy; 
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime; 
    
    /**
     * 更新人
     */    
    private Long updateBy; 
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime; 
    
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */    
    private Integer delFlag; 
    

}

