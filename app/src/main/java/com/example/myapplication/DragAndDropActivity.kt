package com.example.myapplication

import android.content.ClipData
import android.content.ClipDescription
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast

class DragAndDropActivity : AppCompatActivity() {

    val llTop by lazy {
        findViewById<LinearLayout>(R.id.ll_top)
    }
    val llBottom by lazy {
        findViewById<LinearLayout>(R.id.ll_bottom)
    }

    val dragView by lazy {
        findViewById<View>(R.id.drag_item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop)




        /*
        * for drag,
        * 1. clipData
        * 2. shadow
        * 3. invisibility

         */
        dragView.setOnLongClickListener {
            val clipText = "This is our clipData text"
            val clipItem = ClipData.Item(clipText) // clip show the date where u came from
            val mimeType =
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN) // mimeType is a type of data or file ending

            val data = ClipData(clipText, mimeType, clipItem)

            /*
            for shadow when drag
             */
            val dragShadow = DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadow, it, 0)

            it.visibility = View.INVISIBLE
            true
        }

        val dragListener = View.OnDragListener { view, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
                    view.invalidate()//update view
                    true
                }// when the drag view enter layout boundaries

                DragEvent.ACTION_DRAG_LOCATION -> true

                DragEvent.ACTION_DRAG_EXITED -> {
                    view.invalidate()
                    true
                } // when the drag view leave layout boundaries

                DragEvent.ACTION_DROP -> {
                    val item = event.clipData.getItemAt(0)
                    val dragData = item.text

                    Toast.makeText(this, dragData, Toast.LENGTH_SHORT).show()

                    view.invalidate()
                    /*
                    From precious owner layout,view is removed
                     */
                    val v = event.localState as View
                    val owner = v.parent as ViewGroup
                    owner.removeView(v)

                    /*
                    To new owner layout, view is created

                     */
                    val destinaton = view as LinearLayout //this view is current linear view
                    destinaton.addView(v)

                    dragView.visibility = View.VISIBLE
                    true
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    view.invalidate()
                    true
                }
                else -> false

            }
        }
        llTop.setOnDragListener(dragListener) // from
        llBottom.setOnDragListener(dragListener) // to



    }
}