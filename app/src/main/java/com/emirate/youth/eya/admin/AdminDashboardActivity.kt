package com.emirate.youth.eya.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.DashBoardAdapter
import com.emirate.youth.eya.adapters.SpinnerAdapter
import com.emirate.youth.eya.adapters.StudentListAdapter
import com.emirate.youth.eya.dashboard.DashboardActivity
import com.emirate.youth.eya.utils.AppConstant
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.NetworkHelper
import com.emirate.youth.eya.utils.model.SignupModel
import com.emirate.youth.eya.utils.network.ApiInterface
import com.emirate.youth.eya.utils.network.ServiceBuilder
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.common_toolbar.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class AdminDashboardActivity : BaseActivity(), DashBoardAdapter.NavigateListerner {

    var mStudentListAdapter = StudentListAdapter(this,this)
    var studentsList = ArrayList<SignupModel>()

//    private val commonTitle: TextView by lazy { findViewById(R.id.commonTitle) }
//    private val backButton: ImageView by lazy { findViewById(R.id.backButton) }
//    private val evaluationTV: androidx.appcompat.widget.AppCompatTextView by lazy { findViewById(R.id.evaluationTV) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (supportActionBar != null) {
            supportActionBar!!.hide();
        }

//        val logout = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.logout)
//        val language = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.language)
//        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        val btn_next = findViewById<Button>(R.id.btn_next)
        commonTitle.text=resources.getString(R.string.admin_pannel)
        evaluationTV.visibility= View.GONE
        logout.visibility= View.GONE
        btn_next.visibility= View.GONE


        logout.setOnClickListener {
            showLogoutDialog()
        }

        language.setOnClickListener {
            showLanguageChangeDialog(AdminDashboardActivity::class.java)
        }

        backButton.setOnClickListener {
            val intent = Intent(this, UserNavigationActivity::class.java)
            this.startActivity(intent)
            finish()
        }

        mRecyclerView.adapter=mStudentListAdapter

        fetchAdminData()
    }

    override fun navigateToQuestion() {

    }


    private fun fetchAdminData() {
        if (NetworkHelper.isOnline(applicationContext)) {
            showProgress(resources.getString(R.string.admin_dash_progress))
            val request = ServiceBuilder.buildService(ApiInterface::class.java)
            val call = request.NewUserList()
            call.enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    hideProgress()
                    if (response.isSuccessful) {
                        val str_response = response.body()!!.string()
                        //creating json object
                        val json = JSONObject(str_response)
                        Log.w("Success", "json_contact :::: " + json.toString())
                        if (json.getBoolean("status")) {
                            val jsonArray = json.getJSONArray("data")
                            Log.w("Success", "jsonArray length ::: " + jsonArray.length())
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)
                                val mSignupModel=SignupModel()
                                mSignupModel.name=jsonObject.getString("name")
                                mSignupModel.emirateId=jsonObject.getString("emirates_id")
                                mSignupModel.residentialArea=jsonObject.getString("resident_no")
                                studentsList.add(mSignupModel)
                            }
                            mStudentListAdapter.setItems(studentsList)
                            if (studentsList.isEmpty()){
                                mRecyclerView.visibility=View.GONE
                                noDataTV.visibility=View.VISIBLE
                            }else{
                                mRecyclerView.visibility=View.VISIBLE
                                noDataTV.visibility=View.GONE
                            }
                        } else {
                            showFailureDataDialog()
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
}