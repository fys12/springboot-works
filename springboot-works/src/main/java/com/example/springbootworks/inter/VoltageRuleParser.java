package com.example.springbootworks.inter;

import com.alibaba.fastjson.JSON;
import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.domain.TbWarnRule;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

// 电压差规则解析器
@Service
public class VoltageRuleParser implements RuleParser {

    @Override
    public int calculateLevel(TbSignal signal, TbWarnRule rule) {
        // 计算电压差值
        BigDecimal diff = signal.getMx().subtract(signal.getMi());

        List<RuleCondition> conditions = JSON.parseArray(rule.getConditions(), RuleCondition.class);
        return conditions.stream()
                .filter(c -> c.match(diff.doubleValue())) // 判断条件是否满足
                .mapToInt(RuleCondition::getLevel) // 获取满足条件的规则级别
                .findFirst() //  返回第一个满足条件的规则级别
                .orElse(-1); // -1表示不报警
    }
}