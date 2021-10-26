package com.example.everydayexamdemo.designmode.chapter_23_facade

class PhoneImpl : Phone {
    override fun dail() {
        println("打电话")
    }

    override fun hangup() {
        println("挂断")
    }
}