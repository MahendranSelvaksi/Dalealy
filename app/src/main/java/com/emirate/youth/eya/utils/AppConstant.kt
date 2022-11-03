package com.emirate.youth.eya.utils

import android.os.Build

object AppConstant {
    val BASE_URL="https://amritastores.com/schoolApp/"

    val LOGIN_SESSION_USER_ID: String="emiratesId"
    val LOGIN_SESSION_Token: String="User_token"
    val LOGIN_SESSION_NAME: String="Login_session"

    val NoInternet="Internet connection is required!"

    val LANGUAGE_ENGLISH:String="en"
    val LANGUAGE_ARABIC:String="ar"
    val LANGUAGE_KEY:String="language_key"


    fun isAtLeastVersion(version: Int): Boolean {
        return Build.VERSION.SDK_INT >= version
    }
}