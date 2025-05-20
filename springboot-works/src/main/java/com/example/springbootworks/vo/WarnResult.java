package com.example.springbootworks.vo;

import lombok.Data;

// 查询预警信息响应类
@Data
public class WarnResult {
    private Integer carId;
    private String batteryType;
    private String warnName;
    private Integer warnLevel;
}