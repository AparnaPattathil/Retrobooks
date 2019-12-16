package com.example.android.retrobooks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookAdapter(private val context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    private val booksList: MutableList<String> = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return booksList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = booksList.get(position)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, booksList.get(position), Toast.LENGTH_LONG).show()
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.title
    }

    fun updateBooks(books: List<String>) {
        booksList.clear()
        booksList.addAll(books)
        notifyDataSetChanged()
    }
}