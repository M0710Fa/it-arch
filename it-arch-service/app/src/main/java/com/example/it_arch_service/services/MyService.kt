package com.example.it_arch_service.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private val binder = object : MyAidlService.Stub(){
        override fun sum(a: Int, b: Int): Int {
            Log.d("aaa", "binder")
            return a + b
        }
    }
}