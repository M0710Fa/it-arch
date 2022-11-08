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
            return "fdsaf"
        }
    }
}