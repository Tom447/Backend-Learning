package com.itheima.aop;


import com.alibaba.fastjson.JSON;
import com.itheima.mapper.LogMapper;
import com.itheima.pojo.OperateLog;
import lombok.extern.slf4j.Slf4j;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {



    @Autowired
    private LogMapper logMapper;

    // Yauaa 分析器（单例、线程安全）
    private static final UserAgentAnalyzer USER_AGENT_ANALYZER =
            UserAgentAnalyzer.newBuilder()
                    .hideMatcherLoadStats()
                    .withCache(10000)
                    .build();

    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint pjp) throws Throwable {

        LocalDateTime operatorTime = LocalDateTime.now();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (attributes != null) ? attributes.getRequest() : null;
        // 默认值
        String url = "N/A";
        String clientBrowser = "Unknown";
        String clientPlatform = "Unknown";
        if (request != null){
            url = request.getRequestURL().toString();
            clientBrowser = request.getHeader("User-Agent");
            clientPlatform = request.getHeader("sec-ch-ua-platform");
        }
        //访问类名
        String targetClassName = pjp.getTarget().getClass().getName();
        //访问目标方法的方法名
        String targetMethodName = pjp.getSignature().getName();
        //运行时的参数
        Object[] args = pjp.getArgs();
        String methodParam = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //调用原目标方法运行
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        //方法返回值
        String resultValue = JSON.toJSONString(result);




        //操作耗时
        Long costTime = end - begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(null, url, targetClassName, targetMethodName, methodParam, resultValue, clientBrowser, clientPlatform, operatorTime, costTime);

        logMapper.insert(operateLog);
        log.info("operateLog:{}",operateLog);

        return result;

    }

}
