package com.example.contactlistandroid.modules.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistandroid.databinding.ItemLayoutBinding
import com.example.contactlistandroid.model.Contact

class MainRecycleAdapter(
    private val listener: RecyclerViewListener
) : RecyclerView.Adapter<MainRecycleViewHolder>() {

    private var contactList: MutableList<Contact> = mutableListOf()

    //define the view of each item in the recycle
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecycleViewHolder {
        val holder = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainRecycleViewHolder(holder, listener)
    }

    //return the total of items in the recycler
    override fun getItemCount(): Int {
        return contactList.size
    }

    //define the data for each item in the recycle
    override fun onBindViewHolder(holder: MainRecycleViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateContactList(contactList: MutableList<Contact>) {
        this.contactList.apply {
            clear()
            addAll(contactList)
        }
        notifyDataSetChanged()
    }

    fun deleteContact(adapterPosition: Int) {
        this.contactList.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

}