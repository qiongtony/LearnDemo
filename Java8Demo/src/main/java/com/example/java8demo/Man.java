package com.example.java8demo;

public interface Man extends People{
    @Override
    default void eat() {
        System.out.println("男人吃饭");
    }

    void say();
}
