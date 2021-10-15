package com.example.everydayexamdemo.designmode.chapter_19_combination.transparent


/**
 * 抽象根节点
 */
abstract class Component(val name: String) {
    abstract fun doSomething()
    abstract fun addChild(child : Component)
    abstract fun removeChild(child : Component)
    abstract fun getChildren(index : Int) : Component
}