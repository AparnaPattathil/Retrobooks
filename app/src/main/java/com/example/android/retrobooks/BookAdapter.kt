package com.example.android.retrobooks
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookAdapter(private val context: MainActivity,private val booksList: ArrayList<String>): RecyclerView.Adapter<BookAdapter.ViewHolder>() {


//     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//         val title = itemView.findViewById<TextView>(R.id.title)
//
//     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false))
     }

     override fun getItemCount(): Int {
         return booksList.size

     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         Log.d("holder", holder.title.toString())
         holder.title?.text = booksList.get(position)
//         holder.title.text = booksList.get(position)
         holder.itemView.setOnClickListener {
//             Toast.makeText(context, booksList.get(position), Toast.LENGTH_LONG).show()
         }

     }
     class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val title = view.title
     }
 }