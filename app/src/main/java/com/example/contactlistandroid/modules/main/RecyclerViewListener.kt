package com.example.contactlistandroid.modules.main

import com.example.contactlistandroid.model.Contact

interface RecyclerViewListener {

    fun onContactClicked(contact: Contact)

}