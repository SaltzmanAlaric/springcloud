SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省,城市名称',
  `pid` int(3) NULL DEFAULT NULL COMMENT '父id',
  `sort` int(3) NULL DEFAULT 0 COMMENT '排序,数字越小优先级越大',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, '广东省', 0, 1);
INSERT INTO `city` VALUES (2, '湖南省', 0, 0);
INSERT INTO `city` VALUES (3, '深圳市', 1, 0);
INSERT INTO `city` VALUES (4, '佛山市', 1, 2);
INSERT INTO `city` VALUES (5, '广州市', 1, 1);
INSERT INTO `city` VALUES (6, '宝安区', 3, 0);
INSERT INTO `city` VALUES (7, '罗湖区', 3, 0);
INSERT INTO `city` VALUES (8, '福田区', 3, 0);
INSERT INTO `city` VALUES (9, '南山区', 3, 0);
INSERT INTO `city` VALUES (10, '龙华区', 3, 0);
INSERT INTO `city` VALUES (11, '南海区', 4, 0);
INSERT INTO `city` VALUES (12, '禅城区', 4, 0);
INSERT INTO `city` VALUES (13, '三水区', 4, 0);
INSERT INTO `city` VALUES (14, '高明区', 4, 0);

SET FOREIGN_KEY_CHECKS = 1;
