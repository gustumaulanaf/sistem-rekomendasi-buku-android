package com.gustu.rekomendasi.buku.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.gustu.rekomendasi.buku.R
import com.gustu.rekomendasi.buku.model.Books
import com.gustu.rekomendasi.buku.model.BooksItem
import com.gustu.rekomendasi.buku.model.RekomendasiItem
import com.gustu.rekomendasi.buku.presenter.BookView
import com.gustu.rekomendasi.buku.presenter.BooksPresenter
import com.gustu.rekomendasi.buku.retrofit.RetrofitClient
import com.gustu.rekomendasi.buku.ui.adapter.BooksAdapter
import com.gustu.rekomendasi.buku.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment(), BookView {
    lateinit var presenter: BooksPresenter
    lateinit var root: View
    lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_search, container, false)
        initPresenter()
        return root
    }

    private fun initPresenter() {
        presenter = BooksPresenter(RetrofitClient(), this)
//        presenter.searchBook()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.searchBook.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                root.progress_search.visibility = View.VISIBLE
                presenter.searchBook(query!!, SharedPrefUtil.getString("lokasi")!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    override fun onSuccess(body: Books) {

    }

    override fun onError(s: String) {
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show()
        root.progress_search.visibility = View.GONE
    }

    override fun onSuccess2(body: List<BooksItem>) {
        if (body.size==0){
            Toast.makeText(mContext,"Buku Tidak Ditemukan",Toast.LENGTH_SHORT).show()
        }
        val adapter = BooksAdapter(mContext, body)
        root.rvSearch.layoutManager = GridLayoutManager(mContext, 3)
        root.rvSearch.setHasFixedSize(true)
        root.rvSearch.adapter = adapter
        root.progress_search.visibility = View.GONE
    }

    override fun onSuccess3(body: List<RekomendasiItem>) {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}