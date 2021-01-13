package com.freedom.springbootdevtemplate.common.log;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

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

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Object result = proceedingJoinPoint.proceed();

        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setIp(request.getRemoteAddr());
        requestInfo.setUrl(request.getRequestURL().toString());
        requestInfo.setHttpMethod(request.getMethod());
        requestInfo.setRequestParams(getRequestParam(proceedingJoinPoint));
        requestInfo.setResult(result);
        requestInfo.setTimeCost(System.currentTimeMillis() - startTime);
        log.info("Request Info: {}", new Gson().toJson(requestInfo));

        return result;
    }

    /**
     * 通过AOP获取入参数据
     *
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getRequestParam(JoinPoint joinPoint) {

        // joinPoint获取参数名
        String[] params = ((CodeSignature) joinPoint.getStaticPart().getSignature()).getParameterNames();
        // joinPoint获取参数值
        Object[] args = joinPoint.getArgs();

        Map<String, Object> param = new HashMap<>();

        int i = -1;
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse) {
                continue;
            }
            if (arg instanceof MultipartFile) {
                arg = ((MultipartFile) arg).getOriginalFilename();
            }
            param.put(params[++i], arg);
        }
        return param;
    }

}
