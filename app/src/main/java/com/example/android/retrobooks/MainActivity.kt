package com.example.android.retrobooks

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = BookAdapter(this)

        supportActionBar?.title = "Retro Books"

        newRequest()

    }

    fun newRequest() {
        val request = NetworkRequest()
        val call: Call<NetworkResult> = request.bookService.requestBooks()

        call.enqueue(object : Callback<NetworkResult> {

            override fun onFailure(call: Call<NetworkResult>, t: Throwable) {

                Log.d("error", t.localizedMessage)

            }

            override fun onResponse(call: Call<NetworkResult>, response: Response<NetworkResult>) {
                loading.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                response.body()?.results?.books?.map { it.title }
                    ?.let { (recyclerView.adapter as BookAdapter).updateBooks(it) }


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
                recyclerView.visibility = View.GONE
                newRequest()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}


