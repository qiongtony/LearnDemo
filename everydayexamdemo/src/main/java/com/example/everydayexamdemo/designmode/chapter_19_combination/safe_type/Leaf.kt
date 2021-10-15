package com.example.everydayexamdemo.designmode.chapter_19_combination.safe_type

class Leaf(name: String) : Component(name) {
    override fun doSomething() {
        println(name)
    }
}