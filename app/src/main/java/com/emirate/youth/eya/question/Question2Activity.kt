package com.emirate.youth.eya.question

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.QuestionAdapter
import com.emirate.youth.eya.dashboard.DashboardActivity
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.NetworkHelper
import com.emirate.youth.eya.utils.SpinnerListener
import com.emirate.youth.eya.utils.model.QuestionModel
import com.emirate.youth.eya.utils.network.ApiInterface
import com.emirate.youth.eya.utils.network.ServiceBuilder
import kotlinx.android.synthetic.main.common_toolbar.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.util.*

class Question2Activity : BaseActivity(), SpinnerListener {

    var questionList = mutableListOf<QuestionModel>()
    var mQuestionAdapter = QuestionAdapter(this, this)

    var cat1Score:String= ""

    private val commonTitle: TextView by lazy { findViewById(R.id.commonTitle) }
    private val backButton: ImageView by lazy { findViewById(R.id.backButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }


        cat1Score = intent.getStringExtra("cat1Score").toString()

        val logout =
            findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.logout)
        val language =
            findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.language)
        commonTitle.text = resources.getString(R.string.questions_title)

        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        backButton.setOnClickListener {
            onBackPressed()
        }
        language.visibility= View.GONE
        logout.setOnClickListener {
            showLogoutDialog()
        }

        language.setOnClickListener {
            showLanguageChangeDialog(Question2Activity::class.java)
        }

        mRecyclerView.adapter = mQuestionAdapter

        fetchQuestions()

        btn_submit.setOnClickListener {
            if (isAllQuestionAnswered()) {
                if (calculateTheMarks() > 60) {
                    showMarksDialog(calculateTheMarks().toString())
                } else {
                    Toast.makeText(this, R.string.upto_mark_msg, Toast.LENGTH_LONG).show()
                    val intent = Intent(this, Question3Activity::class.java)
                    intent.putExtra("cat1Score", cat1Score)
                    intent.putExtra("cat2Score", calculateTheMarks().toString())
                    this.startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, R.string.fill_all_questions, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showMarksDialog(mark: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.success)
        val value = String.format(
            "%s",
            resources.getString(R.string.leader_name_cat_2)
        )

        builder.setMessage(value)
        builder.setPositiveButton(
            R.string.ok
        ) { dialog, which -> // do something like...
                val intent = Intent(this, Question3Activity::class.java)
                intent.putExtra("cat1Score", cat1Score)
                intent.putExtra("cat2Score", mark)
                this.startActivity(intent)
                finish()
        }

        // create and show the alert dialog

        // create and show the alert dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun isAllQuestionAnswered(): Boolean {
        questionList.forEach {
            if (it.given_answer.isEmpty()) {
                return false
            }
        }
        return true
    }

    fun calculateTheMarks(): Int {
        var mark = 0
        questionList.forEach {
            if (it.given_answer.isNotEmpty()) {
                mark += it.given_answer.toInt()
            }
        }
        Log.w("Success", "Mark :::: " + mark)
        return mark
    }

    override fun onItemSelectListener(value: String, position: Int) {
        questionList[position].given_answer = value
    }

    override fun onBackPressed() {
        showExitConfirmationDialog()
    }

    private fun fetchQuestions() {
        if (NetworkHelper.isOnline(applicationContext)) {
            showProgress(resources.getString(R.string.question_data_load_msg))
            val request = ServiceBuilder.buildService(ApiInterface::class.java)
            val call = request.FetchQuestionnaire()
            call.enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    hideProgress()
                    if (response.isSuccessful) {
                        var str_response = response.body()!!.string()
                        //creating json object
                        val json = JSONObject(str_response)
                        Log.w("Success", "json_contact :::: " + json.toString())
                        if (json.getBoolean("status")) {
                            val jsonArray = json.getJSONArray("data")
                            Log.w("Success", "jsonArray length ::: " + jsonArray.length())
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)
                                val questionModel = QuestionModel()
                                if (jsonObject.getString("ques_cat") == "2") {
                                    questionModel.ques_no = jsonObject.getString("ques_no")
                                    questionModel.ques = jsonObject.getString("ques")
                                    questionModel.ques_ar = jsonObject.getString("ques_ar")
                                    questionModel.ques_cat = jsonObject.getString("ques_cat")
                                    questionModel.ques_desc = jsonObject.getString("ques_desc")
                                    questionModel.ques_desc_ar =
                                        jsonObject.getString("ques_desc_ar")
                                    questionModel.ques_ex = jsonObject.getString("ques_ex")
                                    questionModel.ques_ex_ar = jsonObject.getString("ques_ex_ar")
                                    questionModel.ques_type = jsonObject.getString("ques_type")
                                    questionModel.ques_value = jsonObject.getString("ques_value")
                                    Log.w("Success", "questionModel :::: " + questionModel.ques)
                                    questionList.add(questionModel)
                                }
                            }
                            Log.w("Success", "questionList ::: " + questionList.size)
                            mQuestionAdapter.setItems(questionList)
                            mQuestionAdapter.updateLanguage(selectedLang)
                        } else {

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