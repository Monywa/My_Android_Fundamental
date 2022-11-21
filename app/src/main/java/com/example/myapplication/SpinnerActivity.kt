package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class SpinnerActivity : AppCompatActivity() {

    val spMonths by lazy {
        findViewById<Spinner>(R.id.spinner_months)
    }
    val spGrade by lazy {
        findViewById<Spinner>(R.id.spinner_grade)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        val customList= listOf<String>("First","Second","Third","Fourth","Fifth")

        val adapter=ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,customList)

        spGrade.adapter=adapter

        spMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapter: AdapterView<*>?,
                view: View?,
                positon: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@SpinnerActivity,
                    "You chose ${adapter?.selectedItem}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


    }
}