package com.zhangbt.service;

import com.zhangbt.domain.dto.TrafficDTO;
import com.zhangbt.domain.dto.TrafficDataDTO;
import com.zhangbt.domain.entity.TrafficEntity;
import com.zhangbt.domain.vo.TrafficVO;

import java.util.List;

public interface TrafficService {
    String storeTrafficData(TrafficDTO trafficDTO);

    List<TrafficVO> getTrafficData(TrafficDataDTO trafficDataDTO);
}
