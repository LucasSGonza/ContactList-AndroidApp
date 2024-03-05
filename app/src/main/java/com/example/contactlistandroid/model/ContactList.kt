package com.example.contactlistandroid.model

object ContactList {

    private var contactList = mutableListOf<Contact>()

    fun createContact(contact: Contact) {
        contactList.add(contact)
    }

    fun deleteContact(contact: Contact) {
        contactList.remove(contact)
    }

    fun cleanContactList() {
        contactList.clear()
    }

    fun getCopyOfContactList(): MutableList<Contact> {
        return contactList.toMutableList()
    }

}