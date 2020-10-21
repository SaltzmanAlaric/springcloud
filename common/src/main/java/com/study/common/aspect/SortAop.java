package com.study.common.aspect;

import java.lang.annotation.*;

/**
 * 排序 切面注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface SortAop {

    String name() default "query";

    Class<?> typeClass();

}
