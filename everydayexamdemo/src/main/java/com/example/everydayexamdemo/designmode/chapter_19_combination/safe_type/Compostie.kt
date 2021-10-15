package com.example.everydayexamdemo.designmode.chapter_19_combination.safe_type

import java.util.ArrayList

/**
 * 枝干节点
 */
class Compostie(name : String, val components : MutableList<Component> = ArrayList<Component>()) : Component(name) {
    override fun doSomething() {
        println(name)
        for (competent in components){
            competent.doSomething()
        }
    }

    fun addChild(child : Component){
        components.add(child)
    }

    fun removeChild(child : Component){
        components.remove(child)
    }

    fun getChildren(index : Int) = components[index]
}