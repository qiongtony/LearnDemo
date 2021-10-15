package com.example.everydayexamdemo.designmode.chapter_19_combination.transparent

import java.lang.UnsupportedOperationException

class Leaf(name : String) :Component(name){
    override fun doSomething() {
        println(name)
    }

    override fun addChild(child: Component) {
        throw UnsupportedOperationException("叶子节点没有子节点")
    }

    override fun removeChild(child: Component) {
        throw UnsupportedOperationException("叶子节点没有子节点")
    }

    override fun getChildren(index: Int): Component {
        throw UnsupportedOperationException("叶子节点没有子节点")
    }

}