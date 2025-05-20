package com.example.springbootworks.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.mapper.TbSignalMapper;
import com.example.springbootworks.utils.DynamicTableNameUtil;
import com.example.springbootworks.utils.RocketMqUtils;
import com.example.springbootworks.vo.WarnMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Component
public class ScheduledJob {
    @Resource
    private TbSignalMapper signalMapper;

    @Resource
    private RocketMqUtils rocketMqUtils;
    // 定时任务,扫描预警
    @Scheduled(fixedRate = 60_000) // 每60秒执行一次
    public void scanSignalForWarn() {
        System.out.println("开始扫描预警");
        try {
            String tableName = "tb_signal_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
            DynamicTableNameUtil.setTableName(tableName);

            List<TbSignal> signals = signalMapper.selectList(new QueryWrapper<TbSignal>()
                    .eq("processed", 0));
            if (CollectionUtils.isEmpty(signals)) {
                System.out.println("没有需要处理的数据");
            }
            // 发送MQ消息
            signals.forEach(signal -> {
                WarnMessage message = new WarnMessage();
                message.setId(signal.getId());
                message.setCarId(signal.getCarId());
                message.setMx(signal.getMx());
                message.setMi(signal.getMi());
                message.setIx(signal.getIx());
                message.setIi(signal.getIi());
                try {
                    rocketMqUtils.send("warn_topic", message);
                    System.out.println("发送MQ消息成功:");
                    System.out.println(message);
                } catch (Exception e) {
                    System.out.println("发送MQ消息失败");
                    throw new RuntimeException(e);
                }
            });
        } finally {
            DynamicTableNameUtil.clear();
        }
        System.out.println("扫描预警结束");
    }
}
