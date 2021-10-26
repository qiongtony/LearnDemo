package com.example.everydayexamdemo.designmode.chapter_24_bridge

class Sugar : CoffeeAdditives{
    override fun addSomething()  : String{
        return "加糖"
    }

    override fun toString(): String {
        return "加糖"
    }
}