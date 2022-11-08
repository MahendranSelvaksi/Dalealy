package com.emirate.youth.eya.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.utils.model.SkillsCatModel
import android.widget.Toast

import android.widget.CompoundButton
import com.emirate.youth.eya.utils.SpinnerListener


class JobsAdapter(var mContext: Activity,var mSpinnerListener: SpinnerListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var skillsList = ArrayList<SkillsCatModel>()
    var checkedItemCount = 0


    private fun getLayoutInflater(parent: ViewGroup, layout: Int): View {
        return LayoutInflater.from(
            parent.context
        ).inflate(layout, parent, false)
    }

    fun setItems(subItemList: List<SkillsCatModel>?,checkedItemCount:Int) {
        skillsList.clear()
        subItemList?.let {
            Log.w("Success", "subItemList :::: " + subItemList.size)
            skillsList.addAll(it)
            Log.e("ItemsList", it.toString())
        }
        Log.e("itemsize", "setItems:{${skillsList.size}}")
        this.checkedItemCount=checkedItemCount
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return JobHolder(
            getLayoutInflater(
                parent,
                R.layout.jobs_adapter
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is JobHolder) {
            holder.jobNameTV.text = skillsList[position].skill.toString()
            holder.jobNameTV.setTextColor(ContextCompat.getColor(
                mContext,
                R.color.white
            ))
            holder.tickBox.isChecked = skillsList[position].isChecked==true
            when (skillsList[position].category) {
                1 -> {
                    holder.cardView.setCardBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.cat_1_text_color
                        )
                    )
                }
                2 -> {
                    holder.cardView.setCardBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.cat_2_text_color
                        )
                    )
                }
                3 -> {
                    holder.cardView.setCardBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.cat_3_text_color
                        )
                    )
                }
                4 -> {
                    holder.cardView.setCardBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.cat_4_text_color
                        )
                    )
                }
            }

            holder.tickBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                skillsList[position].isChecked=isChecked
            })
        }
    }

    override fun getItemCount(): Int {
        return skillsList.size
    }

    class JobHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jobNameTV: AppCompatTextView = itemView.findViewById(R.id.jobNameTV)
        val tickBox: androidx.appcompat.widget.AppCompatCheckBox = itemView.findViewById(R.id.tickBox)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }



}