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
import com.emirate.youth.eya.utils.model.SkillsCatModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.common_toolbar.*

class SkillsActivity : BaseActivity() {

    var mSkillsAdapters = SkillsAdapters(this)

    var skillsList = ArrayList<SkillsCatModel>()
    var listOfSkills = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        listOfSkills = intent.getIntegerArrayListExtra("listOfSkills") as ArrayList<Int>

        val btn_next = findViewById<Button>(R.id.btn_next)
        commonTitle.text = resources.getString(R.string.skill)
        evaluationTV.visibility = View.GONE
        language.visibility=View.GONE

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
            val intent = Intent(this, JobsActivity::class.java)
            intent.putIntegerArrayListExtra("listOfSkills",listOfSkills)
            this.startActivity(intent)
            finish()
        }

        mRecyclerView.adapter = mSkillsAdapters

        loadData()
    }

    private fun loadData() {

        listOfSkills.forEachIndexed { j, data ->
            when (data) {
                1 -> {
                    resources.getStringArray(R.array.cat_1_skills).toList().forEach {
                        val skillsCatModel = SkillsCatModel()
                        skillsCatModel.category=1
                        skillsCatModel.skill=it.toString().trim()
                        skillsList.add(skillsCatModel)
                    }
                }
                2 -> {
                    resources.getStringArray(R.array.cat_2_skills).toList().forEach {
                        val skillsCatModel = SkillsCatModel()
                        skillsCatModel.category=2
                        skillsCatModel.skill=it.toString().trim()
                        skillsList.add(skillsCatModel)
                    }
                }
                3 -> {
                    resources.getStringArray(R.array.cat_3_skills).toList().forEach {
                        val skillsCatModel = SkillsCatModel()
                        skillsCatModel.category=3
                        skillsCatModel.skill=it.toString().trim()
                        skillsList.add(skillsCatModel)
                    }
                }
                4 -> {
                    resources.getStringArray(R.array.cat_4_skills).toList().forEach {
                        val skillsCatModel = SkillsCatModel()
                        skillsCatModel.category=4
                        skillsCatModel.skill=it.toString().trim()
                        skillsList.add(skillsCatModel)
                    }
                }
            }
        }
        mSkillsAdapters.setItems(skillsList)
    }
}