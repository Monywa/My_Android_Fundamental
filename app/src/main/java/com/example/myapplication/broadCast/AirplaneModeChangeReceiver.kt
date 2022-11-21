package com.example.myapplication.broadCast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeChangeReceiver : BroadcastReceiver() {
    /*
    broadCast by system is sent into intent which app want to use
    manifest receiver needed
     */


    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirPlaneModeEnabled=intent?.getBooleanExtra("state",false) ?: return

        if(isAirPlaneModeEnabled){
            Toast.makeText(context,"The airplane is enabled",Toast.LENGTH_SHORT).show()
        }else  Toast.makeText(context,"The airplane is disabled",Toast.LENGTH_SHORT).show()
    }
}