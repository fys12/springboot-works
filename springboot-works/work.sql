/*
 Navicat Premium Dump SQL

 Source Server         : .
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : work

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 18/05/2025 13:34:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_signal
-- ----------------------------
DROP TABLE IF EXISTS `tb_signal`;
CREATE TABLE `tb_signal`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `car_id` int NOT NULL COMMENT '车架编号',
  `mx` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高电压',
  `mi` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低电压',
  `ix` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高电流',
  `ii` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低电流',
  `signal_time` datetime NOT NULL COMMENT '信号上报时间',
  `processed` tinyint NULL DEFAULT 0 COMMENT '是否已处理预警：0=未处理，1=已处理',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_car_id_time`(`car_id` ASC, `signal_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '电池信号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_signal
-- ----------------------------
INSERT INTO `tb_signal` VALUES (1, 1, NULL, NULL, NULL, NULL, '2025-05-18 09:59:53', 0);
INSERT INTO `tb_signal` VALUES (2, 12, NULL, NULL, NULL, NULL, '2025-05-18 10:01:43', 0);
INSERT INTO `tb_signal` VALUES (3, 12, NULL, NULL, NULL, NULL, '2025-05-18 10:31:11', 0);
INSERT INTO `tb_signal` VALUES (4, 12, NULL, NULL, NULL, NULL, '2025-05-18 10:34:04', 0);
INSERT INTO `tb_signal` VALUES (5, 12, NULL, NULL, NULL, NULL, '2025-05-18 10:34:19', 0);

-- ----------------------------
-- Table structure for tb_signal_202505
-- ----------------------------
DROP TABLE IF EXISTS `tb_signal_202505`;
CREATE TABLE `tb_signal_202505`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `car_id` int NOT NULL COMMENT '车架编号',
  `mx` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高电压',
  `mi` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低电压',
  `ix` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高电流',
  `ii` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低电流',
  `signal_time` datetime NOT NULL COMMENT '信号上报时间',
  `processed` tinyint NULL DEFAULT 0 COMMENT '是否已处理预警：0=未处理，1=已处理',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_car_id_time`(`car_id` ASC, `signal_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '电池信号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_signal_202505
-- ----------------------------
INSERT INTO `tb_signal_202505` VALUES (2, 12, 12.00, 0.60, 12.00, 11.70, '2025-05-18 10:58:04', 0);

-- ----------------------------
-- Table structure for tb_vehicle_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_vehicle_info`;
CREATE TABLE `tb_vehicle_info`  (
  `vid` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '车辆识别码，16位唯一字符串',
  `car_id` int NOT NULL COMMENT '车架编号',
  `battery_type` tinyint NOT NULL COMMENT '电池类型：1=三元电池，2=铁锂电池',
  `total_mileage` int NULL DEFAULT 0 COMMENT '总里程（km）',
  `battery_health` int NULL DEFAULT 100 COMMENT '电池健康状态（%）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`vid`) USING BTREE,
  INDEX `idx_car_id`(`car_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '车辆信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_vehicle_info
-- ----------------------------
INSERT INTO `tb_vehicle_info` VALUES ('V000000000000001', 1001, 1, 15000, 95, '2025-05-18 08:49:20');
INSERT INTO `tb_vehicle_info` VALUES ('V000000000000002', 1002, 2, 23000, 92, '2025-05-18 08:49:20');
INSERT INTO `tb_vehicle_info` VALUES ('V000000000000003', 1003, 1, 8700, 98, '2025-05-18 08:49:20');

-- ----------------------------
-- Table structure for tb_warn_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_warn_info`;
CREATE TABLE `tb_warn_info`  (
  `warn_id` bigint NOT NULL AUTO_INCREMENT COMMENT '预警ID',
  `car_id` int NOT NULL COMMENT '车架编号',
  `rule_id` int NOT NULL COMMENT '规则编号',
  `warn_level` tinyint NULL DEFAULT NULL COMMENT '预警等级：0=最高',
  `signal_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '触发预警的信号数据',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预警时间',
  PRIMARY KEY (`warn_id`) USING BTREE,
  INDEX `idx_car_id`(`car_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '预警信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_warn_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_warn_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_warn_rule`;
CREATE TABLE `tb_warn_rule`  (
  `rule_id` int NOT NULL COMMENT '规则编号',
  `rule_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '规则名称',
  `battery_type` tinyint NOT NULL COMMENT '电池类型：1=三元电池，2=铁锂电池',
  `rule_type` tinyint NOT NULL COMMENT '规则类型：1=电压差，2=电流差',
  `conditions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '规则条件JSON数组，例：[{\"min\":5,\"max\":null,\"level\":0},{\"min\":3,\"max\":5,\"level\":1}]',
  PRIMARY KEY (`rule_id`, `battery_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '预警规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_warn_rule
-- ----------------------------
INSERT INTO `tb_warn_rule` VALUES (1, '电压差报警', 1, 1, '[\r\n  {\"min\":5.0,\"max\":null,\"level\":0},\r\n  {\"min\":3.0,\"max\":5.0,\"level\":1},\r\n  {\"min\":1.0,\"max\":3.0,\"level\":2},\r\n  {\"min\":0.6,\"max\":1.0,\"level\":3},\r\n  {\"min\":0.2,\"max\":0.6,\"level\":4}\r\n]');
INSERT INTO `tb_warn_rule` VALUES (1, '电压差报警', 2, 1, '[\r\n  {\"min\":2.0,\"max\":null,\"level\":0},\r\n  {\"min\":1.0,\"max\":2.0,\"level\":1},\r\n  {\"min\":0.7,\"max\":1.0,\"level\":2},\r\n  {\"min\":0.4,\"max\":0.7,\"level\":3},\r\n  {\"min\":0.2,\"max\":0.4,\"level\":4}\r\n]');
INSERT INTO `tb_warn_rule` VALUES (2, '电流差报警', 1, 2, '[\r\n  {\"min\":3.0,\"max\":null,\"level\":0},\r\n  {\"min\":1.0,\"max\":3.0,\"level\":1},\r\n  {\"min\":0.2,\"max\":1.0,\"level\":2}\r\n]');
INSERT INTO `tb_warn_rule` VALUES (2, '电流差报警', 2, 2, '[\r\n  {\"min\":1.0,\"max\":null,\"level\":0},\r\n  {\"min\":0.5,\"max\":1.0,\"level\":1},\r\n  {\"min\":0.2,\"max\":0.5,\"level\":2}\r\n]');

SET FOREIGN_KEY_CHECKS = 1;
