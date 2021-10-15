package com.example.everydayexamdemo.designmode.chapter_19_combination.transparent

class Composite(name : String, val list : ArrayList<Component> = ArrayList<Component>()) :Component(name){
    override fun doSomething() {
        println(name)
        for (competent in list){
            competent.doSomething()
        }
    }

    override fun addChild(child: Component) {
        list.add(child)
    }

    override fun removeChild(child: Component) {
        list.remove(child)
    }

    override fun getChildren(index: Int): Component {
       return list[index]
    }

}