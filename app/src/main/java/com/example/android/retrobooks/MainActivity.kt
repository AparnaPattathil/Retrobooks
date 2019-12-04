package com.example.android.retrobooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        click_me_button.setOnClickListener {
            Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show()
            if(loading.visibility == View.GONE){
                loading.visibility =   View.VISIBLE
            }else{
                loading.visibility =  View.GONE
            }
        }
    }
}
