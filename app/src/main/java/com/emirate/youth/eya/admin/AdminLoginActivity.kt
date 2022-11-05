package com.emirate.youth.eya.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.emirate.youth.eya.R
import com.emirate.youth.eya.dashboard.DashboardActivity
import com.emirate.youth.eya.utils.BaseActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AdminLoginActivity : BaseActivity() {

    private val emirateIdTIL: TextInputLayout by lazy { findViewById(R.id.userNameTIL) }
    private val emirateIdET: TextInputEditText by lazy { findViewById(R.id.userNameET) }

    private val passwordTIL: TextInputLayout by lazy { findViewById(R.id.passwordTIL) }
    private val passwordET: TextInputEditText by lazy { findViewById(R.id.passwordET) }
    private val btn_login: Button by lazy { findViewById(R.id.btn_login) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_admin_login)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }


        emirateIdET.doAfterTextChanged {
            if (it.toString().isNotEmpty() || it.toString().length >= 15)
                emirateIdTIL.error = null
        }

        passwordET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                passwordTIL.error = null
        }

        btn_login.setOnClickListener {
            if (emirateIdET.text.toString().isEmpty() && passwordET.text.toString().isEmpty()) {
                emirateIdTIL.error = resources.getString(R.string.fill_user_name)
                passwordTIL.error = resources.getString(R.string.fill_password)
            } else if (emirateIdET.text.toString().isEmpty() || passwordET.text.toString()
                    .isEmpty()
            ) {
                if (emirateIdET.text.toString().isEmpty()) {
                    emirateIdTIL.error = resources.getString(R.string.fill_user_name)
                }
                if (passwordET.text.toString().isEmpty()) {
                    passwordTIL.error = resources.getString(R.string.fill_password)
                }
            } else {
                if (emirateIdET.text.toString().equals("admin", true) && passwordET.text.toString()
                        .equals("admin", true)
                ) {
                    val intent = Intent(this, AdminDashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    this.startActivity(intent)
                    finish()
                }
            }
        }
    }
}