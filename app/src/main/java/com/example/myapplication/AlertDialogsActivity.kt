package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class AlertDialogsActivity : AppCompatActivity() {

    val btn1 by lazy {
        findViewById<Button>(R.id.button1)
    }
    val btn2 by lazy {
        findViewById<Button>(R.id.button2)
    }
    val btn3 by lazy {
        findViewById<Button>(R.id.button3)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialogs)

        val alertDialog=AlertDialog.Builder(this)
            .setTitle("Add Contract")
            .setMessage("Do you want to add Mr.Poop to your contracts list")
            .setIcon(R.drawable.ic_add_contract)
            .setPositiveButton("Yes"){_,_->
                Toast.makeText(this,"You added it ",Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){_,_->
                Toast.makeText(this,"You canceled it ",Toast.LENGTH_SHORT).show()
            }
            .create()

        val options= arrayOf("First Item","Second Item","Third Item")

        val singleChoiceDialog=AlertDialog.Builder(this)
            .setTitle("Choose one of three items")
            .setSingleChoiceItems(options,0){_,i->
                Toast.makeText(this,"You chose ${options[i]} ",Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Yes"){_,_->
                Toast.makeText(this,"You added it ",Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){_,_->
                Toast.makeText(this,"You canceled it ",Toast.LENGTH_SHORT).show()
            }
            .create()

        val multiChoiceDialog=AlertDialog.Builder(this)
            .setTitle("Please Choose wisely")
            .setMultiChoiceItems(options, booleanArrayOf(false,false,false)){ _, i,isChecked ->
                if(isChecked){
                    Toast.makeText(this,"You chose ${options[i]} ",Toast.LENGTH_SHORT).show()
                }else
                    Toast.makeText(this,"You canceled ${options[i]} ",Toast.LENGTH_SHORT).show()

            }
            .setPositiveButton("Yes"){_,_->
                Toast.makeText(this,"You added it",Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){_,_->
                Toast.makeText(this,"You canceled it ",Toast.LENGTH_SHORT).show()
            }
            .create()

        btn1.setOnClickListener {
            alertDialog.show()
        }
        btn2.setOnClickListener {
            singleChoiceDialog.show()
        }
        btn3.setOnClickListener {
            multiChoiceDialog.show()
        }
    }
}