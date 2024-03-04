package com.example.contactlistandroid.modules.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistandroid.R
import com.example.contactlistandroid.model.Contact

class MainRecycleAdapter(private val contactList: MutableList<Contact>) :
    RecyclerView.Adapter<MainRecycleViewHolder>() {

    //define the view of each item in the recycle
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecycleViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MainRecycleViewHolder(textView)
    }

    //return the total of items in the recycler
    override fun getItemCount(): Int {
        return contactList.size
    }

    //define the data for each item in the recycle
    override fun onBindViewHolder(holder: MainRecycleViewHolder, position: Int) {
        try {
            val contact = contactList[position]
            holder.name.text = contact.name
            holder.phone.text = contact.phoneNumber
        } catch (e: ArrayIndexOutOfBoundsException) {
            Log.e("error", "$e")
        }
    }
}