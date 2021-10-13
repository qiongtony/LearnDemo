package com.example.everydayexamdemo.designmode.chapter_11_command

// 命令模式的简要demo，特点请求者与接收者解耦，让一个命令的请求与执行不要耦合在一起

fun main() {
    // 构造一个接收者对象
    val receiver = Receiver()
    // 构造一个命令对象
    val concreteCommand = ConcreteCommand(receiver)
    // 构造请求者
    val invoker = Invoker(concreteCommand)
    // 请求者执行请求方法
    invoker.action()
}

/**
 * 命令接口
 */
interface Command{
    /**
     * 执行具体操作的命令
     */
    fun execute()
}

/**
 * 实际命令逻辑的执行者
 */
class Receiver{
    fun action(){
        System.out.println("执行具体操作")
    }
}

class ConcreteCommand(
        // 持有一个接收者对象
        val receiver: Receiver
) : Command{
    override fun execute() {
        // 调用接收者的相关方法来执行具体逻辑
        receiver.action()
    }
}

class Invoker(val command: Command){
    fun action(){
        command.execute()
    }
}

class Client {
}