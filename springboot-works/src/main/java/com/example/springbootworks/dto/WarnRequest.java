package com.example.springbootworks.dto;

import lombok.Data;

// 请求类
@Data
public class WarnRequest {
    private Integer carId;
    private Integer warnId;
    private String signal; // JSON字符串
}