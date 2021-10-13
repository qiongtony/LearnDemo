package com.example.everydayexamdemo.designmode.chapter_14_iterator

interface Iterator<T> {
    /**
     * 是否有下一个元素
     */
    fun hasNext (): Boolean

    /**
     * 返回当前位置元素，并将位置移至下一位
     */
    fun next() : T
}