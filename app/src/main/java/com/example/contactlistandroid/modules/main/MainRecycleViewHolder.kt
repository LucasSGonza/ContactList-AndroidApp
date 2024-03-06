package com.example.contactlistandroid.modules.main

import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistandroid.databinding.ItemLayoutBinding
import com.example.contactlistandroid.model.Contact

class MainRecycleViewHolder(
    private val binding: ItemLayoutBinding,
    private val listener: RecyclerViewListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contact: Contact) {
        with(binding) {
            cardName.text = contact.name
            cardPhone.text = contact.phoneNumber
            cardLayout.setOnClickListener {
                listener.onContactClicked(contact)
            }
        }
    }

}