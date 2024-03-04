package com.example.contactlistandroid.modules.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistandroid.R

class MainRecycleViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.card_name)
    val phone: TextView = view.findViewById(R.id.card_phone)
}