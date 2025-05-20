package com.example.springbootworks.inter;

import com.alibaba.fastjson.JSON;
import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.domain.TbWarnRule;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

// 电流差规则器
@Service
public class CurrentRuleParser implements RuleParser {
    @Override
    public int calculateLevel(TbSignal signal, TbWarnRule rule) {
        System.out.println("当前规则类型：" + rule.getRuleType());
        System.out.println(signal);
        // 计算电流差值
        BigDecimal diff = signal.getMx().subtract(signal.getMi());// 计算电流差值
        List<RuleCondition> conditions = JSON.parseArray(rule.getConditions(), RuleCondition.class);
        
        return conditions.stream()
            .filter(c -> c.match(diff.doubleValue()))
            .mapToInt(RuleCondition::getLevel)
            .findFirst()
            .orElse(-1); // -1表示不触发预警
    }
}