package com.example.everydayexamdemo.designmode.chapter_13_memento

class Caretaker() {
    val memotos = arrayListOf<Memoto>()
    // 记录当前存储记录的下标，用于撤销记录和获取下个记录
    var index = 0

    companion object{
        const val MAX = 30
    }


    fun saveMemoto(memoto: Memoto){
        if (memotos.size > MAX){
            memotos.removeAt(0)
        }
        memotos.add(memoto)
        index = memotos.size - 1
    }

    fun restoreMemoto() : Memoto{
        val index = if(this.index > 0) --this.index else this.index
        return memotos[index]
    }

    // 获取上一条记录，相当于撤销
    fun getPrevMemoto() : Memoto{
        val index = if(this.index > 0) --this.index else this.index
        return memotos[index]
    }
    // 获取下一条记录，相当于重做
    fun getNextMemoto() : Memoto{
        val i = if(index < memotos.size - 1) ++index else index
        return memotos[i]
    }
}