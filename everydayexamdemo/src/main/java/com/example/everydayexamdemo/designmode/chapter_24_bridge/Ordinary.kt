package com.example.everydayexamdemo.designmode.chapter_24_bridge

class Ordinary : CoffeeAdditives{
    override fun addSomething()  : String{
        return "原味"
    }

    override fun toString(): String {
        return "原味"
    }

}