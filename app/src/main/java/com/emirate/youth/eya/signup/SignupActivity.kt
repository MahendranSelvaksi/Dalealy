package com.emirate.youth.eya.signup

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.SpinnerAdapter
import com.emirate.youth.eya.dashboard.DashboardActivity
import com.emirate.youth.eya.utils.AppConstant
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.SessionManager
import com.emirate.youth.eya.utils.SmileyRemover
import com.emirate.youth.eya.utils.model.SignupModel
import com.emirate.youth.eya.utils.network.ApiInterface
import com.emirate.youth.eya.utils.network.ServiceBuilder
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class SignupActivity : BaseActivity() {

    private val nameTIL: TextInputLayout by lazy { findViewById(R.id.nameTIL) }
    private val nameET: TextInputEditText by lazy { findViewById(R.id.nameET) }

    private val studentNumberTIL: TextInputLayout by lazy { findViewById(R.id.studentNumberTIL) }
    private val studentNumberET: TextInputEditText by lazy { findViewById(R.id.studentNumberET) }

    private val schoolNameTIL: TextInputLayout by lazy { findViewById(R.id.schoolNameTIL) }
    private val school_name_spinner: MaterialAutoCompleteTextView by lazy { findViewById(R.id.school_name_spinner) }

    private val educationalPathTIL: TextInputLayout by lazy { findViewById(R.id.educationalPathTIL) }
    private val educationalPathET: TextInputEditText by lazy { findViewById(R.id.educationalPathET) }

    private val classTIL: TextInputLayout by lazy { findViewById(R.id.classTIL) }
    private val classSpinner: MaterialAutoCompleteTextView by lazy { findViewById(R.id.class_spinner) }

    private val emailIdTIL: TextInputLayout by lazy { findViewById(R.id.emailIdTIL) }
    private val emailIdET: TextInputEditText by lazy { findViewById(R.id.emailIdET) }

    private val contactNumberTIL: TextInputLayout by lazy { findViewById(R.id.contactNumberTIL) }
    private val contactNumberET: TextInputEditText by lazy { findViewById(R.id.contactNumberET) }

    private val identificationNumberTIL: TextInputLayout by lazy { findViewById(R.id.identificationNumberTIL) }
    private val identificationNumberET: TextInputEditText by lazy { findViewById(R.id.identificationNumberET) }

    private val dobTIL: TextInputLayout by lazy { findViewById(R.id.dobTIL) }
    private val dobET: TextInputEditText by lazy { findViewById(R.id.dobET) }

    private val intrestTIL: TextInputLayout by lazy { findViewById(R.id.intrestTIL) }
    private val intrestSpinner: MaterialAutoCompleteTextView by lazy { findViewById(R.id.intrestSpinner) }

    private val emirateIdTIL: TextInputLayout by lazy { findViewById(R.id.emirateIdTIL) }
    private val emirateIdET: TextInputEditText by lazy { findViewById(R.id.emirateIdET) }

    private val residentialAreaTIL: TextInputLayout by lazy { findViewById(R.id.residentialAreaTIL) }
    private val residentialAreaET: TextInputEditText by lazy { findViewById(R.id.residentialAreaET) }

    private val passwordTIL: TextInputLayout by lazy { findViewById(R.id.passwordTIL) }
    private val passwordET: TextInputEditText by lazy { findViewById(R.id.passwordET) }

    private val confirmPasswordTIL: TextInputLayout by lazy { findViewById(R.id.confirmPasswordTIL) }
    private val confirmPasswordET: TextInputEditText by lazy { findViewById(R.id.confirmPasswordET) }

    private val arabicTIL: TextInputLayout by lazy { findViewById(R.id.arabicTIL) }
    private val arabicSpinner: MaterialAutoCompleteTextView by lazy { findViewById(R.id.arabicSpinner) }

    private val englishTIL: TextInputLayout by lazy { findViewById(R.id.englishTIL) }
    private val englishSpinner: MaterialAutoCompleteTextView by lazy { findViewById(R.id.englishSpinner) }

    private val mathsTIL: TextInputLayout by lazy { findViewById(R.id.mathsTIL) }
    private val mathsSpinner: MaterialAutoCompleteTextView by lazy { findViewById(R.id.mathsSpinner) }

    private val chemistryTIL: TextInputLayout by lazy { findViewById(R.id.chemistryTIL) }
    private val chemistrySpinner: MaterialAutoCompleteTextView by lazy { findViewById(R.id.chemistrySpinner) }

    private val physicsTIL: TextInputLayout by lazy { findViewById(R.id.physicsTIL) }
    private val physicsSpinner: MaterialAutoCompleteTextView by lazy { findViewById(R.id.physicsSpinner) }

    private val biologyTIL: TextInputLayout by lazy { findViewById(R.id.biologyTIL) }
    private val biologySpinner: MaterialAutoCompleteTextView by lazy { findViewById(R.id.biologySpinner) }

    private val btn_signup: Button by lazy { findViewById(R.id.btn_signup) }
    private val commonTitle: TextView by lazy { findViewById(R.id.commonTitle) }
    private val backButton: ImageView by lazy { findViewById(R.id.backButton) }

    val smileyRemover = SmileyRemover()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        val schoolNameArray = resources.getStringArray(R.array.school_name_array).toList()
        val interestArray = resources.getStringArray(R.array.intrest_array).toList()
        val gradeArray = resources.getStringArray(R.array.grade_array).toList()
        val classArray = resources.getStringArray(R.array.class_array).toList()

        val schoolAdapter = SpinnerAdapter(this, schoolNameArray)
        val interestAdapter = SpinnerAdapter(this, interestArray)
        val gradeAdapter = SpinnerAdapter(this, gradeArray)
        val classAdapter = SpinnerAdapter(this, classArray)

        commonTitle.text=resources.getString(R.string.Signup)

        val logout = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.logout)
        val language = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.language)

        logout.visibility=View.GONE

        language.setOnClickListener {
            showLanguageChangeDialog(SignupActivity::class.java)
        }

        backButton.setOnClickListener {
            finish()
        }

        intrestSpinner.setAdapter(interestAdapter)
        school_name_spinner.setAdapter(schoolAdapter)
        arabicSpinner.setAdapter(gradeAdapter)
        englishSpinner.setAdapter(gradeAdapter)
        mathsSpinner.setAdapter(gradeAdapter)
        chemistrySpinner.setAdapter(gradeAdapter)
        physicsSpinner.setAdapter(gradeAdapter)
        biologySpinner.setAdapter(gradeAdapter)
        classSpinner.setAdapter(classAdapter)

        ///fetchSchool()

        nameET.filters = arrayOf<InputFilter>(smileyRemover)
        studentNumberET.filters = arrayOf<InputFilter>(smileyRemover)
        educationalPathET.filters = arrayOf<InputFilter>(smileyRemover)
        emailIdET.filters = arrayOf<InputFilter>(smileyRemover)
        contactNumberET.filters = arrayOf<InputFilter>(smileyRemover)
        identificationNumberET.filters = arrayOf<InputFilter>(smileyRemover)
        emirateIdET.filters = arrayOf<InputFilter>(smileyRemover)
        residentialAreaET.filters = arrayOf<InputFilter>(smileyRemover)
        passwordET.filters = arrayOf<InputFilter>(smileyRemover)
        confirmPasswordET.filters = arrayOf<InputFilter>(smileyRemover)
        emirateIdET.filters += InputFilter.LengthFilter(15)
        contactNumberET.filters += InputFilter.LengthFilter(10)

        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            dobET.setText(sdf.format(cal.time))
        }

        dobET.setOnClickListener {
            val datePicker = DatePickerDialog(this@SignupActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))
            datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePicker.show()
        }


        nameET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                nameTIL.error = null
        }

        studentNumberET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                studentNumberTIL.error = null
        }

        educationalPathET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                educationalPathTIL.error = null
        }

        emailIdET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                emailIdTIL.error = null
        }

        contactNumberET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                contactNumberTIL.error = null
        }

        identificationNumberET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                identificationNumberTIL.error = null
        }

        dobET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                dobTIL.error = null
        }

        emirateIdET.doAfterTextChanged {
            if (it.toString().isNotEmpty() || it.toString().length >= 15)
                emirateIdTIL.error = null
            else if (it.toString().length < 15)
                emirateIdTIL.error = resources.getString(R.string.fill_valid_emirate_id)
        }

        residentialAreaET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                residentialAreaTIL.error = null
        }

        passwordET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                passwordTIL.error = null
        }

        confirmPasswordET.doAfterTextChanged {
            if (it.toString().isNotEmpty())
                confirmPasswordTIL.error = null
        }

        school_name_spinner.setOnItemClickListener { adapterView, view, i, l ->
            if (adapterView.getItemAtPosition(i).toString().isNotEmpty()) {
                schoolNameTIL.error = null
            }
        }

        classSpinner.setOnItemClickListener { adapterView, view, i, l ->
            if (adapterView.getItemAtPosition(i).toString().isNotEmpty()) {
                classTIL.error = null
            }
        }

        intrestSpinner.setOnItemClickListener { adapterView, view, i, l ->
            if (adapterView.getItemAtPosition(i).toString().isNotEmpty()) {
                intrestTIL.error = null
            }
        }

        arabicSpinner.setOnItemClickListener { adapterView, view, i, l ->
            if (adapterView.getItemAtPosition(i).toString().isNotEmpty()) {
                arabicTIL.error = null
            }
        }

        englishSpinner.setOnItemClickListener { adapterView, view, i, l ->
            if (adapterView.getItemAtPosition(i).toString().isNotEmpty()) {
                englishTIL.error = null
            }
        }

        mathsSpinner.setOnItemClickListener { adapterView, view, i, l ->
            if (adapterView.getItemAtPosition(i).toString().isNotEmpty()) {
                mathsTIL.error = null
            }
        }

        chemistrySpinner.setOnItemClickListener { adapterView, view, i, l ->
            if (adapterView.getItemAtPosition(i).toString().isNotEmpty()) {
                chemistryTIL.error = null
            }
        }

        physicsSpinner.setOnItemClickListener { adapterView, view, i, l ->
            if (adapterView.getItemAtPosition(i).toString().isNotEmpty()) {
                physicsTIL.error = null
            }
        }

        biologySpinner.setOnItemClickListener { adapterView, view, i, l ->
            if (adapterView.getItemAtPosition(i).toString().isNotEmpty()) {
                biologyTIL.error = null
            }
        }

        btn_signup.setOnClickListener {
            if (isValidInput()) {
                Toast.makeText(this, "Valid input", Toast.LENGTH_LONG).show()
                val signUpModel = SignupModel()
                signUpModel.name=nameET.text.toString().trim()
                signUpModel.studentNumber=studentNumberET.text.toString().trim()
                signUpModel.schoolName=school_name_spinner.text.toString().trim()
                signUpModel.educationalPath=educationalPathET.text.toString().trim()
                signUpModel.className=classSpinner.text.toString().trim()
                signUpModel.contactNumber=contactNumberET.text.toString().trim()
                signUpModel.identificationNumber=identificationNumberET.text.toString().trim()
                signUpModel.dob=dobET.text.toString().trim()
                signUpModel.emailId=emailIdET.text.toString().trim()
                signUpModel.intrest=intrestSpinner.text.toString().trim()
                signUpModel.emirateId=emirateIdET.text.toString().trim()
                signUpModel.residentialArea=residentialAreaET.text.toString().trim()
                signUpModel.password=passwordET.text.toString().trim()
                signUpModel.arabic=arabicSpinner.text.toString().trim()
                signUpModel.english=englishSpinner.text.toString().trim()
                signUpModel.maths=mathsSpinner.text.toString().trim()
                signUpModel.chemistry=chemistrySpinner.text.toString().trim()
                signUpModel.physics=physicsSpinner.text.toString().trim()
                signUpModel.biology=biologySpinner.text.toString().trim()

               Log.w("Success","Sign up request ::::: "+ Gson().toJson(signUpModel).toString())
                showProgress(resources.getString(R.string.signup_progress))
                val request = ServiceBuilder.buildService(ApiInterface::class.java)
                val call = request.register(nameET.text.toString(), studentNumberET.text.toString(),
                    school_name_spinner.text.toString(), educationalPathET.text.toString(),
                    classSpinner.text.toString(), contactNumberET.text.toString(),
                    identificationNumberET.text.toString(), dobET.text.toString(),
                    emailIdET.text.toString(), intrestSpinner.text.toString(),
                    emirateIdET.text.toString(), residentialAreaET.text.toString(),
                    passwordET.text.toString(), arabicSpinner.text.toString(),
                    englishSpinner.text.toString(), mathsSpinner.text.toString(),
                    chemistrySpinner.text.toString(), physicsSpinner.text.toString(),
                    biologySpinner.text.toString())
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
                                this@SignupActivity,
                                json.getString("msg"),
                                Toast.LENGTH_LONG
                            ).show()
                            if (json.getBoolean("status")) {
                                SessionManager.storeSessionStringvalue(applicationContext,
                                    AppConstant.LOGIN_SESSION_NAME,
                                    AppConstant.LOGIN_SESSION_USER_ID,emirateIdET.text.toString())
                                callRegisterActivity()
                            } else {
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("error", t.localizedMessage)
                        hideProgress()
                    }
                })
                //finish()
            }
        }
    }

    fun isValidInput(): Boolean {
        if (nameET.text.toString().isEmpty() && studentNumberET.text.toString()
                .isEmpty() && school_name_spinner.text.toString().isEmpty() &&
            educationalPathET.text.toString().isEmpty() && classSpinner.text.toString()
                .isEmpty() && emailIdET.text.toString().isEmpty() &&
            contactNumberET.text.toString().isEmpty() && identificationNumberET.text.toString()
                .isEmpty() && dobET.text.toString().isEmpty() &&
            intrestSpinner.text.toString().isEmpty() && emirateIdET.text.toString()
                .isEmpty() && residentialAreaET.text.toString().isEmpty() &&
            passwordET.text.toString().isEmpty() && confirmPasswordET.text.toString()
                .isEmpty() && arabicSpinner.text.toString().isEmpty() &&
            englishSpinner.text.toString().isEmpty() && mathsSpinner.text.toString()
                .isEmpty() && chemistrySpinner.text.toString().isEmpty() &&
            physicsSpinner.text.toString().isEmpty() && biologySpinner.text.toString().isEmpty()
        ) {
            nameTIL.error = resources.getString(R.string.fill_name)
            studentNumberTIL.error =resources.getString(R.string.fill_student_number)
            schoolNameTIL.error = resources.getString(R.string.fill_schl_name)
            educationalPathTIL.error = resources.getString(R.string.fill_edu_path)
            classTIL.error = resources.getString(R.string.select_class)
            emailIdTIL.error =resources.getString(R.string.fill_email_id)
            contactNumberTIL.error = resources.getString(R.string.fill_contact_num)
            identificationNumberTIL.error = resources.getString(R.string.fill_identifiction_num)
            dobTIL.error = resources.getString(R.string.fill_dob)
            intrestTIL.error = resources.getString(R.string.fill_interest)
            emirateIdTIL.error = resources.getString(R.string.fill_emirate_id)
            residentialAreaTIL.error = resources.getString(R.string.fill_residential_area)
            passwordTIL.error = resources.getString(R.string.fill_password)
            confirmPasswordTIL.error = resources.getString(R.string.fill_confirm_password)
            arabicTIL.error = resources.getString(R.string.select_arabic)
            englishTIL.error = resources.getString(R.string.select_english)
            mathsTIL.error = resources.getString(R.string.select_maths)
            chemistryTIL.error = resources.getString(R.string.select_chemistry)
            physicsTIL.error = resources.getString(R.string.select_physics)
            biologyTIL.error = resources.getString(R.string.select_biology)
            return false
        } else if (nameET.text.toString().isEmpty() || studentNumberET.text.toString()
                .isEmpty() || school_name_spinner.text.toString().isEmpty() ||
            educationalPathET.text.toString().isEmpty() || classSpinner.text.toString()
                .isEmpty() || emailIdET.text.toString().isEmpty() ||
            contactNumberET.text.toString().isEmpty() || identificationNumberET.text.toString()
                .isEmpty() || dobET.text.toString().isEmpty() ||
            intrestSpinner.text.toString().isEmpty() || emirateIdET.text.toString()
                .isEmpty() || residentialAreaET.text.toString().isEmpty() ||
            passwordET.text.toString().isEmpty() || confirmPasswordET.text.toString()
                .isEmpty() || arabicSpinner.text.toString().isEmpty() ||
            englishSpinner.text.toString().isEmpty() || mathsSpinner.text.toString()
                .isEmpty() || chemistrySpinner.text.toString().isEmpty() ||
            physicsSpinner.text.toString().isEmpty() || biologySpinner.text.toString().isEmpty()
        ) {

            if (nameET.text.toString().isEmpty()) {
                nameTIL.error = resources.getString(R.string.fill_name)
            }
            if (studentNumberET.text.toString().isEmpty()) {
                studentNumberTIL.error =resources.getString(R.string.fill_student_number)
            }
            if (school_name_spinner.text.toString().isEmpty()) {
                schoolNameTIL.error = resources.getString(R.string.fill_schl_name)
            }
            if (educationalPathET.text.toString().isEmpty()) {
                educationalPathTIL.error = resources.getString(R.string.fill_edu_path)
            }
            if (classSpinner.text.toString().isEmpty()) {
                classTIL.error = resources.getString(R.string.select_class)
            }
            if (emailIdET.text.toString().isEmpty()) {
                emailIdTIL.error =resources.getString(R.string.fill_email_id)
            }
            if (contactNumberET.text.toString().isEmpty()) {
                contactNumberTIL.error = resources.getString(R.string.fill_contact_num)
            }
            if (identificationNumberET.text.toString().isEmpty()) {
                identificationNumberTIL.error = resources.getString(R.string.fill_identifiction_num)
            }
            if (dobET.text.toString().isEmpty()) {
                dobTIL.error = resources.getString(R.string.fill_dob)
            }
            if (intrestSpinner.text.toString().isEmpty()) {
                intrestTIL.error = resources.getString(R.string.fill_interest)
            }
            if (emirateIdET.text.toString().isEmpty()) {
                emirateIdTIL.error = resources.getString(R.string.fill_emirate_id)
            }
            if (residentialAreaET.text.toString().isEmpty()) {
                residentialAreaTIL.error = resources.getString(R.string.fill_residential_area)
            }
            if (passwordET.text.toString().isEmpty()) {
                passwordTIL.error = resources.getString(R.string.fill_password)
            }
            if (confirmPasswordET.text.toString().isEmpty()) {
                confirmPasswordTIL.error = resources.getString(R.string.fill_confirm_password)
            }
            if (arabicSpinner.text.toString().isEmpty()) {
                arabicTIL.error = resources.getString(R.string.select_arabic)
            }
            if (englishSpinner.text.toString().isEmpty()) {
                englishTIL.error = resources.getString(R.string.select_english)
            }
            if (mathsSpinner.text.toString().isEmpty()) {
                mathsTIL.error = resources.getString(R.string.select_maths)
            }
            if (chemistrySpinner.text.toString().isEmpty()) {
                chemistryTIL.error = resources.getString(R.string.select_chemistry)
            }
            if (physicsSpinner.text.toString().isEmpty()) {
                physicsTIL.error = resources.getString(R.string.select_physics)
            }
            if (biologySpinner.text.toString().isEmpty()) {
                biologyTIL.error = resources.getString(R.string.select_biology)
            }
            return false
        } else {
            if (emirateIdET.text.toString().length < 15) {
                emirateIdTIL.error = resources.getString(R.string.fill_valid_emirate_id)
                return false
            } else if (confirmPasswordET.text.toString() != passwordET.text.toString()) {
                confirmPasswordTIL.error = resources.getString(R.string.fill_valid_confirm_password)
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailIdET.text.toString().trim())
                    .matches()
            ) {
                emailIdTIL.error = resources.getString(R.string.fill_valid_emirate_id)
                return false
            }
        }
        return true
    }

    fun callRegisterActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        this.startActivity(intent)
        finish()
    }
}