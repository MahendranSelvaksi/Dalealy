package com.emirate.youth.eya.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.DashBoardAdapter
import com.emirate.youth.eya.adapters.UniversityListAdapter
import com.emirate.youth.eya.admin.AdminDashboardActivity
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.model.LoginModel
import android.content.Intent
import android.net.Uri
import kotlinx.android.synthetic.main.common_toolbar.*


class UniversityListActivity : BaseActivity(), UniversityListAdapter.NavigateBrowserListener {

    var mUniversityListAdapter = UniversityListAdapter(this,this)

    private val commonTitle: TextView by lazy { findViewById(R.id.commonTitle) }
    private val backButton: ImageView by lazy { findViewById(R.id.backButton) }
    private val evaluationTV: androidx.appcompat.widget.AppCompatTextView by lazy { findViewById(R.id.evaluationTV) }

    var universityList = ArrayList<LoginModel>()
    var parentCat =  ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        parentCat = intent.getIntegerArrayListExtra("parentCat") as ArrayList<Int>

        language.visibility=View.GONE
        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        val btn_next = findViewById<Button>(R.id.btn_next)
        commonTitle.text=resources.getString(R.string.title_activity_unversity_list)
        evaluationTV.visibility= View.GONE




        btn_next.setText(resources.getString(R.string.close))



        logout.setOnClickListener {
            showLogoutDialog()
        }

        language.setOnClickListener {
            showLanguageChangeDialog(UniversityListActivity::class.java)
        }

        backButton.setOnClickListener {
            finish()
        }
        btn_next.setOnClickListener {
            finish()
        }

        mRecyclerView.adapter=mUniversityListAdapter

        loadData()
    }

    fun loadData(){
        parentCat.forEach {
            when(it){
                1 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_1).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_1).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                2 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_2).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_2).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                3 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_3).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_3).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                4 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_4).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_4).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                5 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_5).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_5).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                6 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_6).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_6).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                7 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_7).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_7).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                8 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_8).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_8).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                9 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_9).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_9).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                10 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_10).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_10).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                11 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_11).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_11).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                12 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_12).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_12).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                13 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_13).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_13).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                14 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_14).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_14).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                15 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_15).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_15).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                16 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_16).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_16).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                17 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_17).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_17).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                18 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_18).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_18).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
                19 -> {
                    val uniNameArray = resources.getStringArray(R.array.name_of_the_universities_for_future_jobs_of_key_19).toList()
                    val linkArray = resources.getStringArray(R.array.link_of_the_universities_for_future_jobs_of_key_19).toList()
                    uniNameArray.forEachIndexed { j, s ->
                        var loginModel=LoginModel()
                        loginModel.emirateId=uniNameArray[j].toString()
                        loginModel.password=linkArray[j].toString()
                        universityList.add(loginModel)
                    }
                }
            }
        }
        val uniNameArray = resources.getStringArray(R.array.university_name_array).toList()
        val linkArray = resources.getStringArray(R.array.university_link_aaray).toList()
        uniNameArray.forEachIndexed { j, s ->
            var loginModel=LoginModel()
            loginModel.emirateId=uniNameArray[j].toString()
            loginModel.password=linkArray[j].toString()
            universityList.add(loginModel)
        }
        mUniversityListAdapter.setItems(universityList)
    }

    override fun navigateToBrowser(value: String, position: Int) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(value)))
    }
}