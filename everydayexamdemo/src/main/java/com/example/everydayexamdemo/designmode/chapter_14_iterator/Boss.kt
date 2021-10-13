package com.example.everydayexamdemo.designmode.chapter_14_iterator

fun main() {
    fun check(iterator: Iterator<Employee>){
        while (iterator.hasNext()){
            System.out.println(iterator.next())
        }
    }
    val companyMin = CompanyMin()
    println("输出companyMin")
    check(companyMin.iterator())
    println("输出companyHui")
    val companyHui = CompanyHui()
    check(companyHui.iterator())
}