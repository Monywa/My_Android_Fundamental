package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class SharedPreferenceActivity : AppCompatActivity() {
    val edName by lazy {
        findViewById<EditText>(R.id.et_name)
    }
    val edAge by lazy {
        findViewById<EditText>(R.id.et_age)
    }
    val cbIsAdult by lazy {
        findViewById<CheckBox>(R.id.cb_is_adult)
    }

    val btnSave by lazy {
        findViewById<Button>(R.id.btn_save)
    }
    val btnUpload by lazy {
        findViewById<Button>(R.id.btn_upload)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        val shareRef=getSharedPreferences("myRef", MODE_PRIVATE)

        val editor=shareRef.edit()



        btnSave.setOnClickListener {

            editor.apply {
                putString("name",edName.text.toString())
                putInt("age",edAge.text.toString().toInt())
                putBoolean("isAdult",cbIsAdult.isChecked)
            }.apply() // override but commit () can also used but  for synchronized
         }
        btnUpload.setOnClickListener {
            val name = shareRef.getString("name",null)
            val age=shareRef.getInt("age",0)
            val isAdult=shareRef.getBoolean("isAdult",false)

            edName.setText(name)
            edAge.setText(age.toString())
            cbIsAdult.isChecked=isAdult
        }
    }
}