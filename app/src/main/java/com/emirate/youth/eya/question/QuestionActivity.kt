package com.emirate.youth.eya.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.QuestionAdapter
import java.util.*
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.SpinnerListener
import com.emirate.youth.eya.utils.model.QuestionModel
import com.emirate.youth.eya.utils.network.ApiInterface
import com.emirate.youth.eya.utils.network.ServiceBuilder
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


class QuestionActivity : BaseActivity(), SpinnerListener {


    var questionList = mutableListOf<QuestionModel>()

    var mQuestionAdapter = QuestionAdapter(this,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        val commonToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.commonToolbar)
        commonToolbar.setTitle("Questions")
        commonToolbar.setNavigationIcon(R.drawable.ic_arrow_back)

        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        commonToolbar.setNavigationOnClickListener {
            finish()
        }

        mRecyclerView.adapter=mQuestionAdapter

        fetchQuestions()

        btn_submit.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Success")

            builder.setMessage("Congrats,you are achieved ${getRandomNumber()} Marks")

            // add a button

            // add a button
            builder.setPositiveButton("OK", null)
            builder.setPositiveButton(
                "OK"
            ) { dialog, which -> // do something like...
                val intent = Intent(this, Question2Activity::class.java)
                this.startActivity(intent)
               finish()
            }

            // create and show the alert dialog

            // create and show the alert dialog
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    fun getRandomNumber() : String{
        val max = 55
        val min = 110
        val randomNum = Random()
        val showMe: Int = min + randomNum.nextInt(max)
        println(showMe)
        return showMe.toString()
    }

    override fun onItemSelectListener(value: String, position: Int) {

    }

    private fun fetchQuestions(){
        showProgress("Question data loading, Please wait...")
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
                        val jsonArray=json.getJSONArray("data")
                        Log.w("Success","jsonArray length ::: "+jsonArray.length())
                        for (i in 0 until jsonArray.length()){
                            val jsonObject = jsonArray.getJSONObject(i)
                            val questionModel=QuestionModel()
                            questionModel.ques_no=jsonObject.getString("ques_no")
                            questionModel.ques=jsonObject.getString("ques")
                            questionModel.ques_ar=jsonObject.getString("ques_ar")
                            questionModel.ques_cat=jsonObject.getString("ques_cat")
                            questionModel.ques_desc=jsonObject.getString("ques_desc")
                            questionModel.ques_desc_ar=jsonObject.getString("ques_desc_ar")
                            questionModel.ques_ex=jsonObject.getString("ques_ex")
                            questionModel.ques_ex_ar=jsonObject.getString("ques_ex_ar")
                            questionModel.ques_type=jsonObject.getString("ques_type")
                            questionModel.ques_value=jsonObject.getString("ques_value")
                            Log.w("Success","questionModel :::: "+questionModel.ques)
                            questionList.add(questionModel)
                        }
                        Log.w("Success","questionList ::: "+questionList.size)
                        mQuestionAdapter.setItems(questionList)
                    } else {

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