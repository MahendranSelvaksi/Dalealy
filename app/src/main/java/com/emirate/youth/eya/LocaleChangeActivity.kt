package com.emirate.youth.eya

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.ConfigurationCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.emirate.youth.eya.databinding.ActivityLocaleChangeBinding
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.LocaleUtil
import kotlinx.android.synthetic.main.activity_locale_change.*
import java.util.*

class LocaleChangeActivity : BaseActivity() {
    private lateinit var firstLocaleCode: String
    private lateinit var secondLocaleCode: String
    private lateinit var currentSystemLocaleCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locale_change)
        currentSystemLocaleCode = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0)!!.language
//        if(storage.getPreferredLocale() == LocaleUtil.OPTION_PHONE_LANGUAGE){
////            if(currentSystemLocaleCode in LocaleUtil.supportedLocales){
////                tvAppLocale.text = getString(R.string.phone_language, Locale(currentSystemLocaleCode).displayLanguage)
////            } else {
////                //current system language is neither English nor my second language (for me Bangla)
////                tvAppLocale.text = "English"
////            }
//        } else {
////            if(currentSystemLocaleCode == storage.getPreferredLocale()){
////                tvAppLocale.text = getString(R.string.phone_language, Locale(currentSystemLocaleCode).displayLanguage)
////            } else {
////                tvAppLocale.text = Locale(storage.getPreferredLocale()).displayLanguage
////            }
//        }
//        firstLocaleCode = if(currentSystemLocaleCode in LocaleUtil.supportedLocales){
//            currentSystemLocaleCode
//        } else {
//            if(storage.getPreferredLocale() == LocaleUtil.OPTION_PHONE_LANGUAGE){
//                //current system language is neither English nor my second language (for me Bangla)
//                "en"
//            } else {
//                storage.getPreferredLocale()
//            }
//        }
        secondLocaleCode = getSecondLanguageCode()
        initRadioButtonUI()
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.op1 -> {
                    updateAppLocale(LocaleUtil.OPTION_PHONE_LANGUAGE)
                    recreate()
                }
                R.id.op2 -> {
                    updateAppLocale(secondLocaleCode)
                    recreate()
                }
            }
        }
    }

    private fun getSecondLanguageCode(): String {
        return if(firstLocaleCode == "en") "ar" else "en"
    }

    @SuppressLint("StringFormatInvalid")
    private fun initRadioButtonUI() {
        if(currentSystemLocaleCode in LocaleUtil.supportedLocales){
            op1.text = getString(R.string.phone_language, getLanguageNameByCode(firstLocaleCode))
        } else {
            op1.text = getLanguageNameByCode(firstLocaleCode)
        }
        op2.text = getLanguageNameByCode(secondLocaleCode)
       // if(storage.getPreferredLocale() == secondLocaleCode) op2.isChecked = true
      //  else op1.isChecked = true
    }

    private fun getLanguageNameByCode(code: String) : String{
        val tempLocale = Locale(code)
        return tempLocale.getDisplayLanguage(tempLocale)
    }

    private fun updateAppLocale(locale: String) {
       // storage.setPreferredLocale(locale)
        LocaleUtil.applyLocalizedContext(applicationContext, locale)
    }
}