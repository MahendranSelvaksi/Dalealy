package com.emirate.youth.eya.question

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.dashboard.DashboardActivity
import com.emirate.youth.eya.utils.BaseActivity
import com.google.android.material.textfield.TextInputLayout

class ImageShownActivity : BaseActivity() {

    private val btIV: androidx.appcompat.widget.AppCompatImageView by lazy { findViewById(R.id.btIV) }
    private val cjIV: androidx.appcompat.widget.AppCompatImageView by lazy { findViewById(R.id.cjIV) }
    private val fjIV: androidx.appcompat.widget.AppCompatImageView by lazy { findViewById(R.id.fjIV) }
    private val fsIV: androidx.appcompat.widget.AppCompatImageView by lazy { findViewById(R.id.fsIV) }
    private val btn_next: Button by lazy { findViewById(R.id.btn_next) }
    private val leaderTV: TextView by lazy { findViewById(R.id.leaderTV) }

    private val commonTitle: TextView by lazy { findViewById(R.id.commonTitle) }
    private val backButton: ImageView by lazy { findViewById(R.id.backButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_shown)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }


        val fromPageNumber=intent.getIntExtra("PageNumber",1)

        Log.w("Success","PageNumber ::::: $fromPageNumber")


        val logout = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.logout)
        val language = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.language)

        commonTitle.text=resources.getString(R.string.ke_title)

        initialize(fromPageNumber)

        logout.setOnClickListener {
            showLogoutDialog()
        }

        language.setOnClickListener {
            showLanguageChangeDialog(ImageShownActivity::class.java)
        }

        backButton.setOnClickListener {
            finish()
        }

        btn_next.setOnClickListener {
            when (fromPageNumber) {
                1 -> {
                    val intent = Intent(this, Question2Activity::class.java)
                    this.startActivity(intent)
                    finish()
                }
                2 -> {
                    val intent = Intent(this, Question3Activity::class.java)
                    this.startActivity(intent)
                    finish()
                }
                3 -> {
                    val intent = Intent(this, Question4Activity::class.java)
                    this.startActivity(intent)
                    finish()
                }
                4 -> {
                    finish()
                }
            }
        }
    }

    fun initialize(pageNumber:Int){
        if (pageNumber==1){
            leaderTV.text=resources.getText(R.string.leader_name_cat_1)
            btIV.setImageResource(R.drawable.bt1)
            cjIV.setImageResource(R.drawable.cj1)
            fjIV.setImageResource(R.drawable.fj1)
            fsIV.setImageResource(R.drawable.fs1)
        }else if (pageNumber==2){
            leaderTV.text=resources.getText(R.string.leader_name_cat_2)
            btIV.setImageResource(R.drawable.bt2)
            cjIV.setImageResource(R.drawable.cj2)
            fjIV.setImageResource(R.drawable.fj2)
            fsIV.setImageResource(R.drawable.fs2)
        }else if (pageNumber==3){
            leaderTV.text=resources.getText(R.string.leader_name_cat_3)
            btIV.setImageResource(R.drawable.bt3)
            cjIV.setImageResource(R.drawable.cj3)
            fjIV.setImageResource(R.drawable.fj3)
            fsIV.setImageResource(R.drawable.fs3)
        }else if (pageNumber==4){
            leaderTV.text=resources.getText(R.string.leader_name_cat_4)
            btIV.setImageResource(R.drawable.bt4)
            cjIV.setImageResource(R.drawable.cj4)
            fjIV.setImageResource(R.drawable.fj4)
            fsIV.setImageResource(R.drawable.fs4)
            btn_next.setText("Close")
        }
    }
}