package com.t4zb.kotlinroomretrofitwithdi.view.adaptor

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.t4zb.kotlinroomretrofitwithdi.R
import com.t4zb.kotlinroomretrofitwithdi.data.model.LocalLorem
import com.t4zb.kotlinroomretrofitwithdi.viewmodel.MainActivityViewModel

class LoremAdaptor constructor(
    context: Context,
    loremList: List<LocalLorem>?,
) : RecyclerView.Adapter<LoremAdaptor.ViewHolder>(){

    private var loremList = loremList

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var loremTextView: MaterialTextView = itemView.findViewById(R.id.tv_lorem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rv_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (loremList == null)
            return

        val currentLorem = loremList!![position]
        holder.loremTextView.text = currentLorem.lorem
    }

    override fun getItemCount(): Int {
        if (loremList == null)
            return 0

        return loremList!!.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateLoremData(loremList: List<LocalLorem>) {
        this.loremList = loremList
        notifyDataSetChanged()
    }

}