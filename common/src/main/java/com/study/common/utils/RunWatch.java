package com.study.common.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 程序运行时长统计.
 */
@Slf4j(topic = "【程序运行时长统计】")
public class RunWatch {

    private static long date = System.currentTimeMillis();

    private static boolean isRun = false;

    /**
     * 启动 计时开始
     */
    public static void start() {
        isRun = true;

        date = System.currentTimeMillis();
    }

    /**
     * 中止 计时暂停
     */
    public static void suspend() {
        tikTok(null, false);
    }

    /**
     * 中止 计时暂停
     */
    public static void suspend(String message) {
        tikTok(message, false);
    }


    /**
     * 停止 计时结束
     */
    public static void stop() {
        tikTok(null, true);
    }

    /**
     * 停止 计时结束
     */
    public static void stop(String message) {
        tikTok(message, true);
    }

    /**
     * 计时统计.
     *
     * @param message 运行程序描述
     * @param isStop 是否停止计时
     */
    private static void tikTok(String message, boolean isStop) {

        long time = System.currentTimeMillis() - date;

        if (!isRun) {
            return;
        }

        log.info("{}耗时：{}ms", StrUtil.isBlank(message) ? "" : message, time);

        isRun = !isStop;
    }

}
