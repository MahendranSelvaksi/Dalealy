package com.emirate.youth.eya.question

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.JobsAdapter
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.SpinnerListener
import com.emirate.youth.eya.utils.model.SkillsCatModel
import kotlinx.android.synthetic.main.activity_jobs.*
import kotlinx.android.synthetic.main.common_toolbar.*

class JobsActivity : BaseActivity(), SpinnerListener {

    var listOfSkills = ArrayList<Int>()
    var jobsList = ArrayList<SkillsCatModel>()
    var mJobsAdapter = JobsAdapter(this, this)
    var checkedItemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        listOfSkills = intent.getIntegerArrayListExtra("listOfSkills") as ArrayList<Int>

        val btn_next = findViewById<Button>(R.id.btn_next)
        commonTitle.text = resources.getString(R.string.future_jobs_key_title)


        language.visibility = View.GONE
        logout.setOnClickListener {
            showLogoutDialog()
        }

        language.setOnClickListener {
            showLanguageChangeDialog(JobsActivity::class.java)
        }

        backButton.setOnClickListener {
            finish()
        }
        btn_next.setOnClickListener {
            val list = java.util.ArrayList<Int>()
            val parentCat = java.util.ArrayList<Int>()
            jobsList.forEach {
                if (it.isChecked) {
                    list.add(it.category)
                    parentCat.add(it.parentCat)
                }
            }
            when {
                list.isEmpty() -> {
                    Toast.makeText(this, "Please select at least 1 job(s)", Toast.LENGTH_LONG)
                        .show()
                }
                list.size <= 3 -> {
                    val intent = Intent(this, FutureJobsActivity::class.java)
                    intent.putIntegerArrayListExtra("listOfSkills", list)
                    intent.putIntegerArrayListExtra("parentCat", parentCat)
                    this.startActivity(intent)
                    finish()
                }
                list.size > 3 -> {
                    Toast.makeText(this, "Please select only 3 jobs", Toast.LENGTH_LONG).show()
                }
            }
        }

        mRecyclerView.adapter = mJobsAdapter
        loadData()
    }

    private fun loadData() {

        listOfSkills.forEachIndexed { j, data ->
            when (data) {
                1 -> {
                    val indexArray =
                        resources.getIntArray(R.array.index_for_future_jobs_of_cat_1).toList()
                    resources.getStringArray(R.array.key_for_future_jobs_of_cat_1).toList()
                        .forEachIndexed { index, s ->
                            val skillsCatModel = SkillsCatModel()
                            skillsCatModel.category = 1
                            skillsCatModel.parentCat = indexArray[index]
                            skillsCatModel.skill = s.toString().trim()
                            jobsList.add(skillsCatModel)
                        }
                }
                2 -> {
                    val indexArray =
                        resources.getIntArray(R.array.index_for_future_jobs_of_cat_2).toList()
                    resources.getStringArray(R.array.key_for_future_jobs_of_cat_2).toList()
                        .forEachIndexed { index, s ->
                            val skillsCatModel = SkillsCatModel()
                            skillsCatModel.category = 2
                            skillsCatModel.parentCat = indexArray[index]
                            skillsCatModel.skill = s.toString().trim()
                            jobsList.add(skillsCatModel)
                        }
                }
                3 -> {

                    val indexArray =
                        resources.getIntArray(R.array.index_for_future_jobs_of_cat_3).toList()
                    resources.getStringArray(R.array.key_for_future_jobs_of_cat_3).toList()
                        .forEachIndexed { index, s ->
                            val skillsCatModel = SkillsCatModel()
                            skillsCatModel.category = 3
                            skillsCatModel.parentCat = indexArray[index]
                            skillsCatModel.skill = s.toString().trim()
                            jobsList.add(skillsCatModel)
                        }
                }
                4 -> {
                    val indexArray =
                        resources.getIntArray(R.array.index_for_future_jobs_of_cat_4).toList()
                    resources.getStringArray(R.array.key_for_future_jobs_of_cat_4).toList()
                        .forEachIndexed { index, s ->
                            val skillsCatModel = SkillsCatModel()
                            skillsCatModel.category = 4
                            skillsCatModel.parentCat = indexArray[index]
                            skillsCatModel.skill = s.toString().trim()
                            jobsList.add(skillsCatModel)
                        }
                }
            }
        }
        mJobsAdapter.setItems(jobsList, checkedItemCount)
    }

    override fun onItemSelectListener(value: String, position: Int) {
        if (position >= 0) {
            jobsList[position].isChecked = value.toBoolean() == true
            //  mJobsAdapter.setItems(jobsList,checkedItemCount)
        } else {
            Toast.makeText(this, "Should choose only 3 jobs", Toast.LENGTH_LONG).show()
        }

    }
}