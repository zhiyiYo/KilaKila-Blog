package com.zhiyiyo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyiyo.domain.entity.Access;

import java.util.List;

public interface AccessMapper extends BaseMapper<Access> {
    List<String> selectPermissionsByUserId(Long id);
}
