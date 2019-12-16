package com.example.android.retrobooks

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
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

        val actionBar = supportActionBar
        actionBar!!.title = "Retro Books"

        loading.visibility = View.VISIBLE

        container = findViewById(R.id.linearLayout)
        newRequest()

    }

    fun newRequest() {
        val request = NetworkRequest()
        val call: Call<NetworkResult> = request.bookService.requestBooks()
        loading.visibility = View.VISIBLE

        call.enqueue(object : Callback<NetworkResult> {

            override fun onFailure(call: Call<NetworkResult>, t: Throwable) {

                Log.d("error", t.localizedMessage)

            }

            override fun onResponse(call: Call<NetworkResult>, response: Response<NetworkResult>) {
                loading.visibility =View.GONE
                addTextView("status code ${response.code()}")
                val totalResults = response.body()?.num_results
                addTextView("Total results=${totalResults.toString()}")
                response.body()?.results?.books?.forEach {
                    addTextView("${it.rank} | ${it.title}")
                }

            }


        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                loading.visibility = View.VISIBLE
                newRequest()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addTextView(label: String) {
        val view = TextView(this)
        view.text = label
        view.textSize = 22f

        view.setTextColor(Color.parseColor("#ffaa00"))
        linearLayout.addView(view)
    }
}


