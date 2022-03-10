package com.zhiyiyo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhiyiyo.constants.SystemConstants;
import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.domain.dto.LoginUserDTO;
import com.zhiyiyo.domain.entity.LoginUser;
import com.zhiyiyo.domain.entity.User;
import com.zhiyiyo.domain.vo.BlogUserLoginVo;
import com.zhiyiyo.domain.vo.UserInfoVo;
import com.zhiyiyo.enums.AppHttpCodeEnum;
import com.zhiyiyo.mapper.UserMapper;
import com.zhiyiyo.service.BlogLoginService;
import com.zhiyiyo.service.UserService;
import com.zhiyiyo.utils.BeanCopyUtils;
import com.zhiyiyo.utils.JwtUtil;
import com.zhiyiyo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(LoginUserDTO user) {
        // 验证用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 将用户信息存入 redis
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String token = JwtUtil.createJWT(userId);
        redisCache.setCacheObject(SystemConstants.REDIS_USER_ID_PREFIX + userId, loginUser);

        // 将 token 和用户信息返回给用户
        UserInfoVo userInfo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        userInfo.setIsAdmin(SystemConstants.ADMIN_USER.equals(loginUser.getUser().getType()));
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(token, userInfo);
        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {
        // 获取 userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();

        // 从 redis 中删除用户信息
        redisCache.deleteObject(SystemConstants.REDIS_USER_ID_PREFIX + userId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        // 查询用户是否已经存在
        LambdaQueryWrapper<User> sameNameWrapper = new LambdaQueryWrapper<>();
        sameNameWrapper.eq(User::getUserName, user.getUserName());
        User sameNameUser = userMapper.selectOne(sameNameWrapper);
        if (sameNameUser != null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
        }

        // 查询邮箱是否已经存在
        LambdaQueryWrapper<User> sameEmailWrapper = new LambdaQueryWrapper<>();
        sameEmailWrapper.eq(User::getEmail, user.getEmail());
        User sameEmailUser = userMapper.selectOne(sameEmailWrapper);
        if (sameEmailUser != null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.EMAIL_EXIST);
        }

        // 添加用户
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        return ResponseResult.okResult();
    }
}
