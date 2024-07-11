package com.roger.exception;

import com.roger.user.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j// If you want to  responsebody return the error by exceptionHandler need to write this annotation
public class GlobalExceptionHandler {

    // private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ArithmeticException.class)
    public Result handleRuntimeException(ArithmeticException ex) {
        // 紀錄詳細的錯誤日誌
        log.error(ex.getMessage(), ex);

        // 提供更加具體的訊息
        String errorMessage = "發生不必要的錯誤: " + ex.getMessage() + "，請盡快解決";
        return Result.error(errorMessage);
    }
}
