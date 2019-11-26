package com.telarg.security.commons.utils;

import com.telarg.security.commons.LoggerMetrics;
import com.telarg.security.data.vo.ClassifyResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> asoServiceExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        Long timeStarted = System.currentTimeMillis();
        String transactionId = request.getHeader("Authorization");
        try{
            ClassifyResponse result = (ClassifyResponse) proceedingJoinPoint.proceed();
            loggerMetrics.saveMetric(timeStarted, request.getRequestURI(), transactionId, "telarg.app",
                    200, result.getTag() + " : " +  result.getValue(),environment.getProperty("spring.application.name"));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            loggerMetrics.saveMetric(timeStarted, request.getRequestURI(), transactionId, "telarg.app",
                    500,"",environment.getProperty("spring.application.name"));
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
