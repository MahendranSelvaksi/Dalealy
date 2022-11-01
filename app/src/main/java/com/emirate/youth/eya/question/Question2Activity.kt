package com.emirate.youth.eya.question

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.QuestionAdapter
import com.emirate.youth.eya.utils.BaseActivity
import com.emirate.youth.eya.utils.SpinnerListener
import java.util.*

class Question2Activity : BaseActivity(), SpinnerListener {

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
                val intent = Intent(this, Question3Activity::class.java)
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
}