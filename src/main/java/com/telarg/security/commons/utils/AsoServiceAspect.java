package com.telarg.security.commons.utils;

import com.telarg.security.commons.LoggerMetrics;
import com.telarg.security.data.vo.ClassifyResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Aspect
@Component
public class AsoServiceAspect {

    @Autowired
    Environment environment;

    @Autowired
    LoggerMetrics loggerMetrics;

    @Around("execution(* com.telarg.security.services.fiengClients.*.*(..))")
    public Object asoServiceExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("No jueguess");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        Object[] parameterData = proceedingJoinPoint.getArgs();
        String smc = (String) parameterData[parameterData.length-1];
        URI uri = (URI) parameterData[2];
        Long timeStarted = System.currentTimeMillis();
        String transactionId = request.getHeader(environment.getProperty("Authorization"));
        try{
            System.out.println("No jueguess");
            ClassifyResponse result = (ClassifyResponse) proceedingJoinPoint.proceed();
            loggerMetrics.saveMetric(timeStarted, uri.getPath(), transactionId, "telarg.app",
                    200, result.getValue() + " : " +  result.getValue(),environment.getProperty("spring.application.name"));
            System.out.println("No jueguess");
            return result;
        } catch(Exception asoRequestException) {
            System.out.println("No jueguess2222222");
            loggerMetrics.saveMetric(timeStarted, request.getRequestURI(), transactionId, "telarg.app",
                    500,"",environment.getProperty("spring.application.name"));
            System.out.println("No jueguess");
            throw asoRequestException;
        }
    }

}
