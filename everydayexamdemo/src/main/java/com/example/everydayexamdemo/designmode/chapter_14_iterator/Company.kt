package com.example.everydayexamdemo.designmode.chapter_14_iterator

interface Company<T> {
    fun iterator() : Iterator<T>
}