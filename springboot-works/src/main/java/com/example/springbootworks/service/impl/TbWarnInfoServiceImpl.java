package com.example.springbootworks.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.domain.TbVehicleInfo;
import com.example.springbootworks.domain.TbWarnInfo;
import com.example.springbootworks.domain.TbWarnRule;
import com.example.springbootworks.dto.WarnRequest;
import com.example.springbootworks.inter.RuleFactory;
import com.example.springbootworks.inter.RuleParser;
import com.example.springbootworks.service.TbVehicleInfoService;
import com.example.springbootworks.service.TbWarnInfoService;
import com.example.springbootworks.mapper.TbWarnInfoMapper;
import com.example.springbootworks.service.TbWarnRuleService;
import com.example.springbootworks.vo.WarnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【tb_warn_info(预警信息表)】的数据库操作Service实现
* @createDate 2025-05-18 07:01:03
*/
@Service
public class TbWarnInfoServiceImpl extends ServiceImpl<TbWarnInfoMapper, TbWarnInfo>
    implements TbWarnInfoService{
    @Autowired
    private TbVehicleInfoService vehicleService;
    @Autowired
    private TbWarnRuleService ruleService;

    @Override
    public List<WarnResult> checkWarnings(List<WarnRequest> requests) {
        return requests.stream()
                .map(this::checkSingleRequest)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<WarnResult> checkSingleRequest(WarnRequest request) {
        List<WarnResult> results = new ArrayList<>();
        TbVehicleInfo vehicle = vehicleService.getByCarId(request.getCarId());
        // 解析信号数据
        TbSignal signal = parseSignal(request.getSignal());
        // 获取规则列表
        List<TbWarnRule> rules;
        //  指定规则id则根据匹配的规则进行判断
        if (request.getWarnId() != null) {
            rules = ruleService.getByRuleIdAndType(request.getWarnId(), vehicle.getBatteryType());
        } else {
            rules = ruleService.getRulesByType(vehicle.getBatteryType());
        }
        // 计算预警等级
        for (TbWarnRule rule : rules) {
            RuleParser parser = RuleFactory.getParser(rule.getRuleType());
            int level = parser.calculateLevel(signal, rule);

            if (level >= 0) {
                // 封装VO返回
                WarnResult result = new WarnResult();
                result.setCarId(request.getCarId());
                result.setBatteryType(getBatteryTypeName(vehicle.getBatteryType()));
                result.setWarnName(rule.getRuleName());
                result.setWarnLevel(level);
                results.add(result);
            }
        }
        return results;
    }

    private TbSignal parseSignal(String signalJson) {
        return JSON.parseObject(signalJson, TbSignal.class);
    }

    private String getBatteryTypeName(Integer type) {
        return type == 1 ? "三元电池" : "铁锂电池";
    }

}




