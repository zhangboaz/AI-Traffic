package com.zhangbt.controller;

import com.zhangbt.common.result.Result;
import com.zhangbt.common.utils.CustomCache;
import com.zhangbt.domain.dto.TrafficDTO;
import com.zhangbt.domain.dto.TrafficDataDTO;
import com.zhangbt.domain.vo.TrafficVO;
import com.zhangbt.service.TrafficService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/traffic")
@Slf4j
@AllArgsConstructor
public class TrafficController {

    private final TrafficService trafficService;

    private final CustomCache<String, Object> customCache;

    private final SimpMessagingTemplate messagingTemplate; // 注入消息模板

    @PostMapping("/data")
    public Result<TrafficVO> getData(@RequestBody TrafficDTO trafficDTO) {
        log.info("交通数据：{}", trafficDTO);
        // 先保存数据到缓存
        String key = trafficService.storeTrafficData(trafficDTO);

        // 使用ws发送到前端
        messagingTemplate.convertAndSend("/topic/trafficData", trafficDTO);

        log.info("cache数据：{}-{}", key, customCache.get(key));
        return Result.success();
    }

    @PostMapping("/getTrafficData")
    public Result<List<TrafficVO>> getTrafficData(@RequestBody TrafficDataDTO trafficDataDTO) {
        log.info("TrafficDataDTO:{}", trafficDataDTO);
        List<TrafficVO> trafficData = trafficService.getTrafficData(trafficDataDTO);
        return Result.success(trafficData);
    }

}
