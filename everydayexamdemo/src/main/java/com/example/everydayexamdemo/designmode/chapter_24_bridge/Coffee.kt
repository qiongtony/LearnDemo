package com.example.everydayexamdemo.designmode.chapter_24_bridge

abstract class Coffee(val coffeeAdditives: CoffeeAdditives) {
    /**
     * 制作咖啡
     */
    abstract fun makeCoffee()
}