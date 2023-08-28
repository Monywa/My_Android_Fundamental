package com.example.myapplication.broadCast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings


class AirplaneModeChangeReceiver : BroadcastReceiver() {
    /*
    broadCast by system is sent into intent which app want to use
    manifest receiver needed
     */


    override fun onReceive(context: Context?, intent: Intent?) {

//        val isAirPlaneModeEnabled=intent?.getBooleanExtra("state",false) ?: return
//        if(isAirPlaneModeEnabled){
//            Toast.makeText(context,"The airplane is enabled",Toast.LENGTH_SHORT).show()
//        }else  Toast.makeText(context,"The airplane is disabled",Toast.LENGTH_SHORT).show()

        if(intent?.action==Intent.ACTION_AIRPLANE_MODE_CHANGED){
            val isTurnedOn= Settings.Global.getInt(
                context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON
            ) !=0
            println("Is airplane mode enabled? $isTurnedOn")
        }


    }
}