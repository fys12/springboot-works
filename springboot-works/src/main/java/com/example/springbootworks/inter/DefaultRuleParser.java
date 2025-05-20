package com.example.springbootworks.inter;

import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.domain.TbWarnRule;

// 默认解析器
public class DefaultRuleParser implements RuleParser {
    @Override
    public int calculateLevel(TbSignal signal, TbWarnRule rule) {
        System.out.println("未知规则类型，使用默认解析器:" + rule.getRuleType());
        return -1; // 默认不触发预警
    }
}