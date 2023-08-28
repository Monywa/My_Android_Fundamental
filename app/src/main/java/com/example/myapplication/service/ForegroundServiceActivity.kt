package com.example.myapplication.service

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.example.myapplication.R

class ForegroundServiceActivity : AppCompatActivity() {
    val btnStartService : Button by lazy {
        findViewById(R.id.btn_start_service)
    }

    val btnStopService : Button by lazy {
        findViewById(R.id.btn_stop_service)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.POST_NOTIFICATIONS
                ),0
            )
        }

        btnStartService.setOnClickListener {
            //explicit Intent
            Intent(applicationContext,RunningService::class.java).also {
                it.action=RunningService.Actions.START.toString()
                startService(it)
            }
        }

        btnStopService.setOnClickListener {
            //explicit Intent
            Intent(applicationContext,RunningService::class.java).also {
                it.action=RunningService.Actions.STOP.toString()
                startService(it)
            }
        }


    }
}