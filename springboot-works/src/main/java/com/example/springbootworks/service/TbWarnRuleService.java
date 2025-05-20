package com.example.springbootworks.service;

import com.example.springbootworks.domain.TbWarnRule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_warn_rule(预警规则表)】的数据库操作Service
* @createDate 2025-05-18 07:01:03
*/
public interface TbWarnRuleService extends IService<TbWarnRule> {

    List<TbWarnRule> getRulesByType(Integer batteryType);

    List<TbWarnRule> getByRuleIdAndType(Integer warnId, Integer batteryType);
}
