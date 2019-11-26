package com.telarg.security.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class LoggerMetrics {

    private static final Log log = LogFactory.getLog(LoggerMetrics.class);

    public void saveMetric(Long timeStarted, String  endpoint, String transactionId,
                                  String applicationId, Integer statusCode, Object response, String serviceName, boolean isController){
        ObjectMapper objectMapper = new ObjectMapper();
        Long timeFinished = System.currentTimeMillis();
        Long timeElapsed = timeFinished - timeStarted;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zone = ZoneId.of("America/Mexico_City");
        LocalDateTime dateTime = LocalDateTime.now(zone);
        JSONObject metricData = new JSONObject();
        metricData.put("endpoint",endpoint);
        metricData.put("transactionId",transactionId);
        metricData.put("applicationId",applicationId);
        metricData.put("startDate",dateTime.format(formatter));
        metricData.put("elapsedTime",timeElapsed);
        metricData.put("httpStatus",statusCode);
        metricData.put("serviceName",serviceName);
        if (isController)
            metricData.put("source","Controller");
        else
            metricData.put("source","Classifier");
        try {
            metricData.put("response", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        }catch (JsonProcessingException jpException){
            metricData.put("response","");
        }
        log.info(metricData);
    }

}
