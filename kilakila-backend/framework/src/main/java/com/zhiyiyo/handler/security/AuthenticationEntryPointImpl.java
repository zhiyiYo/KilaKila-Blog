package com.zhiyiyo.handler.security;

import com.alibaba.fastjson.JSON;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.enums.AppHttpCodeEnum;
import com.zhiyiyo.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        ResponseResult result = null;

        if (e instanceof InsufficientAuthenticationException) {
            result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        } else if (e instanceof BadCredentialsException) {
            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(), e.getMessage());
        } else {
            result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, "认证失败");
        }

        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
