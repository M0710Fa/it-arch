package com.example.it_arch_service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    private val binder = object : IMyAidlInterface.Stub(){
        override fun sum(str: String?): String {
            var cnt = 0
            if(!str.isNullOrEmpty()) {
                cnt = str.filter { it.toString() == " " }.count() + 1
            }
            return cnt.toString()
        }
    }
}