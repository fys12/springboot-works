package com.example.springbootworks.mq;

import com.alibaba.fastjson.JSON;
import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.domain.TbVehicleInfo;
import com.example.springbootworks.domain.TbWarnInfo;
import com.example.springbootworks.domain.TbWarnRule;
import com.example.springbootworks.inter.RuleParser;
import com.example.springbootworks.service.TbSignalService;
import com.example.springbootworks.service.TbVehicleInfoService;
import com.example.springbootworks.service.TbWarnInfoService;
import com.example.springbootworks.service.TbWarnRuleService;
import com.example.springbootworks.vo.WarnMessage;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

// MQ消费者
@Component
@RocketMQMessageListener(topic = "warn_topic",consumerGroup = "warn_consumer")
public class WarnConsumer implements RocketMQListener<WarnMessage> {
    @Autowired
    private TbWarnRuleService ruleService;
    @Autowired
    private TbWarnInfoService warnInfoService;

    @Autowired
    private TbSignalService signalService;

    @Autowired
    private TbVehicleInfoService vehicleService;

    @Override
    public void onMessage(WarnMessage message) {
        if (Objects.isNull(message)) {
            System.out.println("消息数据为空");
            return;
        }

        try {
            System.out.println("收到消息：" + message);
            TbSignal msgData = getMsgData(message);
            TbVehicleInfo vehicle = vehicleService.getByCarId(message.getCarId());
            // 检查车辆是否存在
            if (vehicle == null) {
                System.out.println("未找到车辆信息，carId: " + message.getCarId());
                return;
            }
            System.out.println("对车辆" + message.getCarId() + "进行预警检查" );
            List<TbWarnRule> rules = ruleService.getRulesByType(vehicle.getBatteryType());
            if (CollectionUtils.isEmpty(rules)) {
                System.out.println("未找到预警规则，batteryType: " + vehicle.getBatteryType());
                return;
            }
            rules.forEach(rule -> {
                int level = RuleParser.parse(rule, msgData);
                if (level >= 0) {
                    TbWarnInfo warn = new TbWarnInfo();
                    warn.setCarId(message.getCarId());
                    warn.setRuleId(rule.getRuleId());
                    warn.setWarnLevel(level);
                    warn.setSignalData(JSON.toJSONString(msgData));
                    warn.setCreateTime(LocalDateTime.now());
                    warnInfoService.save(warn);
                }
            });
            signalService.markProcessed(msgData.getId());
            System.out.println("处理消息成功"+message);
        } catch (Exception e) {
            System.out.println("处理消息失败"+message);
            throw new RuntimeException(e); // 抛出异常会触发 RocketMQ 重试机制
        }
    }

    // 获取消息数据
    private  TbSignal getMsgData(WarnMessage message) {
        TbSignal signal =  new TbSignal();
        signal.setId(message.getId());
        signal.setMx(message.getMx());
        signal.setMi(message.getMi());
        signal.setIx(message.getIx());
        signal.setIi(message.getIi());
        signal.setCarId(message.getCarId());
        return signal;
    }
}