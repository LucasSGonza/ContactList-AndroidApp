package com.example.contactlistandroid.modules.main

import androidx.lifecycle.ViewModel
import com.example.contactlistandroid.model.Contact
import com.example.contactlistandroid.model.ContactList

class MainViewModel : ViewModel() {
    private var contactListInstance = ContactList

    fun getCopyOfList(): MutableList<Contact> {
        return contactListInstance.getCopyOfContactList()
    }

    fun deleteContact(position: Int) {
        contactListInstance.deleteContact(position)
    }

}