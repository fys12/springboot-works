package com.example.springbootworks.common;

/**
 * 每月定时任务建立分表语句
 */
public class SQLTEXT {
    public static final String SQL_TEXT = "CREATE TABLE IF NOT EXISTS tb_signal_${yyyyMM} (\n" +
            "  -- 此处结构与原表一致（保持你的字段定义）\n" +
            "  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',\n" +
            "  `car_id` int NOT NULL COMMENT '车架编号',\n" +
            "  `mx` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高电压',\n" +
            "  `mi` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低电压',\n" +
            "  `ix` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高电流',\n" +
            "  `ii` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低电流',\n" +
            "  `signal_time` datetime NOT NULL COMMENT '信号上报时间',\n" +
            "  `processed` tinyint NULL DEFAULT 0 COMMENT '是否已处理预警：0=未处理，1=已处理',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  INDEX `idx_car_id_time`(`car_id` ASC, `signal_time` ASC) USING BTREE\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电池信号表';";
}
