package org.example.newmybatis.exception;

import org.example.newmybatis.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局对象处理，捕获controller里面的异常
 */

@ControllerAdvice("org.example.newmybatis.controller")
public class GlobalExceptionHandler {
    /**
     * 拿到异常类
     *
     * @return
     */

    @ExceptionHandler(Exception.class)
    /**
     * 指明返回的解析的是json
     */
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error();
    }

}
