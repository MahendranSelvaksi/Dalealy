package com.emirate.youth.eya.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R

class DashBoardAdapter(var mListener: NavigateListerner,var mContext: Activity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private fun getLayoutInflater(parent: ViewGroup, layout: Int): View {
        return LayoutInflater.from(
            parent.context
        ).inflate(layout, parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DashboardHolder(
            getLayoutInflater(
                parent,
                R.layout.dashborad_adapter
            )
        )
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DashboardHolder) {
            when (position) {
                0 -> {
                    holder.evaluationTV.text = mContext.resources.getString(R.string.very_week)
                    holder.skillTV.text =  mContext.resources.getString(R.string.very_week_skill)
                    holder.ratingTV.text = "1"
                    holder.exampleTV.text = mContext.resources.getString(R.string.very_week_ex)
                }
                1 -> {
                    holder.evaluationTV.text =  mContext.resources.getString(R.string.week)
                    holder.skillTV.text =mContext.resources.getString(R.string.week_skill)
                    holder.ratingTV.text = "2"
                    holder.exampleTV.text = mContext.resources.getString(R.string.week_ex)
                }
                2 -> {
                    holder.evaluationTV.text = mContext.resources.getString(R.string.moderate)
                    holder.skillTV.text =  mContext.resources.getString(R.string.moderate_skill)
                    holder.ratingTV.text = "3"
                    holder.exampleTV.text =  mContext.resources.getString(R.string.moderate_ex)
                }
                3 -> {
                    holder.evaluationTV.text =  mContext.resources.getString(R.string.strong)
                    holder.skillTV.text =  mContext.resources.getString(R.string.strong_skill)
                    holder.ratingTV.text = "4"
                    holder.exampleTV.text =  mContext.resources.getString(R.string.strong_ex)
                }
                4 -> {
                    holder.evaluationTV.text =  mContext.resources.getString(R.string.very_capable)
                    holder.skillTV.text = mContext.resources.getString(R.string.very_capable_skill)
                    holder.ratingTV.text = "5"
                    holder.exampleTV.text = mContext.resources.getString(R.string.very_capable_ex)
                }
            }

          /*  holder.dashBoardHolder.setOnClickListener {
                mListener.navigateToQuestion()
            }*/
        }

    }

    class DashboardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val evaluationTV: AppCompatTextView = itemView.findViewById(R.id.evaluationTV)
        val skillTV: AppCompatTextView = itemView.findViewById(R.id.skillTV)
        val ratingTV: AppCompatTextView = itemView.findViewById(R.id.ratingTV)
        val exampleTV: AppCompatTextView = itemView.findViewById(R.id.exampleTV)
        val dashBoardHolder: CardView = itemView.findViewById(R.id.dashBoardHolder)
    }

    interface NavigateListerner {
        abstract fun navigateToQuestion()
    }
}