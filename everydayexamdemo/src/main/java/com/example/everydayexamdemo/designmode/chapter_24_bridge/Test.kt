package com.example.everydayexamdemo.designmode.chapter_24_bridge

fun main() {
    val ordinary = Ordinary()
    val sugar = Sugar()


    val largeCoffee = LargeCoffee(ordinary)
    largeCoffee.makeCoffee()

    val smallCoffee = SmallCoffee(ordinary)
    smallCoffee.makeCoffee()

    val largeSugarCoffee = LargeCoffee(sugar)
    largeSugarCoffee.makeCoffee()

    val smallSugarCoffee = SmallCoffee(sugar)
    smallSugarCoffee.makeCoffee()
}