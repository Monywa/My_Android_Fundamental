package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult

class ImplictActivity : AppCompatActivity() {


    val imagView by lazy {
        findViewById<ImageView>(R.id.implicit_image)
    }

    val btn by lazy {
        findViewById<Button>(R.id.uploadBtn)
    }
    val btn2 by lazy {
        findViewById<Button>(R.id.uploadBtn2)
    }

    val startForResult = registerForActivityResult(GetContent()) {
        val uri = it
        imagView.setImageURI(uri)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.implict_image)

        btn.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, 0)
            }
        }
        btn2.setOnClickListener {
            startForResult.launch("image/*")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            val uri = data?.data
            imagView.setImageURI(uri)
        }
    }
}