package com.example.springbootworks.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootworks.domain.TbWarnRule;
import com.example.springbootworks.service.TbWarnRuleService;
import com.example.springbootworks.mapper.TbWarnRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_warn_rule(预警规则表)】的数据库操作Service实现
* @createDate 2025-05-18 07:01:03
*/
@Service
public class TbWarnRuleServiceImpl extends ServiceImpl<TbWarnRuleMapper, TbWarnRule>
    implements TbWarnRuleService{

    @Resource
    private TbWarnRuleMapper warnRuleMapper;

    @Override
    public List<TbWarnRule> getRulesByType(Integer batteryType) {
        QueryWrapper<TbWarnRule> query = new QueryWrapper<>();
        query.eq("battery_type", batteryType);
        return warnRuleMapper.selectList(query);
    }

    @Override
    public List<TbWarnRule> getByRuleIdAndType(Integer ruleId, Integer batteryType) {
        QueryWrapper<TbWarnRule> query = new QueryWrapper<>();
        query.eq("rule_id", ruleId)
                .eq("battery_type", batteryType);
        return warnRuleMapper.selectList(query);
    }
}




