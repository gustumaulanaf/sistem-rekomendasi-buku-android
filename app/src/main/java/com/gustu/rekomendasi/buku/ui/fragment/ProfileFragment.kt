package com.gustu.rekomendasi.buku.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.gustu.rekomendasi.buku.R
import com.gustu.rekomendasi.buku.ui.activity.LoginActivity
import com.gustu.rekomendasi.buku.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
    lateinit var mContext: Context
    lateinit var mActivity: Activity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.tvName.text = SharedPrefUtil.getString("nama")
        view.tvAge.text = SharedPrefUtil.getString("usia")
        view.tvCountry.text = SharedPrefUtil.getString("alamat")
        Glide.with(mContext).load(SharedPrefUtil.getString("avatar")).into(view.imgProfile)
        view.btSignOut.setOnClickListener {
            SharedPrefUtil.saveBoolean("isLogin", false)
            startActivity(Intent(mContext, LoginActivity::class.java))
            mActivity.finish()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mActivity = activity
    }
}