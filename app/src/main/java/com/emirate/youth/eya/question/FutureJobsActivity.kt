package com.emirate.youth.eya.question

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.SkillsAdapters
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.model.SkillsCatModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.common_toolbar.*
import java.util.*
import kotlin.collections.ArrayList

class FutureJobsActivity : BaseActivity() {

    var mSkillsAdapters = SkillsAdapters(this)

    var skillsList = ArrayList<SkillsCatModel>()
    var listOfSkills = ArrayList<Int>()
    var listOfJobsKeys =  TreeSet<Int>()
    var parentCat =  ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        listOfSkills = intent.getIntegerArrayListExtra("listOfSkills") as ArrayList<Int>
        parentCat = intent.getIntegerArrayListExtra("parentCat") as ArrayList<Int>

        listOfJobsKeys.addAll(listOfSkills)

        val btn_next = findViewById<Button>(R.id.btn_next)
        commonTitle.text = resources.getString(R.string.future_jobs)
        evaluationTV.visibility = View.GONE


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
            val intent = Intent(this, UniversityListActivity::class.java)
            intent.putIntegerArrayListExtra("listOfSkills",listOfSkills)
            intent.putIntegerArrayListExtra("parentCat",parentCat)
            this.startActivity(intent)
            finish()
        }

        mRecyclerView.adapter = mSkillsAdapters

        loadData()
    }

    private fun loadData() {

        listOfJobsKeys.forEachIndexed { j, data ->
            when (data) {
                1 -> {
                    resources.getStringArray(R.array.future_jobs_for_cat_1).toList().forEach {
                        val skillsCatModel = SkillsCatModel()
                        skillsCatModel.category=1
                        skillsCatModel.parentCat=data
                        skillsCatModel.skill=it.toString().trim()
                        skillsList.add(skillsCatModel)
                    }
                }
                2 -> {
                    resources.getStringArray(R.array.future_jobs_for_cat_2).toList().forEach {
                        val skillsCatModel = SkillsCatModel()
                        skillsCatModel.category=2
                        skillsCatModel.parentCat=data
                        skillsCatModel.skill=it.toString().trim()
                        skillsList.add(skillsCatModel)
                    }
                }
                3 -> {
                    resources.getStringArray(R.array.future_jobs_for_cat_3).toList().forEach {
                        val skillsCatModel = SkillsCatModel()
                        skillsCatModel.category=3
                        skillsCatModel.parentCat=data
                        skillsCatModel.skill=it.toString().trim()
                        skillsList.add(skillsCatModel)
                    }
                }
                4 -> {
                    resources.getStringArray(R.array.future_jobs_for_cat_4).toList().forEach {
                        val skillsCatModel = SkillsCatModel()
                        skillsCatModel.category=4
                        skillsCatModel.parentCat=data
                        skillsCatModel.skill=it.toString().trim()
                        skillsList.add(skillsCatModel)
                    }
                }
            }
        }
        mSkillsAdapters.setItems(skillsList)
    }
}