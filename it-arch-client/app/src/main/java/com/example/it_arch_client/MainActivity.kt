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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16. dp),
                    color = MaterialTheme.colors.background
                ) {

                    var cnt by remember {
                        mutableStateOf("0")
                    }

                    MainScreen(
                        countWords = {
                            cnt = countWords(it)
                                        },
                        words = cnt
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(iMyAidlInterface == null){
            val intent = Intent("MyService")
            intent.setPackage("com.example.it_arch_service")
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
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

    private fun countWords(str: String): String{
        var result = "0"
        try {
            result = iMyAidlInterface?.sum(str).toString()
            Log.i("aaaaaaaaaaaaaaaaaaa", result)
        }catch (e: RemoteException){
            e.printStackTrace()
            result = "Error"
        }
        return result
    }
}