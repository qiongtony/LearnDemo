package com.example.java8demo;

public class MyClass {
    public static void main(String[] args){
        Test test = new Test();
        // 执行是Man.eat(),同时实现父接口和子接口，子接口的default方法生效
        test.eat();
    }

    static class Test implements Man, People{

        public Test(){

        }

        @Override
        public void say() {

        }
    }

    // 接口，同时继承子接口和父接口，父接口的default方法被覆盖
    interface Boy extends Man, People{
    }
}