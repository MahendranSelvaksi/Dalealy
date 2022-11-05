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


class UniversityListActivity : BaseActivity(), UniversityListAdapter.NavigateBrowserListener {

    var mUniversityListAdapter = UniversityListAdapter(this,this)

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

        val logout = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.logout)
        val language = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.language)
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