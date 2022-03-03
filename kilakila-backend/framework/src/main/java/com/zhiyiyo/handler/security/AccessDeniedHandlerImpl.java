package com.zhiyiyo.handler.security;

import com.alibaba.fastjson.JSON;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.enums.AppHttpCodeEnum;
import com.zhiyiyo.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException, ServletException {
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH, e.getMessage());
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
