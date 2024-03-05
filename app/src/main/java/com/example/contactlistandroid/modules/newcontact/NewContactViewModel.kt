package com.example.contactlistandroid.modules.newcontact

import androidx.lifecycle.ViewModel
import com.example.contactlistandroid.model.Contact
import com.example.contactlistandroid.model.ContactList

class NewContactViewModel : ViewModel() {

    private var contactListInstance = ContactList

    fun createContact(
        contactName: String,
        contactPhone: String
    ) {
        val contact = Contact(
            contactListInstance.getCopyOfContactList().size + 1,
            contactName,
            contactPhone
        )
        contactListInstance.createContact(contact)
    }

}