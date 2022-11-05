package com.emirate.youth.eya.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.emirate.youth.eya.R
import com.emirate.youth.eya.dashboard.DashboardActivity
import com.emirate.youth.eya.login.LoginActivity
import com.emirate.youth.eya.utils.BaseActivity

class UserNavigationActivity : BaseActivity() {

    private val btn_admin_login: Button by lazy { findViewById(R.id.btn_admin_login) }
    private val btn_student_login: Button by lazy { findViewById(R.id.btn_student_login) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_navigation)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        btn_admin_login.setOnClickListener {
            val intent = Intent(this, AdminLoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            this.startActivity(intent)
        }
        btn_student_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            this.startActivity(intent)
        }
    }
}