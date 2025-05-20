package com.example.springbootworks.inter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 规则工厂
@Service
public class RuleFactory {
    private static final Map<Integer, RuleParser> PARSERS = new ConcurrentHashMap<>();

    static {
        PARSERS.put(1, new VoltageRuleParser()); // 1=电压差规则
        PARSERS.put(2, new CurrentRuleParser());  // 2=电流差规则
    }

    // 获取对应的规则解析器
    public static RuleParser getParser(Integer ruleType) {
        return PARSERS.getOrDefault(ruleType, new DefaultRuleParser());
    }
}