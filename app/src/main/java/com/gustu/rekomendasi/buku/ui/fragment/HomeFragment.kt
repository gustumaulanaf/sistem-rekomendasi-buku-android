package com.gustu.rekomendasi.buku.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.gustu.rekomendasi.buku.R
import com.gustu.rekomendasi.buku.model.BooksItem
import com.gustu.rekomendasi.buku.presenter.BookView
import com.gustu.rekomendasi.buku.ui.adapter.VPHomeBookAdapter
import com.gustu.rekomendasi.buku.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    lateinit var root: View
    val fragmentList = listOf(HomeBookFragment("for"), HomeBookFragment("new"))
    val titleList = listOf("For You", "New")
    lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vpAdapter = VPHomeBookAdapter(childFragmentManager, fragmentList, titleList)
        view.vpHome.adapter = vpAdapter
        view.tabHome.setupWithViewPager(view.vpHome)
        view.vpHome.setCurrentItem(0, true)
        if (isAdded) {
            view.tabHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    view.vpHome.setCurrentItem(tab!!.position, true)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
        }
        Glide.with(mContext).load(SharedPrefUtil.getString("avatar")).into(view.imgHome)
        view.tvNameHome.text = SharedPrefUtil.getString("nama")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}