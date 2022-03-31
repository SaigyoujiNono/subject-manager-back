package com.mqd.gxcj.subjectmanager.handler;

import cn.dev33.satoken.exception.NotLoginException;
import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.utils.R;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exception(Exception e){
        String message = e.getMessage();
        if (e instanceof NotLoginException){
            log.error(message);
            return R.notLogin();
        } else if (e instanceof BindException){
            return R.newInstance(RStatus.VERIFY_ERROR);
        } else if (e instanceof AppException){
            AppException ae = (AppException) e;
            return R.newInstance(ae.getStatus());
        }
        e.printStackTrace();
        return R.fail();
    }
}
