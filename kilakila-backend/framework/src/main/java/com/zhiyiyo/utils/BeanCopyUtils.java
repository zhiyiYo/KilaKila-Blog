package com.zhiyiyo.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    /**
     * 拷贝属性
     *
     * @param o     原始数据实体类
     * @param clazz 目标数据实体类
     * @param <V>   目标实体类的类型
     * @return 拷贝后的目标实体类
     */
    public static <V> V copyBean(Object o, Class<V> clazz) {
        V t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(o, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 拷贝属性列表
     * @param oList 原始数据实体类列表
     * @param clazz 目标数据实体类
     * @param <O> 原始数据实体类类型
     * @param <V> 目标数据实体类类型
     * @return 拷贝后的目标数据实体类列表
     */
    public static <O, V> List<V> copyBeanList(List<O> oList, Class<V> clazz) {
        return oList.stream().map(o -> copyBean(o, clazz)).collect(Collectors.toList());
    }
}
