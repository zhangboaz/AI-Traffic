package com.zhangbt.domain.dto;

import com.zhangbt.domain.entity.TrafficEntity;
import lombok.Data;

@Data
public class TrafficDataDTO {


    /**
     * 位置标识
     * 唯一标识数据来源的位置，如高速东入口
     */
    private String locationCode;


    /**
     * 数据产生时间（UNIX时间戳格式，秒级）
     * 用于时序数据分析与数据对齐
     * 示例：1717029200（2024-05-30 14:33:20）
     */
    private Long timestamp;



    /**
     * 坐标信息（经纬度）
     * 经纬度精度：小数点后6位（约0.11米）
     * 示例：longitude=120.123456, latitude=30.123456
     */
    private TrafficEntity.Coordinates coordinates;

    @Data
    public static class Coordinates {
        /**
         * 经度，范围：-180.000000 ~ 180.000000
         * 示例：120.123456
         */
        private Double longitude;

        /**
         * 纬度，范围：-90.000000 ~ 90.000000
         * 示例：30.123456
         */
        private Double latitude;
    }

}
