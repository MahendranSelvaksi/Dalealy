package com.emirate.youth.eya.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Build.VERSION_CODES
import android.preference.PreferenceManager
import android.util.Log
import com.emirate.youth.eya.utils.AppConstant.LANGUAGE_ENGLISH
import com.emirate.youth.eya.utils.AppConstant.LANGUAGE_KEY
import java.util.*

class LocaleManager(mContext: Context) {

    val prefs: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(mContext);

    fun getLocale(res: Resources): Locale? {
        val config = res.configuration
        return if (isAtLeastVersion(VERSION_CODES.N)) config.locales[0] else config.locale
    }

    fun isAtLeastVersion(version: Int): Boolean {
        return Build.VERSION.SDK_INT >= version
    }

    fun setLocale(c: Context?): Context? {
        return updateResources(c!!, getLanguage().toString())
    }

    fun setNewLocale(c: Context?, language: String): Context? {
        Log.w("Success", "setNewLocale:::$language")
        persistLanguage(language)
        return updateResources(c!!, language)
    }

    fun getLanguage(): String? {
        return prefs!!.getString(
            LANGUAGE_KEY,
           LANGUAGE_ENGLISH
        )
    }

    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(language: String) {
        Log.w("Success", "presistLanguage:::$language")
        // use commit() instead of apply(), because sometimes we kill the application process immediately
        // which will prevent apply() to finish
        prefs!!.edit()
            .putString(LANGUAGE_KEY, language)
            .commit()
    }

    private fun updateResources(context: Context, language: String): Context? {
        Log.w("Success", "updateResources:::$language")
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)
        /*  if (Utility.isAtLeastVersion(JELLY_BEAN_MR1)) {
            Log.w("Success","Comes updateResources if");
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {*/Log.w("Success", "Comes updateResources else")
        config.locale = locale
        res.updateConfiguration(config, res.displayMetrics)
        //}
        return context
    }
}