package com.emirate.youth.eya.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("StaticFieldLeak")
object SessionManager{
    private lateinit var mContext: Context
    lateinit var appConstants: AppConstant

    @SuppressLint("StaticFieldLeak")
    fun SessionManager(context: Context) {
        mContext = context
        appConstants= AppConstant

    }

    fun getSessionStringValue(context: Context,sessionName: String, sessionKey: String): String {
        val settings = getSession(context,sessionName)
        return settings.getString(sessionKey, "0")!!
    }

    private fun getSession(mContext: Context,sessionName: String): SharedPreferences {
        val PRIVATE_MODE = 0
        return mContext.getSharedPreferences(sessionName, PRIVATE_MODE)
    }

    fun getSessionIntValue(mContext: Context,sessionName: String, sessionKey: String): Int {
        val settings = getSession(mContext,sessionName)
        // Reading integer value from SharedPreferences
        return settings.getInt(sessionKey, 0)
    }


    fun storeSessionStringvalue(mContext: Context,sessionName: String, key: String, value: String) {
        val settings = getSession(mContext,sessionName)
        // Writing String data to SharedPreferences
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }


    fun storeSessionIntvalue(mContext: Context,sessionName: String, key: String, value: Int) {
        val settings = getSession(mContext,sessionName)
        // Writing integer data to SharedPreferences
        val editor = settings.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun isLogin(mContext: Context): Boolean {
        val settings = getSession(mContext,AppConstant.LOGIN_SESSION_NAME)
        // Reading integer value from SharedPreferences

        return settings.getString(AppConstant.LOGIN_SESSION_USER_ID, "")!!.isNotEmpty()
    }

    fun getUserID(mContext: Context): String {
        val settings = getSession(mContext,AppConstant.LOGIN_SESSION_NAME)
        // Reading integer value from SharedPreferences
//        Log.w("Success", "UserID::: " + settings.getInt(appConstants.LOGIN_SESSION_USER_ID, -1))
        return if (settings.getInt(AppConstant.LOGIN_SESSION_USER_ID, -1) > 0) settings.getInt(AppConstant.LOGIN_SESSION_USER_ID, -1).toString() else ""
    }

    fun callLogout(mContext: Context) {
        val settings = getSession(mContext,AppConstant.LOGIN_SESSION_NAME)
        val editor = settings.edit()
        editor.clear()
        editor.apply()
    }

}