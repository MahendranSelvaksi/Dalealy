package com.emirate.youth.eya.question

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.SkillsAdapters
import com.emirate.youth.eya.adapters.UniversityListAdapter
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.model.LoginModel

class SkillsActivity : BaseActivity() {

    var mSkillsAdapters = SkillsAdapters(this)

    private val commonTitle: TextView by lazy { findViewById(R.id.commonTitle) }
    private val backButton: ImageView by lazy { findViewById(R.id.backButton) }
    private val evaluationTV: androidx.appcompat.widget.AppCompatTextView by lazy { findViewById(R.id.evaluationTV) }

    var universityList = ArrayList<LoginModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        val fromPageNumber = intent.getIntExtra("PageNumber", 1)

        val logout = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.logout)
        val language = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.language)
        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        val btn_next = findViewById<Button>(R.id.btn_next)
        commonTitle.text = resources.getString(R.string.skill)
        evaluationTV.visibility = View.GONE


        if (fromPageNumber == 4)
            btn_next.text = resources.getString(R.string.close)



        logout.setOnClickListener {
            showLogoutDialog()
        }

        language.setOnClickListener {
            showLanguageChangeDialog(SkillsActivity::class.java)
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
                    val intent = Intent(this, UniversityListActivity::class.java)
                    this.startActivity(intent)
                    finish()
                }
            }
        }

        mRecyclerView.adapter = mSkillsAdapters

        loadData(fromPageNumber)
    }

    private fun loadData(fromPageNumber: Int) {
        var skillList: List<Any> = resources.getStringArray(R.array.cat_1_skills).toList()
        if (fromPageNumber == 1) {
            skillList = resources.getStringArray(R.array.cat_1_skills).toList()
        } else if (fromPageNumber == 2) {
            skillList = resources.getStringArray(R.array.cat_2_skills).toList()
        } else if (fromPageNumber == 3) {
            skillList = resources.getStringArray(R.array.cat_3_skills).toList()
        } else if (fromPageNumber == 4) {
            skillList = resources.getStringArray(R.array.cat_4_skills).toList()
        }
        skillList.forEachIndexed { j, s ->
            val loginModel = LoginModel()
            loginModel.emirateId = s.toString()
            universityList.add(loginModel)
        }
        mSkillsAdapters.setItems(universityList, fromPageNumber)
    }
}