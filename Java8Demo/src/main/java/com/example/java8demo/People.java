package com.example.java8demo;

/**
 * java8支持接口内默认方法实现和静态方法---接口更像类了
 * 默认方法实现是为了应对修改接口时造成不需要使用到新增方法的实现类也需要改动的问题
 */
public interface People {
    default void eat(){
        System.out.println("人吃饭");
    }

    static void jump(){
        System.out.println("人跳了一下");
    }
}
