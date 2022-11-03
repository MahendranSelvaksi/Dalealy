package com.emirate.youth.eya.utils

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log

class AppController : Application() {

    var localeManager: LocaleManager? = null

    override fun attachBaseContext(base: Context) {
        localeManager = LocaleManager(base)
        super.attachBaseContext(localeManager!!.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeManager!!.setLocale(this)
        Log.w("TAG", "onConfigurationChanged: " + newConfig.locale.language)
    }
}

