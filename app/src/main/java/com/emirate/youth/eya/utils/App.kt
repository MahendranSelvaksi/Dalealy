package com.emirate.youth.eya.utils

import android.app.Application
import android.content.Context

class App : Application() {

    val storage : Storage by lazy {
        Storage(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleUtil.getLocalizedContext(base, Storage(base).getPreferredLocale()))
    }
}