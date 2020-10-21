package com.study.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.UUID;

/**
 * 数字处理工具类.
 */
public class NumberUtils {

    /**
     * 四舍五入 保留小数
     *
     * @param d     小数
     * @param scale 保留个数
     * @return 结果
     */
    public static Double parseDouble(Double d, int scale) {
        return Objects.isNull(d) ? null : parseBigDecimal(new BigDecimal(d), scale);
    }

    /**
     * 四舍五入 保留小数
     *
     * @param scale 保留个数
     * @return 结果
     */
    public static Double parseBigDecimal(BigDecimal bg, int scale) {
        if (null == bg) {
            return null;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return bg.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * @return 随机生成32位uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 小数解析
     */
    public static Double parseDecimal(Object obj, Double defalutValue) {
        Double bhd;
        if (obj instanceof BigDecimal) {
            bhd = ((BigDecimal) obj).doubleValue();
        } else if (obj instanceof Double) {
            bhd = (Double) obj;
        } else if(obj instanceof Integer){
            bhd = 1.0 * ((Integer) obj).intValue();
        } else {
            bhd = defalutValue;
        }
        return bhd;
    }

    /**
     * 计算同比
     */
    public static Double comparePercent(Object currValue, Object preValue) {
        Double curr = 0.0D;
        Double prev = 0.0D;
        if (currValue instanceof Integer) {
            curr = 1.0 * (int) currValue;
        } else if (currValue instanceof Double) {
            curr = (Double) currValue;
        }
        if (preValue instanceof Integer) {
            prev = 1.0 * (int) preValue;
        } else if (preValue instanceof Double) {
            prev = (Double) preValue;
        }
        Double compareValue = 0.0D;
        if (0.0 != prev) {
            compareValue = NumberUtils.parseDouble((curr - prev) / prev * 100, 2);
        }
        return compareValue;
    }

}