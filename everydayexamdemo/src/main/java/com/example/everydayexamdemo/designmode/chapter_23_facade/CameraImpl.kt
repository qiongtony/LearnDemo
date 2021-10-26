package com.example.everydayexamdemo.designmode.chapter_23_facade

class CameraImpl : Camera {
    override fun open() {
        println("打开相机")
    }

    override fun takePicture() {
        println("拍照")
    }

    override fun close() {
        println("关闭相机")
    }
}