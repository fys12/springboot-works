package com.example.springbootworks.inter;

import lombok.Data;

// 规则类
@Data
public class RuleCondition {
    private Double min; // 最小值（null表示不限制）
    private Double max; // 最大值（null表示不限制）
    private Integer level; // 预警等级
    
    // 判断值是否符合条件
    public boolean match(double value) {
        boolean meetMin = min == null || value >= min;
        boolean meetMax = max == null || value < max;
        return meetMin && meetMax;
    }
}