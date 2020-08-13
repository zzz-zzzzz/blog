package com.tsu.zzz.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(* com.tsu.zzz.controller.*.*(..))")
    public void log() {

    }

    @Around("log()")
    public Object aroundPrint(ProceedingJoinPoint pjd) {
        Object returnValue = null;
        try {
            RequestLog requestLog = new RequestLog();
            Object[] args = pjd.getArgs();
            requestLog.setArgs(args);
            requestLog.setIp(request.getRemoteAddr());
            requestLog.setUrl(request.getRequestURL().toString());
            String classMethod = pjd.getTarget().getClass().getName() + "." + pjd.getSignature().getName();
            requestLog.setClassMethod(classMethod);
            log.info("Request:{}", requestLog.toString());
            returnValue = pjd.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {

        }
        return returnValue;
    }

    @AllArgsConstructor
    @ToString
    @NoArgsConstructor
    @Data
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }

}
