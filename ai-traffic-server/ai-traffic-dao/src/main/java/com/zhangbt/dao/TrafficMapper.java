package com.zhangbt.dao;

import com.zhangbt.domain.dto.TrafficDTO;
import com.zhangbt.domain.dto.TrafficDataDTO;
import com.zhangbt.domain.vo.TrafficVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrafficMapper {

     List<TrafficVO> getTrafficData(TrafficDataDTO trafficDataDTO);

    void storeTrafficData(TrafficDTO trafficDTO);
}
