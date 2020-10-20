package com.freedom.springbootdevtemplate.common.log;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author freedom
 * @date 2020/2/27 20:47
 * @description 日志切面
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * 以controller包下定义的所以请求为切入点
     */
    @Pointcut("execution(public * com.freedom.springbootdevtemplate.controller..*.*(..))")
    public void webLog() {}

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL         : {}", request.getRequestURL().toString());
        log.info("Http Method : {}", request.getMethod());
        log.info("IP          : {}", request.getRemoteAddr());
        log.info("Request Args: {}", getRequestParam(joinPoint));
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("Response Args : {}", result != null ? new Gson().toJson(result) : "");
        log.info("Time-consuming: {}", System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     * 通过AOP获取入参数据
     *
     * @param joinPoint
     * @return
     */
    private String getRequestParam(JoinPoint joinPoint) {

        // joinPoint获取参数名
        String[] params = ((CodeSignature) joinPoint.getStaticPart().getSignature()).getParameterNames();
        // joinPoint获取参数值
        Object[] args = joinPoint.getArgs();

        Map<String, Object> param = new HashMap<>();

        int i = -1;
        for (Object arg : args) {
            i++;
            if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse) {
                continue;
            }
            param.put(params[i], arg);
        }

        return new Gson().toJson(param);
    }

}
