package com.example.it_arch_client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.it_arch_client.ui.main.MainScreen
import com.example.it_arch_client.ui.theme.ItarchclientTheme
import com.example.it_arch_service.IMyAidlInterface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ItarchclientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(
                        launchService = {
                            var result = "aaa"
                            try {
                                result = iMyAidlInterface?.sum("fd").toString()
                                Log.i("aaaaaaaaaaaaaaaaaaa", result)
                            }catch (e: RemoteException){
                                e.printStackTrace()
                                result = "Error"
                            }}
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent("MyService")
        intent.setPackage("com.example.it_arch_service")
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

    var iMyAidlInterface : IMyAidlInterface? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, binder: IBinder) {
            iMyAidlInterface   = IMyAidlInterface.Stub.asInterface(binder)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.e("ClientApplication", "サービスに接続できませんでした")
            iMyAidlInterface   = null
        }
    }

    private fun launchService(){
        var result = "aaa"
        try {
            result = iMyAidlInterface?.sum("fdsa").toString()
            Log.i("aaaaaaaaaaaaaaaaaaa", result)
        }catch (e: RemoteException){
            e.printStackTrace()
            result = "Error"
        }
    }
}