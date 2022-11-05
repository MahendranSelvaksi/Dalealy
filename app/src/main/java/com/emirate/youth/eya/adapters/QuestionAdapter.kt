package com.emirate.youth.eya.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.question.Question2Activity
import com.emirate.youth.eya.question.Question3Activity
import com.emirate.youth.eya.question.Question4Activity
import com.emirate.youth.eya.question.QuestionActivity
import com.emirate.youth.eya.utils.AppConstant
import com.emirate.youth.eya.utils.SpinnerListener
import com.emirate.youth.eya.utils.model.QuestionModel
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class QuestionAdapter(var mContext: Activity, var mSpinnerListener: SpinnerListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var questionList = ArrayList<QuestionModel>()
    var isArabic: Boolean = false
    private fun getLayoutInflater(parent: ViewGroup, layout: Int): View {
        return LayoutInflater.from(
            parent.context
        ).inflate(layout, parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QuestionHolder(
            getLayoutInflater(
                parent,
                R.layout.question_adapter
            )
        )
    }

    fun updateLanguage(lang: String) {
        isArabic = lang == AppConstant.LANGUAGE_ARABIC
        notifyDataSetChanged()
    }

    fun setItems(subItemList: List<QuestionModel>?) {
        questionList.clear()
        subItemList?.let {
            Log.w("Success", "subItemList :::: " + subItemList.size)
            questionList.addAll(it)
            Log.e("ItemsList", it.toString())
        }
        Log.e("itemsize", "setItems:{${questionList.size}}")
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is QuestionHolder) {
            val gradeArray = mContext.resources.getStringArray(R.array.evaluation_array).toList()
            val gradeAdapter = SpinnerAdapter(mContext, gradeArray)

            when (mContext) {
                is QuestionActivity -> {
                    holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.cat_1_bg))
                }
                is Question2Activity -> {
                    holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.cat_2_bg))
                }
                is Question3Activity -> {
                    holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.cat_3_bg))
                }
                is Question4Activity -> {
                    holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.cat_4_bg))
                }
            }

            if (questionList[position].given_answer.isNotEmpty()){
                when(questionList[position].given_answer){
                    "1"-> holder.rb1.isChecked=true
                    "2"-> holder.rb2.isChecked=true
                    "3"-> holder.rb3.isChecked=true
                    "4"-> holder.rb4.isChecked=true
                    "5"-> holder.rb5.isChecked=true
                }
            }else{
                holder.rg.clearCheck()
            }

            if (isArabic) {
                holder.skillTV.text = questionList[position].ques_ar
                holder.detailTV.text = questionList[position].ques_desc_ar
                holder.exampleTV.text = questionList[position].ques_ex_ar
            } else {
                holder.skillTV.text = questionList[position].ques
                holder.detailTV.text = questionList[position].ques_desc
                holder.exampleTV.text = questionList[position].ques_ex
            }

            holder.rb1.setOnClickListener {
                mSpinnerListener.onItemSelectListener(
                    "1", position
                )
            }
            holder.rb2.setOnClickListener {
                mSpinnerListener.onItemSelectListener(
                    "2", position
                )
            }
            holder.rb3.setOnClickListener {
                mSpinnerListener.onItemSelectListener(
                    "3", position
                )
            }
            holder.rb4.setOnClickListener {
                mSpinnerListener.onItemSelectListener(
                    "4", position
                )
            }
            holder.rb5.setOnClickListener {
                mSpinnerListener.onItemSelectListener(
                    "5", position
                )
            }

        }

    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: androidx.cardview.widget.CardView = itemView.findViewById(R.id.cardView)
        val skillTV: AppCompatTextView = itemView.findViewById(R.id.skillTV)
        val detailTV: AppCompatTextView = itemView.findViewById(R.id.detailTV)
        val exampleTV: AppCompatTextView = itemView.findViewById(R.id.exampleTV)
        val rg: RadioGroup = itemView.findViewById(R.id.rg)
        val rb1: RadioButton = itemView.findViewById(R.id.rb1)
        val rb2: RadioButton = itemView.findViewById(R.id.rb2)
        val rb3: RadioButton = itemView.findViewById(R.id.rb3)
        val rb4: RadioButton = itemView.findViewById(R.id.rb4)
        val rb5: RadioButton = itemView.findViewById(R.id.rb5)
    }
}