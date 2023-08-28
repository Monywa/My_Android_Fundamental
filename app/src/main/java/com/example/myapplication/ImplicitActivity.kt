package com.example.myapplication

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts.GetContent

class ImplicitActivity : AppCompatActivity() {


    val imagView by lazy {
        findViewById<ImageView>(R.id.implicit_image)
    }

    val btn by lazy {
        findViewById<Button>(R.id.uploadBtn)
    }
    val btn2 by lazy {
        findViewById<Button>(R.id.uploadBtn2)
    }
    val btnToYouTube by lazy {
        findViewById<Button>(R.id.btn_go_to_you_tube)
    }
    val btnSendEmail by lazy {
        findViewById<Button>(R.id.btn_send_email)
    }

    val startForResult = registerForActivityResult(GetContent()) {
        val uri = it
        imagView.setImageURI(uri)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.implict_image)



        Log.d("ImplicitActivity","onCreate:")
//        val imageUri= if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
//        }else intent?.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
//        imageUri?.let {
//            Log.d("ImplicitActivity:"," Image is received : $imageUri")
//        }
//
//        imagView.setImageURI(imageUri)


        btn.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, 0)
            }
        }
        btn2.setOnClickListener {
            startForResult.launch("image/*")
        }

        //explict intent
        btnToYouTube.setOnClickListener {
            //Action to open the main of the desired package
            Intent(Intent.ACTION_MAIN).also {
                it.`package` = "com.google.android.youtube"

//                if(it.resolveActivity(packageManager)){
//                    startActivity(it)
//                } // it get a waring better solution is below try catch
//                (Google prevents and limits that apps can scan for other apps )
                try {
                    startActivity(it)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }

            }
        }

        btnSendEmail.setOnClickListener {
            //implicit
        //action want to send something like email and so on..
            val intent=Intent(Intent.ACTION_SEND).apply {
                type="text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("test@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "This is my subject")
                putExtra(Intent.EXTRA_TEXT,"This is the content of my email")
            }

            //because of the waring from resolveActivity, we need to add queries to manifest
            if(intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            val uri = data?.data
            imagView.setImageURI(uri)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("ImplicitActivity","onNewIntent")
        val imageUri= if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        }else intent?.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
        imageUri?.let {
            Log.d("ImplicitActivity:"," Image is received : $imageUri")
        }

        imagView.setImageURI(imageUri)
    }

    override fun onResume() {
        super.onResume()
        Log.d("ImplicitActivity","onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ImplicitActivity","onStop")
    }
}