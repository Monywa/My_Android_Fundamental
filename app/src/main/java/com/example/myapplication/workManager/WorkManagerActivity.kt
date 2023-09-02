package com.example.myapplication.workManager

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.myapplication.R
import java.util.UUID

class WorkManagerActivity : AppCompatActivity() {

    private var unCompressedUri:Uri?=null

    private var compressedBitmap:Bitmap?=null

    private var workId:UUID?=null

    lateinit var workManager:WorkManager

    val unCompressedPhoto:ImageView by lazy{
        findViewById(R.id.unCompressed_photo)
    }
    val compressedPhoto:ImageView by lazy{
        findViewById(R.id.compressed_photo)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        Log.d("WorkManagerActivity","onCreate:")
        workManager=WorkManager.getInstance(applicationContext)


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("WorkManagerActivity","OnNewIntent:")

        val uri= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
        }?: return

        unCompressedUri=uri

        Log.d("WorkManagerActivity","OnNewIntent:$uri")

        val request= OneTimeWorkRequestBuilder<PhotoCompressionWorker>()
            .setInputData(
                workDataOf(
                    PhotoCompressionWorker.KEY_CONTENT_URI to uri.toString(),
                    PhotoCompressionWorker.KEY_COMPRESSION_THRESHOLD to 1024 * 20L //20kb
                )
            ).build()
        workId=request.id
        workManager.enqueue(request)

        unCompressedPhoto.setImageURI(unCompressedUri)

        workId?.let {
           workManager.getWorkInfoByIdLiveData(it).observe(this){workInfo->

                val filePath=workInfo.outputData.getString(PhotoCompressionWorker.KEY_RESULT_PATH)

                filePath?.let {
                    val bitMap=BitmapFactory.decodeFile(filePath)
                    compressedPhoto.setImageBitmap(bitMap)
                }
            }


        }
    }
}