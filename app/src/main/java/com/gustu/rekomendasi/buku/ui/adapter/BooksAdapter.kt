package com.gustu.rekomendasi.buku.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gustu.rekomendasi.buku.R
import com.gustu.rekomendasi.buku.model.BooksItem
import com.gustu.rekomendasi.buku.ui.activity.BookDetailActivity
import kotlinx.android.synthetic.main.item_books.view.*


/**Created by Gustu Maulana Firmansyah on 11,September,2021  gustumaulanaf@gmail.com **/
class BooksAdapter(val context: Context, val books: List<BooksItem>) :
    RecyclerView.Adapter<BooksAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_books, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvBookName.text = books[position].bookTitle
        Glide.with(context).load(books[position].imageM).placeholder(R.drawable.book_icon).into(holder.itemView.imgBook)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra("book", books[position])
            intent.putExtra("from","buku")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }
}