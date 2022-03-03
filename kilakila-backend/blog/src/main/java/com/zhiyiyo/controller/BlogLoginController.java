package com.zhiyiyo.controller;

import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.dto.LoginUserDTO;
import com.zhiyiyo.domain.entity.User;
import com.zhiyiyo.enums.AppHttpCodeEnum;
import com.zhiyiyo.exception.SystemException;
import com.zhiyiyo.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@Valid @RequestBody LoginUserDTO user) {
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }

    @PostMapping("/register")
    public ResponseResult register(@Valid @RequestBody User user){
        return blogLoginService.register(user);
    }
}
