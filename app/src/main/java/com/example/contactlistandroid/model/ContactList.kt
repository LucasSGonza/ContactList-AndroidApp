package com.example.contactlistandroid.model

object ContactList {

    private var contactList = mutableListOf<Contact>()

    fun createContact(contact: Contact) {
        contactList.add(contact)
    }

    fun deleteContact(position: Int) {
        contactList.removeAt(position)
    }

    fun editContact(contact: Contact): Contact? {
        contactList.firstOrNull { it.id == contact.id }?.let {
            it.name = contact.name
            it.phoneNumber = contact.phoneNumber
            return it
        } ?: return null
    }

    fun getCopyOfContactList(): MutableList<Contact> {
        return contactList.toMutableList()
    }

}