package com.emirate.youth.eya.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.emirate.youth.eya.R
import com.emirate.youth.eya.utils.model.LoginModel
import com.emirate.youth.eya.utils.model.SignupModel

class StudentListAdapter(
    var mListener: DashBoardAdapter.NavigateListerner,
    var mContext: Activity
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var studentsList = ArrayList<SignupModel>()

    fun setItems(subItemList: List<SignupModel>?) {
        studentsList.clear()
        subItemList?.let {
            Log.w("Success", "subItemList :::: " + subItemList.size)
            studentsList.addAll(it)
            Log.e("ItemsList", it.toString())
        }
        Log.e("itemsize", "setItems:{${studentsList.size}}")
        notifyDataSetChanged()
    }

    private fun getLayoutInflater(parent: ViewGroup, layout: Int): View {
        return LayoutInflater.from(
            parent.context
        ).inflate(layout, parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StudentHolder(
            getLayoutInflater(
                parent,
                R.layout.student_list_adapter
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StudentHolder) {
            holder.snoTV.text = (position + 1).toString()
            holder.nameTV.text=studentsList[position].name.toString().trim()
            holder.emirateIdTV.text=studentsList[position].emirateId.toString().trim()
            holder.residentialAreaTV.text=studentsList[position].residentialArea.toString().trim()
        }
    }

    override fun getItemCount(): Int {
        return studentsList.size
    }

    class StudentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val snoTV: AppCompatTextView = itemView.findViewById(R.id.snoTV)
        val nameTV: AppCompatTextView = itemView.findViewById(R.id.nameTV)
        val emirateIdTV: AppCompatTextView = itemView.findViewById(R.id.emirateIdTV)
        val residentialAreaTV: AppCompatTextView = itemView.findViewById(R.id.residentialAreaTV)
    }
}