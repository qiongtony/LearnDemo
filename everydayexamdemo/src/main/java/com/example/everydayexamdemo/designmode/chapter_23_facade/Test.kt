package com.example.everydayexamdemo.designmode.chapter_23_facade

/**
 * 外观模式
 * 用一个类来对外提供子系统的入口
 * 好处：
 * 1、调用方不用关注调用细节；
 * 2、对外隐藏子系统的实现细节；
 * 缺点：
 * 高级类拥有子系统的接口，当子系统接口改变时也需要更新，可能会导致子系统修改引起外部类修改，不符合开闭原则
 * 增加了外部类
 *
 * 举例：
 * 我们经常用的XxxManager管理类、第三方库使用的类
 */
fun main() {
    val mobilePhone = MobilePhone()
    mobilePhone.takePicture()

    mobilePhone.videoChat()

}