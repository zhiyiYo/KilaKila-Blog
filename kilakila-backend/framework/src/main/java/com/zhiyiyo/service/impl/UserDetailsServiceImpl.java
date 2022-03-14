package com.zhiyiyo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhiyiyo.domain.entity.LoginUser;
import com.zhiyiyo.domain.entity.User;
import com.zhiyiyo.enums.AppHttpCodeEnum;
import com.zhiyiyo.mapper.AccessMapper;
import com.zhiyiyo.mapper.UserMapper;
import com.zhiyiyo.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccessMapper accessMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        User user = userMapper.selectOne(wrapper);
        Assert.notNull(user, AppHttpCodeEnum.LOGIN_ERROR);

        List<String> permissions = accessMapper.selectPermissionsByUserId(user.getId());
        return new LoginUser(user, permissions);
    }
}
