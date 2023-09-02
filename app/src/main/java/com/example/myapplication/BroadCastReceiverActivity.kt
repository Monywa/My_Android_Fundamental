package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.broadCast.AirplaneModeChangeReceiver
import com.example.myapplication.broadCast.TestReceiver

class BroadCastReceiverActivity : AppCompatActivity() {
    //Starts Dynamic Broadcast Receiver
    lateinit var receiver :AirplaneModeChangeReceiver

    //custom broadcast receiver (send from other )
    //In this case, I wrote both send and receive
    //Starts Dynamic Broadcast Receiver
     val testReceiver: TestReceiver=TestReceiver()


    private val btnSendBroadCast: Button by lazy {
        findViewById(R.id.btn_send_broadcast)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_reciver)

        receiver=AirplaneModeChangeReceiver()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver,it)
        }
        registerReceiver(
            testReceiver,
            IntentFilter("TEST_ACTION")
        )


        //Custom send broadcast
        btnSendBroadCast.setOnClickListener {
            sendBroadcast(
                Intent("TEST_ACTION")
            )
        }

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
        unregisterReceiver(testReceiver)
    }
}