package com.example.everydayexamdemo.designmode.chapter_24_bridge

class LargeCoffee(coffeeAdditives: CoffeeAdditives) : Coffee(coffeeAdditives) {
    override fun makeCoffee() {
        println("大杯的${coffeeAdditives}咖啡")
    }
}