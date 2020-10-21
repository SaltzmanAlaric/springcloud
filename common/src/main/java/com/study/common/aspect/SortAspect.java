package com.study.common.aspect;

import cn.hutool.core.util.ArrayUtil;
import com.study.common.utils.QueryUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 排序Aspect
 */
@Aspect
@Component
@Slf4j
public class SortAspect {

    /**
     * 方法执行前
     */
    @Before("@annotation(sortAop)")
    public void before(JoinPoint joinPoint, SortAop sortAop) throws Exception {
        // 接收到请求，记录请求内容
        log.trace("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (ArrayUtil.isNotEmpty(args)) {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Parameter[] parameters = method.getParameters();
            int idx = -1;
            for (int i = 0; i < parameters.length; i++) {
                if (sortAop.name().equals(parameters[i].getName())) {
                    idx = i;
                    break;
                }
            }
            Assert.isTrue(idx > -1, "SortAop的name错误");
            // 通过属性获取对象的属性
            Field field = args[idx].getClass().getDeclaredField("sort");
            // 对象的属性的访问权限设置为可访问
            field.setAccessible(true);
            // 获取属性的对应的值
            Sort sort = (Sort) field.get(args[idx]);
            QueryUtils.setField(sort, sortAop.typeClass());
        }
    }

}
