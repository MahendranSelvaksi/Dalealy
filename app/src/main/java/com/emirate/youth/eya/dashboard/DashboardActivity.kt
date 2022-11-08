package com.emirate.youth.eya.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.adapters.DashBoardAdapter
import com.emirate.youth.eya.question.QuestionActivity
import com.emirate.youth.eya.utils.BaseActivity

class DashboardActivity : BaseActivity(), DashBoardAdapter.NavigateListerner {

    var mDashBoardAdapter = DashBoardAdapter(this,this)

    private val commonTitle: TextView by lazy { findViewById(R.id.commonTitle) }
    private val backButton: ImageView by lazy { findViewById(R.id.backButton) }
    private val evaluationTV: androidx.appcompat.widget.AppCompatTextView by lazy { findViewById(R.id.evaluationTV) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        val logout = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.logout)
        val language = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.language)
        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        val btn_next = findViewById<Button>(R.id.btn_next)
        commonTitle.text=resources.getString(R.string.dash_board)
        evaluationTV.text=resources.getString(R.string.dash_board_caption)

        logout.setOnClickListener {
            showLogoutDialog()
        }

        language.setOnClickListener {
            showLanguageChangeDialog(DashboardActivity::class.java)
        }

        backButton.setOnClickListener {
            finish()
        }

        mRecyclerView.adapter=mDashBoardAdapter

        btn_next.setOnClickListener { navigateToQuestion() }
    }

    override fun navigateToQuestion() {
        val intent = Intent(this, QuestionActivity::class.java)
        this.startActivity(intent)
    }


    /* @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.w("Success","isChecked:::"+isChecked+(isChecked ? LocaleManager.LANGUAGE_ENGLISH : LocaleManager.LANGUAGE_TAMIL));
    }*/

}