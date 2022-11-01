package com.emirate.youth.eya.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.utils.SpinnerListener
import com.emirate.youth.eya.utils.model.QuestionModel
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class QuestionAdapter(var mContext: Activity,var mSpinnerListener: SpinnerListener) : RecyclerView.Adapter<RecyclerView.ViewHolder> (){

    var questionList = ArrayList<QuestionModel>()
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

    fun setItems(subItemList: List<QuestionModel>?){
        questionList.clear()
        subItemList?.let {
            Log.w("Success","subItemList :::: "+subItemList.size)
            questionList.addAll(it)
            Log.e("ItemsList", it.toString())
        }
        Log.e("itemsize", "setItems:{${questionList.size}}")
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is QuestionHolder){
            val gradeArray = mContext.resources.getStringArray(R.array.evaluation_array).toList()
            val gradeAdapter = SpinnerAdapter(mContext, gradeArray)
            holder.answerSpinner.setAdapter(gradeAdapter)

            holder.answerSpinner.setOnItemClickListener { adapterView, view, i, l ->
                if (adapterView.getItemAtPosition(i).toString().isNotEmpty()) {
                    mSpinnerListener.onItemSelectListener(adapterView.getItemAtPosition(i).toString(),position)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val skillTV : AppCompatTextView =itemView.findViewById(R.id.skillTV)
        val detailTV: AppCompatTextView = itemView.findViewById(R.id.detailTV)
        val exampleTV: AppCompatTextView = itemView.findViewById(R.id.exampleTV)
        val answerSpinner: MaterialAutoCompleteTextView = itemView.findViewById(R.id.answerSpinner)
    }
}