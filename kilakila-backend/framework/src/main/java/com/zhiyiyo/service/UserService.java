package com.zhiyiyo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.dto.UserDTO;
import com.zhiyiyo.domain.entity.User;

public interface UserService extends IService<User> {
    ResponseResult getUserInfo();
    ResponseResult getAdminInfo();
    ResponseResult register(User user);
    ResponseResult updateUserInfo(UserDTO user);
}
