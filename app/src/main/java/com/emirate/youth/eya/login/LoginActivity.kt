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
                emirateIdTIL.error = resources.getString(R.string.fill_valid_emirate_id)
        }

        passwordET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                passwordTIL.error = null
        }

        btn_login.setOnClickListener {
            if (emirateIdET.text.toString().trim().isEmpty() && passwordET.text.toString().trim()
                    .isEmpty()
            ) {
                emirateIdTIL.error = resources.getString(R.string.fill_emirate_id)
                passwordTIL.error = resources.getString(R.string.fill_password)
            } else if (emirateIdET.text.toString().trim().isEmpty() || passwordET.text.toString()
                    .trim()
                    .isEmpty()
            ) {
                if (emirateIdET.text.toString().trim().isEmpty()) {
                    emirateIdTIL.error = resources.getString(R.string.fill_emirate_id)
                }
                if (passwordET.text.toString().trim().isEmpty()) {
                    passwordTIL.error = resources.getString(R.string.fill_password)
                }
            } /*else if (emirateIdET.text.toString().length < 15) {
                emirateIdTIL.error = "Please fill valid Emirate Id"
            } */ else {
                callAPI()
            }
        }

        txt_signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            this.startActivity(intent)
        }
    }

    fun callAPI() {
        if (NetworkHelper.isOnline(applicationContext)) {
            showProgress(resources.getString(R.string.login_progress))
            val request = ServiceBuilder.buildService(ApiInterface::class.java)
            val call =
                request.login(emirateIdET.text.toString().trim(), passwordET.text.toString().trim())
            call.enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    hideProgress()
                    if (response.isSuccessful) {
                        val str_response = response.body()!!.string()
                        //creating json object
                        val json: JSONObject = JSONObject(str_response)
                        Log.w("Success", "json_contact :::: " + json.toString())
                        Toast.makeText(
                            this@LoginActivity,
                            json.getString("msg"),
                            Toast.LENGTH_LONG
                        ).show()
                        if (json.getBoolean("status")) {
                            SessionManager.storeSessionStringvalue(
                                applicationContext, AppConstant.LOGIN_SESSION_NAME,
                                AppConstant.LOGIN_SESSION_USER_ID, emirateIdET.text.toString()
                            )
                            callRegisterActivity()
                        } else {
                            emirateIdTIL.error = resources.getString(R.string.fill_valid_emirate_id)
                            passwordTIL.error = resources.getString(R.string.fill_valid_password)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("error", t.localizedMessage)
                    hideProgress()
                }
            })
        } else {
            showNoNetworkDialog()
        }
    }

    fun callRegisterActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        this.startActivity(intent)
        finish()
    }
}