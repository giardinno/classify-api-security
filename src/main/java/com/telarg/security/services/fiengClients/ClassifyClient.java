package com.telarg.security.services.fiengClients;

import com.telarg.security.data.vo.ClassifyRequest;
import com.telarg.security.data.vo.ClassifyResponse;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("classify")
@RibbonClient(name = "classify")
public interface ClassifyClient {

    @PostMapping("/classifier")
    public ClassifyResponse getClasification(@RequestBody ClassifyRequest classifyRequest);

}