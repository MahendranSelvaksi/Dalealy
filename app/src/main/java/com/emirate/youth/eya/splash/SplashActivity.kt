package com.emirate.youth.eya.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emirate.youth.eya.R
import com.emirate.youth.eya.login.LoginActivity
import android.os.Handler
import android.view.WindowManager
import com.emirate.youth.eya.dashboard.DashboardActivity
import com.emirate.youth.eya.utils.SessionManager

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            if (SessionManager.isLogin(applicationContext)){
                val intent = Intent(this, DashboardActivity::class.java)
                this.startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                this.startActivity(intent)
                finish()
            }

        }, 3000)
    }
}