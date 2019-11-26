package com.telarg.security.commons.utils;

import com.telarg.security.commons.LoggerMetrics;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private Log log = LogFactory.getLog(ControllerAspect.class);

    @Around("(@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            " @annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)) && execution(public * *(..))")
    public ResponseEntity<Object> controllerExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            Long timeStarted = System.currentTimeMillis();
            String applicationName = environment.getProperty("spring.application.name") != null ? environment.getProperty("spring.application.name") : "";
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String transactionId = request.getHeader("Authorization");
            try {
                ResponseEntity<Object> result = (ResponseEntity<Object>) proceedingJoinPoint.proceed();
                loggerMetrics.saveMetric(timeStarted, request.getRequestURI(), transactionId, "telarg.app",
                        result.getStatusCode().value(), result.getBody(), applicationName, true);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                loggerMetrics.saveMetric(timeStarted, request.getRequestURI(), transactionId, "telarg.app",
                        500, "Error en el servicio", applicationName, true);
            }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
