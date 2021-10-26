package com.example.everydayexamdemo.designmode.chapter_24_bridge

class SmallCoffee(coffeeAdditives: CoffeeAdditives) : Coffee(coffeeAdditives) {
    override fun makeCoffee() {
        println("小杯的${coffeeAdditives}咖啡")
    }
}