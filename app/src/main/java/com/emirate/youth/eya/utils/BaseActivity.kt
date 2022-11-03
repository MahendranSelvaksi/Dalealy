package com.emirate.youth.eya.utils

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.emirate.youth.eya.R
import com.emirate.youth.eya.dashboard.DashboardActivity
import com.emirate.youth.eya.login.LoginActivity
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var oldPrefLocaleCode : String

    lateinit var selectedLang:String

    /**
     * updates the toolbar text locale if it set from the android:label property of Manifest
     */
    private fun resetTitle() {
        try {
            val label = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA).labelRes;
            if (label != 0) {
                setTitle(label);
            }
        } catch (e: PackageManager.NameNotFoundException) {}
    }

    fun showLanguageChangeDialog(className: Class<*>?){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.alert)

        builder.setMessage(R.string.lang_msg)
        builder.setPositiveButton(
            R.string.Yes
        ) { dialog, which -> // do something like...
            val localeManager = LocaleManager(applicationContext)
            setNewLocale(
                if (localeManager.getLanguage()==(AppConstant.LANGUAGE_ENGLISH)) AppConstant.LANGUAGE_ARABIC
                else AppConstant.LANGUAGE_ENGLISH, true,className
            )
        }
        builder.setNegativeButton(
            R.string.No
        ) { dialog, which -> // do something like...
            dialog.dismiss()
        }

        // create and show the alert dialog

        // create and show the alert dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun setNewLocale(language: String, restartProcess: Boolean,classs: Class<*>?): Boolean {
        val localeManager = LocaleManager(applicationContext)
        localeManager.setNewLocale(applicationContext, language)
        val i = Intent(this, classs)
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        if (restartProcess) {
            System.exit(0)
        } else {
            //Toast.makeText(getActivity(), "Activity restarted", Toast.LENGTH_SHORT).show();
        }
        return true
    }


    fun showLogoutDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.alert)

        builder.setMessage(R.string.alert_msg)
        builder.setPositiveButton(
            R.string.Yes
        ) { dialog, which -> // do something like...
            SessionManager.callLogout(applicationContext)
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
            finish()
        }
        builder.setNegativeButton(
            R.string.No
        ) { dialog, which -> // do something like...
            dialog.dismiss()
        }

        // create and show the alert dialog

        // create and show the alert dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showExitConfirmationDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.alert)

        builder.setMessage(R.string.exit_msg)
        builder.setPositiveButton(
            R.string.Yes
        ) { dialog, which -> // do something like...
            finish()
        }
        builder.setNegativeButton(
            R.string.No
        ) { dialog, which -> // do something like...
            dialog.dismiss()
        }

        // create and show the alert dialog

        // create and show the alert dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun attachBaseContext(newBase: Context) {
        val localeManager = LocaleManager(newBase)
        selectedLang=localeManager.getLanguage().toString()
        super.attachBaseContext(localeManager.setLocale(newBase))
        Log.d("TAG", "attachBaseContext")
    }

    private val progressDialogLazy = lazy {
        val progressDialog = ProgressDialog(this, R.style.MyAlertDialogStyle)
        progressDialog.setMessage("Loading...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.show()
        return@lazy progressDialog
    }

    open val progressDialog by progressDialogLazy
    open fun showProgress(content: String?) {
        if (progressDialog.isShowing)
            progressDialog.dismiss()
        if (content != null) {
            progressDialog.setMessage("$content...")
            progressDialog.setCancelable(false)
        }
        progressDialog.show()
    }

    open fun hideProgress() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetTitle()
    }
}