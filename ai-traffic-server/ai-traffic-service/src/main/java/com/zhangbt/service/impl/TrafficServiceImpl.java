package com.zhangbt.service.impl;

import com.zhangbt.common.utils.CustomCache;
import com.zhangbt.dao.TrafficMapper;
import com.zhangbt.domain.dto.TrafficDTO;
import com.zhangbt.domain.dto.TrafficDataDTO;
import com.zhangbt.domain.entity.TrafficEntity;
import com.zhangbt.domain.vo.TrafficVO;
import com.zhangbt.service.TrafficService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TrafficServiceImpl implements TrafficService {

    private final CustomCache<String, Object> customCache;

    private final TrafficMapper trafficMapper;


    @Override
    public String storeTrafficData(TrafficDTO trafficDTO) {
        String key = "traffic_data_" + trafficDTO.getLocationCode() + "_" + trafficDTO.getTimestamp();

        customCache.put(key, trafficDTO, 5000);

        // 存储到数据库
        trafficMapper.storeTrafficData(trafficDTO);

        return key;
    }

    @Override
    public List<TrafficVO> getTrafficData(TrafficDataDTO trafficDataDTO) {

        String timestamp = String.valueOf(trafficDataDTO.getTimestamp());

        if (customCache.get("traffic_data_" + trafficDataDTO.getLocationCode() + "_" + trafficDataDTO.getTimestamp()) != null) {

            return (List<TrafficVO>) customCache.get("traffic_data_" + trafficDataDTO.getLocationCode() + "_" + trafficDataDTO.getTimestamp());
        }

        return trafficMapper.getTrafficData(trafficDataDTO);
    }
}
