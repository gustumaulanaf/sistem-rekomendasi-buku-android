package com.gustu.rekomendasi.buku.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import kotlinx.android.synthetic.main.fragment_home_book.*
import kotlinx.android.synthetic.main.fragment_home_book.view.*

class HomeBookFragment(var params: String) : Fragment(), BookView {
    lateinit var mContext: Context
    lateinit var root: View
    lateinit var presenter: BooksPresenter
    var page: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home_book, container, false)
        initPresenter()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.swipe_home_book.setOnRefreshListener {
            initPresenter()
        }
        if (params == "for") {
            if (page > 1) {
                view.btBackHomeBook.visibility = View.VISIBLE
            } else {
                view.btBackHomeBook.visibility = View.GONE
            }
            view.btBackHomeBook.setOnClickListener {
                if (page > 0) {
                    view.btBackHomeBook.visibility = View.GONE
                    view.btNextHomeBook.visibility = View.GONE
                    root.progress_home_book.visibility = View.VISIBLE
                    root.rvHomeBook.visibility = View.GONE
                    page -= 30
                    presenter.rekomendasiNegara(page, SharedPrefUtil.getString("lokasi")!!)
                }
            }
            view.btNextHomeBook.setOnClickListener {
                view.btBackHomeBook.visibility = View.GONE
                view.btNextHomeBook.visibility = View.GONE
                root.progress_home_book.visibility = View.VISIBLE
                root.rvHomeBook.visibility = View.GONE
                page += 30
                presenter.rekomendasiNegara(page, SharedPrefUtil.getString("lokasi")!!)
            }
        } else {
            view.btBackHomeBook.visibility = View.GONE
            view.btNextHomeBook.visibility = View.GONE

        }
    }

    private fun initPresenter() {
        root.btBackHomeBook.visibility = View.GONE
        root.btNextHomeBook.visibility = View.GONE
        root.progress_home_book.visibility = View.VISIBLE
        presenter = BooksPresenter(RetrofitClient(), this)
        if (params == "for") {
            presenter.rekomendasiNegara(page, SharedPrefUtil.getString("lokasi")!!)
        } else {
            presenter.getBukuBaru(SharedPrefUtil.getString("lokasi")!!)
        }
    }

    override fun onSuccess(body: Books) {
    }

    override fun onError(s: String) {
        root.swipe_home_book.isRefreshing = false
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show()
        root.progress_home_book.visibility = View.GONE
        root.btBackHomeBook.visibility = View.GONE
        root.btNextHomeBook.visibility = View.GONE
    }

    override fun onSuccess2(body: List<BooksItem>) {
        root.swipe_home_book.isRefreshing = false
        root.btNextHomeBook.visibility = View.VISIBLE
        if (params == "for") {
            root.btNextHomeBook.visibility = View.VISIBLE
            if (page > 1) {
                root.btBackHomeBook.visibility = View.VISIBLE
            } else {
                root.btBackHomeBook.visibility = View.GONE
            }
        } else {
            root.btBackHomeBook.visibility = View.GONE
            root.btNextHomeBook.visibility = View.GONE

        }
        root.rvHomeBook.visibility = View.VISIBLE
        root.rvHomeBook.layoutManager = GridLayoutManager(mContext, 3)
        root.rvHomeBook.setHasFixedSize(true)
        root.rvHomeBook.adapter = BooksAdapter(mContext, body)
        root.rvHomeBook.smoothScrollToPosition(0)
        root.progress_home_book.visibility = View.GONE
    }

    override fun onSuccess3(body: List<RekomendasiItem>) {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}