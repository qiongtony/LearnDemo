package com.example.everydayexamdemo.designmode.chapter_19_combination.safe_type

import com.example.everydayexamdemo.designmode.chapter_19_combination.safe_type.Compostie
import com.example.everydayexamdemo.designmode.chapter_19_combination.safe_type.Leaf

/**
 * 安全的组合模式
 * 特点：
 * 抽象根节点没有具体的实现，而是由枝干节点增加添加、删除、获取操作
 * 缺点：
 * 不符合依赖倒置原则，抽象根接节点Component很鸡肋，与之想反，透明的组合模式，则是将枝干节点的这些功能放到了抽象根节点
 */
fun main() {
    val root = Compostie("Root")

    val branch1 = Compostie("Branch1")
    val branch2 = Compostie("Branch2")

    val leaf1 = Leaf("Leaf1")
    val leaf2 = Leaf("Leaf2")

    branch1.addChild(leaf1)
    branch2.addChild(leaf2)

    root.addChild(branch1)
    root.addChild(branch2)

    root.doSomething()
}