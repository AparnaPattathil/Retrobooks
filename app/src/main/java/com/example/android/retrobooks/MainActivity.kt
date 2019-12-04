package com.example.android.retrobooks

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.linearLayout)

        click_me_button.setOnClickListener {
            Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show()
            if(loading.visibility == View.GONE)
                loading.visibility =   View.VISIBLE
            else
                loading.visibility =  View.GONE

val request = NetworkRequest()
            val call: Call<NetworkResult> = request.bookService.requestBooks()

            Log.d("call", call.toString())
            call.enqueue(object : Callback<NetworkResult>{
                override fun onFailure(call: Call<NetworkResult>, t: Throwable) {
                    loading.visibility =  View.GONE

                }

                override fun onResponse(call: Call<NetworkResult>, response: Response<NetworkResult>) {
                    loading.visibility =  View.GONE

                }

            })
        }
    }

//

    private fun addTextView(label: String) {
        val view = TextView(this)
        view.text = label
        view.textSize = 28f

        view.setTextColor(Color.parseColor("#ffaa00"))
        linearLayout.addView(view)
    }
}
