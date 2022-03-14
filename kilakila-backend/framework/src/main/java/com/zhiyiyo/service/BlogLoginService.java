package com.zhiyiyo.service;

import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.dto.LoginUserDTO;
import com.zhiyiyo.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(LoginUserDTO user);
    ResponseResult logout();
}

