package com.example.contactlistandroid.model

object ContactList {

    private var contactList = mutableSetOf<Contact>()

    fun createContact(contact: Contact) {
        contactList.add(contact)
    }

    fun deleteContact(contact: Contact) {
        contactList.remove(contact)
    }

    fun cleanContactList() {
        contactList.clear()
    }

}