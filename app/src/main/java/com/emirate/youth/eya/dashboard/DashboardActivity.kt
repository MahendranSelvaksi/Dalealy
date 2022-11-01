package com.emirate.youth.eya.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.DashBoardAdapter
import com.emirate.youth.eya.login.LoginActivity
import com.emirate.youth.eya.question.QuestionActivity
import com.emirate.youth.eya.signup.SignupActivity
import com.emirate.youth.eya.utils.AppConstant
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.SessionManager

class DashboardActivity : BaseActivity(), DashBoardAdapter.NavigateListerner {

    var mDashBoardAdapter = DashBoardAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        val commonToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.commonToolbar)
        val logout = commonToolbar.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.logout)
        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        val btn_next = findViewById<Button>(R.id.btn_next)
        commonToolbar.setTitle("Dash Board")
        commonToolbar.setNavigationIcon(R.drawable.ic_arrow_back)

        logout.setOnClickListener {
            SessionManager.callLogout(applicationContext)
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
            finish()
        }

        commonToolbar.setNavigationOnClickListener {
            finish()
        }

        mRecyclerView.adapter=mDashBoardAdapter

        btn_next.setOnClickListener { navigateToQuestion() }
    }

    override fun navigateToQuestion() {
        val intent = Intent(this, QuestionActivity::class.java)
        this.startActivity(intent)
    }
}