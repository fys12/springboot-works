package com.example.springbootworks.inter;

import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.domain.TbWarnRule;


// 规则解析器接口
public interface RuleParser {

    /**
     * 计算预警等级
     * @param signal 信号
     * @param rule 预警规则
     * @return 预警等级
     */
    int calculateLevel(TbSignal signal, TbWarnRule rule);

    /**
     * 解析规则
     * @param rule 预警规则
     * @param signal 信号
     * @return 预警等级
     */
    static int parse(TbWarnRule rule, TbSignal signal) {
        // 静态方法：通过工厂获取解析器并计算结果
        RuleParser parser = RuleFactory.getParser(rule.getRuleType());
        return parser.calculateLevel(signal, rule);
    }
}
