package com.crazy.portal.config.service;

import com.crazy.portal.service.handover.DeliverService;
import com.crazy.portal.service.handover.IHandover;
import com.crazy.portal.service.handover.ReceiveService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lee on 2019/8/24.
 */

@Configuration
public class HandoverConfig {

    @Resource
    private DeliverService deliverService;
    @Resource
    private ReceiveService receiveService;

    @Bean
    public Map<String, IHandover> handoverServiceMap() {
        Map<String, IHandover> serviceMap = new HashMap<>();
        serviceMap.put("deliver", deliverService);
        serviceMap.put("receive", receiveService);
        return serviceMap;
    }
}
