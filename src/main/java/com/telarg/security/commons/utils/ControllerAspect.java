package com.telarg.security.commons.utils;

import com.telarg.security.commons.LoggerMetrics;
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


@Aspect
@Component
public class ControllerAspect {

    @Autowired
    Environment environment;

    @Autowired
    LoggerMetrics loggerMetrics;

    @Around("(@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            " @annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)) && execution(public * *(..))")
    public Object controllerExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("No mamesssss");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        String transactionId = request.getHeader(environment.getProperty("Authorization"));
        String applicationName = environment.getProperty("spring.application.name") != null ? environment.getProperty("spring.application.name") : "";
        Long timeStarted = System.currentTimeMillis();
        try{
            System.out.println("No mamesssss");
            ResponseEntity<Object> result = (ResponseEntity<Object>) proceedingJoinPoint.proceed();
            System.out.println("No mamesssss");
            loggerMetrics.saveMetric(timeStarted, request.getRequestURI(), transactionId, "telarg.app",
                                     result.getStatusCode().value(),result.getBody(),applicationName);
            System.out.println("No mamesssss");
            return result;
        } catch(Exception e) {
            System.out.println("No mamesssss2222");
            loggerMetrics.saveMetric(timeStarted, request.getRequestURI(), transactionId, "telarg.app",
            500 ,"Error en el servicio",applicationName);
            System.out.println("No mamessss3333");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
