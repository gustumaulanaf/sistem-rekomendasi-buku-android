package com.gustu.rekomendasi.buku.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gustu.rekomendasi.buku.R
import com.gustu.rekomendasi.buku.model.Books
import com.gustu.rekomendasi.buku.model.BooksItem
import com.gustu.rekomendasi.buku.model.RekomendasiItem
import com.gustu.rekomendasi.buku.presenter.BookView
import com.gustu.rekomendasi.buku.presenter.BooksPresenter
import com.gustu.rekomendasi.buku.retrofit.RetrofitClient
import com.gustu.rekomendasi.buku.ui.adapter.BookRecommendDetailAdapter
import com.gustu.rekomendasi.buku.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity(), BookView {
    lateinit var presenter: BooksPresenter
    var bookID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        if (intent.getStringExtra("from").equals("rekomendasi")) {
            val booksItem: RekomendasiItem = intent.getParcelableExtra("book")!!
            bookID = booksItem.isbn!!
            Glide.with(applicationContext).load(booksItem.imageL).into(imgBookDetail)
            tvBookTitleDetail.text = booksItem.bookTitle
            tvAuthorDetail.text = booksItem.bookAuthor
            tvPublisherDetail.text = booksItem.publisher
            tvYearDetail.text = booksItem.yearPublication
        } else {
            val booksItem: BooksItem = intent.getParcelableExtra("book")!!
            bookID = booksItem.isbn!!
            Glide.with(applicationContext).load(booksItem.imageL).into(imgBookDetail)
            tvBookTitleDetail.text = booksItem.bookTitle
            tvAuthorDetail.text = booksItem.bookAuthor
            tvPublisherDetail.text = booksItem.publisher
            tvYearDetail.text = booksItem.yearPublication
        }
        initPresenter()
    }

    private fun initPresenter() {
        presenter = BooksPresenter(RetrofitClient(), this)
        presenter.rekomendasi(
            SharedPrefUtil.getString("user_id")!!,
            SharedPrefUtil.getString("lokasi")!!,
            bookID
        )
        progress_detail.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true

    }

    override fun onSuccess(body: Books) {

    }

    override fun onError(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
        progress_detail.visibility = View.GONE
    }

    override fun onSuccess2(body: List<BooksItem>) {

    }

    override fun onSuccess3(body: List<RekomendasiItem>) {
        val array: ArrayList<RekomendasiItem> = ArrayList()
        for (i in body.indices) {
            if (!body[i].isbn.equals(bookID)) {
                array.add(body[i])
            }
        }
        rvRecommendDetail.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvRecommendDetail.setHasFixedSize(false)
        rvRecommendDetail.adapter = BookRecommendDetailAdapter(applicationContext, array)
        progress_detail.visibility = View.GONE
    }
}