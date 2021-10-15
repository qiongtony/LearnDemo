package com.example.everydayexamdemo.designmode.chapter_19_combination.transparent

/**
 * 符合依赖倒置原则
 * 不管是枝干、还是叶子都是Component
 */
fun main() {
    val root = Composite("Root")
    // 可以声明为父类Component
    val branch1 : Component = Composite("Branch1")
    val branch2 : Component = Composite("Branch2")

    val leaf1 : Component = Leaf("Leaf1")
    val leaf2 : Component = Leaf("Leaf2")

    branch1.addChild(leaf1)
    branch2.addChild(leaf2)

    root.addChild(branch1)
    root.addChild(branch2)

    root.doSomething()
}