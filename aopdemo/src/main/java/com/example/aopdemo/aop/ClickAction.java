package com.example.aopdemo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 针对方法
@Target(ElementType.METHOD)
// 运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickAction {
    // 名称
    String name();
}
