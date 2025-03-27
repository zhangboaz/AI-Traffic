package com.zhangbt.handler;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.zhangbt.common.exception.BaseException;
import com.zhangbt.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     */
    @ExceptionHandler(BaseException.class)
    public Result<String> exceptionHandler(BaseException e) {
        log.error("异常信息11：{}", e.getMessage());
        log.error(Result.error(e.getMessage()).toString());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> globalExceptionHandler(Exception e) {
        log.error("全局异常信息：{}", e.getMessage());
        return Result.error("系统发生错误，请稍后再试");
    }

}
