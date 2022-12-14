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
import com.emirate.youth.eya.utils.model.LoginModel
import com.emirate.youth.eya.utils.model.SkillsCatModel

class SkillsAdapters(
    var mContext: Activity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var skillsList = ArrayList<SkillsCatModel>()
    private fun getLayoutInflater(parent: ViewGroup, layout: Int): View {
        return LayoutInflater.from(
            parent.context
        ).inflate(layout, parent, false)
    }

    fun setItems(subItemList: List<SkillsCatModel>?) {
        skillsList.clear()
        subItemList?.let {
            Log.w("Success", "subItemList :::: " + subItemList.size)
            skillsList.addAll(it)
            Log.e("ItemsList", it.toString())
        }
        Log.e("itemsize", "setItems:{${skillsList.size}}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UniversityHolder(
            getLayoutInflater(
                parent,
                R.layout.university_list_adapter
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UniversityHolder) {
            holder.uniNameTV.text = skillsList[position].skill.toString()
            holder.linkTV.visibility = View.GONE
            holder.uniNameTV.setTextColor(ContextCompat.getColor(
                mContext,
                R.color.white
            ))
            when ( skillsList[position].category) {
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
        }
    }

    override fun getItemCount(): Int {
        return skillsList.size
    }

    class UniversityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val uniNameTV: AppCompatTextView = itemView.findViewById(R.id.uniNameTV)
        val linkTV: AppCompatTextView = itemView.findViewById(R.id.linkTV)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }


    interface NavigateBrowserListener {
        abstract fun navigateToBrowser(value: String, position: Int)
    }
}