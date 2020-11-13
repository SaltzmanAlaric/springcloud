package com.study.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义公共字段处理器(配合tabf)
 */
@Slf4j
@Component
public class ProviderSolarMetaObjectHandler implements MetaObjectHandler {

    /**
     *     @TableField(value = "cjsj", fill = FieldFill.INSERT)
     */
    private static final String CREATE_TIME = "cjsj";
    /**
     *     @TableField(value = "cjr", fill = FieldFill.INSERT)
     */
    private static final String CREATE_USER = "cjr";
    /**
     *     @TableField(value = "zhxgsj", fill = FieldFill.INSERT_UPDATE)
     */
    private static final String UPDATE_TIME = "zhxgsj";
    /**
     *     @TableField(value = "zhxgr", fill = FieldFill.INSERT_UPDATE)
     */
    private static final String UPDATE_USER = "zhxgr";

    /**
     * 插入操作自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        Date now = new Date();
        this.setFieldValByName(CREATE_TIME, now, metaObject);
        Object createUserObj = this.getFieldValByName(CREATE_USER, metaObject);
        this.preUpdate(metaObject, now);
        if (null != createUserObj) {
            this.setFieldValByName(UPDATE_USER, createUserObj, metaObject);
        }

    }

    /**
     * 更新操作自动填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        Date now = new Date();
        Object updateTimeObj = this.getFieldValByName(UPDATE_TIME, metaObject);
        if (null == updateTimeObj) {
            this.preUpdate(metaObject, now);
        }
    }

    /**
     * 封装更新的统一处理
     */
    private void preUpdate(MetaObject metaObject, Date now) {
        // 强制维护时间
        this.setFieldValByName(UPDATE_TIME, now, metaObject);
    }
}