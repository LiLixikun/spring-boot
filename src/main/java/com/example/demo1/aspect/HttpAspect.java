package com.example.demo1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

//    public void log() {
//
//    }
//
//    @Before("log()")
//    public void doBefore(JoinPoint joinPoint) {
//
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        //url
//        logger.info("url=" + request.getRequestURI());
//        //method
//        logger.info("请求方法" + request.getMethod());
//        //ip
//        logger.info("请求方法" + request.getRemoteAddr());
//
//        //controller
//        logger.info(("类方法=" + joinPoint.getSignature().getDeclaringTypeName()));
//        //参数
//        logger.info("参数=" + joinPoint.getArgs());
//    }
//
//    @After("log()")
//    public void doAfter() {
//        logger.info("2222222222222");
//    }
//
//    @AfterReturning(returning = "object", pointcut = "log()")
//    public void doAfterReturnK(Object object) {
//        logger.info("response={}" + object.toString());
//    }
}
