package com.example.everydayexamdemo.designmode.chapter_14_iterator

class CompanyHui : Company<Employee>{
    private val array : Array<Employee> = arrayOf(
            Employee("辉哥", 35, "男", "程序员"),
            Employee("小红", 22, "女", "产品精力")
    )

    override fun iterator(): Iterator<Employee> {
        return HuiIterator(array)
    }
}

class HuiIterator(val array : Array<Employee>) : Iterator<Employee>{
    var index = 0
    override fun hasNext(): Boolean {
        return index < array.size
    }

    override fun next(): Employee {
        return array[index++]
    }

}