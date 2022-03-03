package com.zhiyiyo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.entity.User;
import com.zhiyiyo.domain.vo.UserInfoVo;
import com.zhiyiyo.mapper.UserMapper;
import com.zhiyiyo.service.UserService;
import com.zhiyiyo.utils.BeanCopyUtils;
import com.zhiyiyo.utils.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public ResponseResult getUserInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        return ResponseResult.okResult(BeanCopyUtils.copyBean(user, UserInfoVo.class));
    }
}
