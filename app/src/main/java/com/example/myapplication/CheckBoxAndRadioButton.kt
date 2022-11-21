package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class CheckBoxAndRadioButton : AppCompatActivity() {

    val rdGroup by lazy{
        findViewById<RadioGroup>(R.id.radioGroup)
    }
    val cbSalad by lazy{
        findViewById<CheckBox>(R.id.chSalad)
    }

    val chOnion by lazy{
        findViewById<CheckBox>(R.id.chOnion)
    }
    val cbCheese by lazy{
        findViewById<CheckBox>(R.id.chCheese)
    }

    val btnOrder by lazy {
        findViewById<Button>(R.id.btnOrder)
    }
    val orderText by lazy {
        findViewById<TextView>(R.id.txtOrder)
    }
    val btnGoBack by lazy {
        findViewById<Button>(R.id.btnGoBack)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkbox_and_radio_button)

        btnOrder.setOnClickListener {
            val rdMeatChecked=rdGroup.checkedRadioButtonId
            val meat=findViewById<RadioButton>(rdMeatChecked)
            val onion=chOnion.isChecked
            val cheese=cbCheese.isChecked
            val salad=cbSalad.isChecked

            val orderString="You order a burger with \n"+
                    "${meat?.text}"+
                    (if(onion) " \n Onion" else "")+
                    (if(cheese) "\n Cheese" else "")+
                    (if(salad) " \n Salad" else "")

            orderText.text=orderString

//            Toast.makeText(applicationContext,"Sucess order",Toast.LENGTH_LONG).show()
            Toast(this).apply {
                duration=Toast.LENGTH_SHORT
                view=layoutInflater.inflate(R.layout.custom_toast,findViewById(R.id.clToast))
                show()
            }
        }

        btnGoBack.setOnClickListener {
            finish()
        }

    }
}