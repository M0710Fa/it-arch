package com.example.it_arch_service.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService: Service() {
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private val binder = object : MyAidlInterface.Stub(){
        override fun sum(a: Int, b: Int): String {
            return "aaaaa"
        }
    }
}