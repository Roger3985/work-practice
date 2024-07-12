package com.roger.exception;

import com.roger.user.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

// @ControllerAdvice
// @ResponseBody // If you want to  responsebody return the error by exceptionHandler need to write this annotation
@RestControllerAdvice // @ControllerAdvice and @ResponseBody combine
@Slf4j // use it
public class GlobalExceptionHandler {

    // private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class); // this is log4j2

    /**
     * catch the ArithmeticException
     */
    @ExceptionHandler(ArithmeticException.class)
    public Result handArithmeticException(ArithmeticException ae, HttpServletRequest request) {
        String errorMessage = null;
        if (request.getRequestURL().toString().contains("/master")) {
            // 提供更加具體的訊息
            errorMessage = "Master(URL): " + request.getRequestURL() + " 發生算數錯誤: " + ae.getMessage() + "，請盡快解決";
        } else {
            // 提供更加具體的訊息
            errorMessage = "Other(URL): " + request.getRequestURL() + " 發生算數錯誤: " + ae.getMessage() + "，請盡快解決";
        }
        // 紀錄詳細的錯誤日誌
        log.error(errorMessage, ae); // (ae) Second param can use the stack to find the wrong
        return Result.error(errorMessage);
    }

    /**
     * catch other Exception
     */
    @ExceptionHandler(Exception.class)
    public Result handRuntimeException(Exception e, HttpServletRequest request) {
        // log
        log.error(e.getMessage(), e);
        // give the more information
        String errorMessage = "URL: " + request.getRequestURI() + " 發生除算數以外不必要的錯誤: " + e.getMessage() + "，請盡快解決";
        return Result.error(errorMessage);
    }
}
