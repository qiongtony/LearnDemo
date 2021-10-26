package com.example.everydayexamdemo.designmode.chapter_23_facade

class MobilePhone {
    private val phone : Phone = PhoneImpl()
    private val camera : Camera = CameraImpl()

    fun dail(){
        phone.dail()
    }

    fun videoChat(){
        println("视频聊天接通中")
        camera.open()
        phone.dail()
    }

    fun hangup(){
        phone.hangup()
    }

    fun takePicture(){
        camera.open()
        camera.takePicture()
    }

    fun closeCamera(){
        camera.close()
    }
}