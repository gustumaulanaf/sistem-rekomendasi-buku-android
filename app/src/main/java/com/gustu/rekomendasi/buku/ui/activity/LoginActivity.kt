package com.gustu.rekomendasi.buku.ui.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gustu.rekomendasi.buku.R
import com.gustu.rekomendasi.buku.model.LoginItem
import com.gustu.rekomendasi.buku.presenter.LoginPresenter
import com.gustu.rekomendasi.buku.presenter.LoginView
import com.gustu.rekomendasi.buku.retrofit.RetrofitClient
import com.gustu.rekomendasi.buku.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginView {
    lateinit var presenter: LoginPresenter
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initPresenter()
    }

    private fun initPresenter() {
        presenter = LoginPresenter(RetrofitClient(), this)
    }

    private fun initView() {
        btLogin.setOnClickListener(this)
        progressDialog = ProgressDialog(this)
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setMessage("Sedang Masuk...")
        progressDialog.setTitle("Tunggu")
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btLogin -> {
                if (etUserIDLogin.text.toString().isEmpty()) {
                    etUserIDLogin.error = "Tidak Boleh Kosong"
                } else if (etPwdLogin.text.toString().isEmpty()) {
                    etPwdLogin.error = "Tidak Boleh Kosong"
                } else {
                    presenter.login(
                        etUserIDLogin.text.toString().toInt(),
                        etPwdLogin.text.toString().toInt()
                    )
                    progressDialog.show()
                }
            }
        }
    }

    override fun onSuccess(body: List<LoginItem>) {
        progressDialog.dismiss()
        SharedPrefUtil.saveString("user_id", etUserIDLogin.text.toString())
        SharedPrefUtil.saveString("password", etPwdLogin.text.toString())
        val lokasi = body[0].location!!
        val lastWord: String = lokasi.substring(lokasi.lastIndexOf(" ") + 1)
        SharedPrefUtil.saveString("lokasi", "%${lastWord}%")
        SharedPrefUtil.saveString("nama", body[0].name!!)
        SharedPrefUtil.saveString("avatar", body[0].avatar!!)
        SharedPrefUtil.saveString("usia", body[0].age!!)
        SharedPrefUtil.saveString("alamat", body[0].location!!)
        SharedPrefUtil.saveBoolean("isLogin", true)
        Toast.makeText(applicationContext, lastWord, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onError(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
        progressDialog.dismiss()
    }
}