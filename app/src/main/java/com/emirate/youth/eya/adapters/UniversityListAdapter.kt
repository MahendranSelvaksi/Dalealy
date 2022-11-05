package com.emirate.youth.eya.adapters

import android.app.Activity
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.utils.model.LoginModel
import com.emirate.youth.eya.utils.model.QuestionModel

class UniversityListAdapter(
    var mListener: NavigateBrowserListener,
    var mContext: Activity
) :    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var universityList = ArrayList<LoginModel>()
    private fun getLayoutInflater(parent: ViewGroup, layout: Int): View {
        return LayoutInflater.from(
            parent.context
        ).inflate(layout, parent, false)
    }

    fun setItems(subItemList: List<LoginModel>?) {
        universityList.clear()
        subItemList?.let {
            Log.w("Success", "subItemList :::: " + subItemList.size)
            universityList.addAll(it)
            Log.e("ItemsList", it.toString())
        }
        Log.e("itemsize", "setItems:{${universityList.size}}")
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
            holder.uniNameTV.text=universityList[position].emirateId.toString()

            val mString = mContext.resources.getString(R.string.link_caption)
            val mSpannableString = SpannableString(mString)
            mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
            holder.linkTV.text = mSpannableString

            holder.linkTV.setOnClickListener {
                mListener.navigateToBrowser(universityList[position].password.toString(),position)
            }
        }
    }

    override fun getItemCount(): Int {
        return universityList.size
    }

    class UniversityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val uniNameTV: AppCompatTextView = itemView.findViewById(R.id.uniNameTV)
        val linkTV: AppCompatTextView = itemView.findViewById(R.id.linkTV)
    }


    interface NavigateBrowserListener {
        abstract fun navigateToBrowser(value:String,position:Int)
    }
}

