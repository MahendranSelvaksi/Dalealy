package com.emirate.youth.eya.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
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
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class LoginActivity : BaseActivity() {

    val smileyRemover = SmileyRemover()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        emirateId1ET.filters = arrayOf<InputFilter>(smileyRemover)
        emirateId2ET.filters = arrayOf<InputFilter>(smileyRemover)
        emirateId3ET.filters = arrayOf<InputFilter>(smileyRemover)
        emirateId4ET.filters = arrayOf<InputFilter>(smileyRemover)
        passwordET.filters = arrayOf<InputFilter>(smileyRemover)
        emirateId1ET.filters += InputFilter.LengthFilter(3)
        emirateId2ET.filters += InputFilter.LengthFilter(4)
        emirateId3ET.filters += InputFilter.LengthFilter(5)
        emirateId4ET.filters += InputFilter.LengthFilter(3)


        emirateId1ET.doAfterTextChanged {
            if (it.toString().trim().isEmpty() && it.toString().trim().length==3){
                emirateId1ET.imeOptions= EditorInfo.IME_ACTION_NEXT
                emirateIdValidation()
            }else{
                emirateIdValidation()
            }
        }
        emirateId2ET.doAfterTextChanged {
            if (it.toString().trim().isEmpty() && it.toString().trim().length==4){
                emirateId2ET.imeOptions= EditorInfo.IME_ACTION_NEXT
                emirateIdValidation()
            }else{
                emirateIdValidation()
            }
        }
        emirateId3ET.doAfterTextChanged {
            if (it.toString().trim().isEmpty() && it.toString().trim().length==5){
                emirateId3ET.imeOptions= EditorInfo.IME_ACTION_NEXT
                emirateIdValidation()
            }else{
                emirateIdValidation()
            }
        }
        emirateId4ET.doAfterTextChanged {
            if (it.toString().trim().isEmpty() && it.toString().trim().length==3){
                emirateId4ET.imeOptions= EditorInfo.IME_ACTION_DONE
                emirateIdValidation()
            }else{
                emirateIdValidation()
            }
        }

        passwordET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                passwordTIL.error = null
        }

        btn_login.setOnClickListener {
            if (!emirateIdValidation() && passwordET.text.toString().trim().isEmpty()) {
                Log.w("Success","Comes If")
                passwordTIL.error = resources.getString(R.string.fill_password)
            } else if (!emirateIdValidation() || passwordET.text.toString()
                    .trim()
                    .isEmpty()
            ) {
                Log.w("Success","Comes Else If")
                emirateIdValidation()
                if (passwordET.text.toString().trim().isEmpty()) {
                    passwordTIL.error = resources.getString(R.string.fill_password)
                }
            } /*else if (emirateIdET.text.toString().length < 15) {
                emirateIdTIL.error = "Please fill valid Emirate Id"
            } */ else {
                Log.w("Success","Comes Else")
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
                request.login((emirateId1ET.text.toString().trim()+"-"+emirateId2ET.text.toString().trim()+"-"+emirateId3ET.text.toString().trim()+"-"+emirateId4ET.text.toString().trim()), passwordET.text.toString().trim())
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
                                AppConstant.LOGIN_SESSION_USER_ID, (emirateId1ET.text.toString().trim()+"-"+emirateId2ET.text.toString().trim()+"-"+emirateId3ET.text.toString().trim()+"-"+emirateId4ET.text.toString().trim())
                            )
                            callRegisterActivity()
                        } else {
                            emirateErrorTV.visibility= View.VISIBLE
                            emirateErrorTV.text  = resources.getString(R.string.fill_valid_emirate_id)
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

    private fun emirateIdValidation() : Boolean{
        return if (emirateId1ET.text.toString().trim().length< 3 || emirateId2ET.text.toString().trim().length< 4||
            emirateId3ET.text.toString().trim().length< 5 || emirateId4ET.text.toString().trim().length< 3 ) {
                Log.w("Success","Comes emirateIdValidation IF")
            emirateErrorTV.text = resources.getString(R.string.fill_valid_emirate_id)
            emirateErrorTV.visibility= View.VISIBLE
            false
        }else{
            Log.w("Success","Comes emirateIdValidation Else")
            emirateErrorTV.visibility= View.GONE
            true
        }
    }
}