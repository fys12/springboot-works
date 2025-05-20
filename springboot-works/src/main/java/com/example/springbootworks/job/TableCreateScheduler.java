package com.example.springbootworks.job;

import com.example.springbootworks.common.SQLTEXT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TableCreateScheduler {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 每月最后一天 23:59:59 触发
    @Scheduled(cron = "0 59 23 L * ?")
    public void createNextMonthTable() {
        // 获取下个月的年月格式（如 202405 → 202406）
        String nextMonth = LocalDateTime.now()
                .plusMonths(1)
                .format(DateTimeFormatter.ofPattern("yyyyMM"));

        String sql = "CREATE TABLE IF NOT EXISTS tb_signal_" + nextMonth + SQLTEXT.SQL_TEXT;
        
        jdbcTemplate.execute(sql);
        System.out.println("创建表 tb_signal_" + nextMonth + " 成功");
    }
}