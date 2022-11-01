package com.emirate.youth.eya.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.emirate.youth.eya.R
import com.emirate.youth.eya.dashboard.DashboardActivity
import com.emirate.youth.eya.signup.SignupActivity
import com.emirate.youth.eya.utils.*
import com.emirate.youth.eya.utils.network.ApiInterface
import com.emirate.youth.eya.utils.network.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class LoginActivity : BaseActivity() {


    private val emirateIdTIL: TextInputLayout by lazy { findViewById(R.id.emirateIdTIL) }
    private val emirateIdET: TextInputEditText by lazy { findViewById(R.id.emirateIdET) }

    private val passwordTIL: TextInputLayout by lazy { findViewById(R.id.passwordTIL) }
    private val passwordET: TextInputEditText by lazy { findViewById(R.id.passwordET) }
    private val txt_signup: TextView by lazy { findViewById(R.id.txt_signup) }
    private val btn_login: Button by lazy { findViewById(R.id.btn_login) }
    private val switchOnOff: androidx.appcompat.widget.SwitchCompat by lazy { findViewById(R.id.switchOnOff) }

    val smileyRemover = SmileyRemover()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        emirateIdET.filters = arrayOf<InputFilter>(smileyRemover)
        passwordET.filters = arrayOf<InputFilter>(smileyRemover)
        emirateIdET.filters += InputFilter.LengthFilter(15)


        emirateIdET.doAfterTextChanged {
            if (it.toString().isNotEmpty() || it.toString().length >= 15)
                emirateIdTIL.error = null
            else if (it.toString().length < 15)
                emirateIdTIL.error = "Please fill valid Emirate Id"
        }

        passwordET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                passwordTIL.error = null
        }

        switchOnOff.setOnClickListener {
            //LocaleManagerMew.setLocale(this@LoginCustomerFragment.activity?.applicationContext)
            var mCurrentLanguage =
                LocaleManagerMew.getCurrentLanguage(this@LoginActivity.applicationContext!!.applicationContext)
            if (mCurrentLanguage == LocaleManagerMew.mArabicFlag) {
                LocaleManagerMew.setNewLocale(
                    this@LoginActivity.applicationContext!!,
                    LocaleManagerMew.mEnglishFlag
                )
            } else if (mCurrentLanguage == LocaleManagerMew.mEnglishFlag) {
                LocaleManagerMew.setNewLocale(
                    this@LoginActivity.applicationContext!!,
                    LocaleManagerMew.mArabicFlag
                )
            }
            this.recreate()
        }

        btn_login.setOnClickListener {
            if (emirateIdET.text.toString().isEmpty() && passwordET.text.toString().isEmpty()) {
                emirateIdTIL.error = "Please fill Emirate Id"
                passwordTIL.error = "Please fill Password"
            } else if (emirateIdET.text.toString().isEmpty() || passwordET.text.toString()
                    .isEmpty()
            ) {
                if (emirateIdET.text.toString().isEmpty()) {
                    emirateIdTIL.error = "Please fill Emirate Id"
                }
                if (passwordET.text.toString().isEmpty()) {
                    passwordTIL.error = "Please fill Password"
                }
            } /*else if (emirateIdET.text.toString().length < 15) {
                emirateIdTIL.error = "Please fill valid Emirate Id"
            } */ else {
                showProgress("Login progress, Please wait...")
                val request = ServiceBuilder.buildService(ApiInterface::class.java)
                val call = request.login(emirateIdET.text.toString(), passwordET.text.toString())
                call.enqueue(object : retrofit2.Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        hideProgress()
                        if (response.isSuccessful) {
                            var str_response = response.body()!!.string()
                            //creating json object
                            val json: JSONObject = JSONObject(str_response)
                            Log.w("Success", "json_contact :::: " + json.toString())
                            Toast.makeText(
                                this@LoginActivity,
                                json.getString("msg"),
                                Toast.LENGTH_LONG
                            ).show()
                            if (json.getBoolean("status")) {
                                SessionManager.storeSessionStringvalue(applicationContext,AppConstant.LOGIN_SESSION_NAME,
                                    AppConstant.LOGIN_SESSION_USER_ID,emirateIdET.text.toString())
                                callRegisterActivity()
                            } else {
                                emirateIdTIL.error = "Please fill valid Emirate Id"
                                passwordTIL.error = "Please fill valid Password"
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("error", t.localizedMessage)
                        hideProgress()
                    }
                })
            }
        }

        txt_signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            this.startActivity(intent)
            finish()
        }
    }

    fun callRegisterActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        this.startActivity(intent)
        finish()
    }
}