package com.zhiyiyo.utils;

import com.zhiyiyo.enums.AppHttpCodeEnum;
import com.zhiyiyo.exception.SystemException;
import org.apache.poi.xssf.usermodel.TextHorizontalOverflow;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

public class Assert {
    private Assert() {
    }

    /**
     * 断言对象不为空，为空则抛出异常
     *
     * @param object       对象
     * @param httpCodeEnum 状态码枚举类
     * @throws SystemException 系统异常
     */
    public static void notNull(@Nullable Object object, AppHttpCodeEnum httpCodeEnum) {
        if (object == null) {
            throw new SystemException(httpCodeEnum);
        }
    }

    /**
     * 断言对象为空，否则抛出异常
     *
     * @param object       对象
     * @param httpCodeEnum 状态码枚举类
     * @throws SystemException 系统异常
     */
    public static void isNull(Object object, AppHttpCodeEnum httpCodeEnum) {
        if (object != null) {
            throw new SystemException(httpCodeEnum);
        }
    }

    /**
     * 断言标志为真
     * @param flag 标志
     * @param httpCodeEnum 状态码枚举类
     * @throws SystemException 系统异常
     */
    public static void isTrue(boolean flag, AppHttpCodeEnum httpCodeEnum) {
        if (!flag) {
            throw new SystemException(httpCodeEnum);
        }
    }
}
