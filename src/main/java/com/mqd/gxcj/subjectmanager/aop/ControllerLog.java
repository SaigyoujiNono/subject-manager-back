package com.mqd.gxcj.subjectmanager.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerLog {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void requestLog(){}

    @Around("requestLog()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        }finally {
            long t2 = System.currentTimeMillis();
            long time = t2-t1;
            if (time < 1500){
                log.info("{} request success: {}",joinPoint.getSignature(),time);
            }else {
                log.warn("{} request success: {}",joinPoint.getSignature(),time);
            }
        }
        return result;
    }
}
