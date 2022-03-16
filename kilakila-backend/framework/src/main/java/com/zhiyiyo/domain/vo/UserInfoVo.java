package com.zhiyiyo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private Long id;

    private String userName;

    private String nickName;

    private String signature;

    private String avatar;

    private String sex;

    private Boolean isAdmin;

    private String email;

    private String phonenumber;
}
