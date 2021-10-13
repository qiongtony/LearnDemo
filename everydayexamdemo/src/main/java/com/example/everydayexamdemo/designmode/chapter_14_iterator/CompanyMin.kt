package com.example.everydayexamdemo.designmode.chapter_14_iterator

class CompanyMin : Company<Employee>{
    private val list = ArrayList<Employee>()

    init {
        list.add(Employee("小民", 96, "男", "程序员"))
        list.add(Employee("晓芸", 22, "女", "助理"))
        list.add(Employee("小芳", 18, "女", "测试"))
        list.add(Employee("可儿", 25, "女", "设计"))
    }

    override fun iterator(): Iterator<Employee> {
        return CompanyMinIterator(list)
    }

}

class CompanyMinIterator(val list : List<Employee>) : Iterator<Employee>{

    var index = 0

    override fun hasNext(): Boolean {
        return index < list.size
    }

    override fun next(): Employee {
        return list[index++]
    }

}