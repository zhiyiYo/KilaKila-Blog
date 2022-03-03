package com.zhiyiyo.handler.exception;


import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.enums.AppHttpCodeEnum;
import com.zhiyiyo.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({SystemException.class})
    public ResponseResult systemExceptionHandler(SystemException e) {
        log.error("发生异常！{0}", e);
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("发生异常！{0}", e);
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseResult.errorResult(AppHttpCodeEnum.REQUIRE_USER_INFO, message);
    }

    @ExceptionHandler({Exception.class})
    public ResponseResult exceptionHandler(Exception e) {
        log.error("发生异常！{0}", e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, e.getMessage());
    }
}
