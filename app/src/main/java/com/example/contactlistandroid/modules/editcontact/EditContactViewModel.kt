package com.example.contactlistandroid.modules.editcontact

import androidx.lifecycle.ViewModel
import com.example.contactlistandroid.model.Contact
import com.example.contactlistandroid.model.ContactList

class EditContactViewModel : ViewModel() {
    private var contactListInstance = ContactList

    fun editContact(contact: Contact): Contact? {
        return contactListInstance.editContact(contact)
    }

}