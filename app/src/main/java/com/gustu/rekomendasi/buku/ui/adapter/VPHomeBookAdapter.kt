package com.gustu.rekomendasi.buku.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.ListFragment


/**Created by Gustu Maulana Firmansyah on 15,September,2021  gustumaulanaf@gmail.com **/
class VPHomeBookAdapter(
    val fm: FragmentManager,
    val listFragment: List<Fragment>,
    val listTitle: List<String>
) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitle[position]
    }
}