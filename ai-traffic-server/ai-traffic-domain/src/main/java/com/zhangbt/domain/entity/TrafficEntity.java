package com.zhangbt.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrafficEntity {

    /**
     * {
     *   "locationCode": "CAM-001",
     *   "locationDescription": "高速东入口，靠近收费站",
     *   "timestamp": 1717029200,
     *   "vehicle_count": 42,
     *   "avg_speed": 45.6,
     *   "coordinates": {
     *     "longitude": 120.123456,
     *     "latitude": 30.123456
     *   },
     *   "traffic_status": "畅通"
     * }
     */

    /**
     * 位置标识
     * 唯一标识数据来源的位置，如高速东入口
     */
    private String locationCode;

    /**
     * 位置描述
     * 对该位置的详细说明，如“高速东入口，靠近收费站”
     */
    private String locationDescription;

    /**
     * 数据产生时间（UNIX时间戳格式，秒级）
     * 用于时序数据分析与数据对齐
     * 示例：1717029200（2024-05-30 14:33:20）
     */
    private Long timestamp;

    /**
     * 车辆计数
     * 统计传感器覆盖区域的瞬时车辆数量
     * 示例：42
     */
    private Integer vehicleCount;

    /**
     * 平均速度（单位：km/h）
     * 反映道路通行效率，计算行程时间的重要参数
     * 示例：45.6
     * 数据库存储建议使用 DECIMAL(5,1)
     */
    private Double avgSpeed;

    /**
     * 坐标信息（经纬度）
     * 经纬度精度：小数点后6位（约0.11米）
     * 示例：longitude=120.123456, latitude=30.123456
     */
    private Coordinates coordinates;

    /**
     * 交通状态
     * 典型状态：
     * 畅通（>40 km/h）、缓行（20-40 km/h）、拥堵（<20 km/h）
     * 示例："畅通"
     */
    private String trafficStatus;

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
