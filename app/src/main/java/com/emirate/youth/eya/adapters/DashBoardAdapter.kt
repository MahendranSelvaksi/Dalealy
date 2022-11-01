package com.emirate.youth.eya.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R

class DashBoardAdapter(var mListener: NavigateListerner) :
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
                    holder.evaluationTV.text = "Very weak"
                    holder.skillTV.text = "The skill is simple and rare"
                    holder.ratingTV.text = "1"
                    holder.exampleTV.text = "Accuracy: Inaccurate in most of my work and I often find errors after completion"
                }
                1 -> {
                    holder.evaluationTV.text = " weak"
                    holder.skillTV.text = "The skill is apparent but below average"
                    holder.ratingTV.text = "2"
                    holder.exampleTV.text = "Accuracy: On some important things, I try to be accurate and really get accurate"
                }
                2 -> {
                    holder.evaluationTV.text = "Moderate"
                    holder.skillTV.text = "The skill is shown on average"
                    holder.ratingTV.text = "3"
                    holder.exampleTV.text = "Accuracy: I am accurate in important matters and inaccurate in other matters"
                }
                3 -> {
                    holder.evaluationTV.text = "strong"
                    holder.skillTV.text = "The skill is clearly visible"
                    holder.ratingTV.text = "4"
                    holder.exampleTV.text = "Accuracy: I am accurate in most matters of my personal and professional life without getting tired"
                }
                4 -> {
                    holder.evaluationTV.text = "very capable"
                    holder.skillTV.text = "The skill is definitely visible and very high"
                    holder.ratingTV.text = "5"
                    holder.exampleTV.text = "Accuracy: I am very accurate in all the details of my life and work automatically and I enjoy it"
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