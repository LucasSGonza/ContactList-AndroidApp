package com.example.contactlistandroid.modules.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contactlistandroid.R
import com.example.contactlistandroid.databinding.ActivityMainBinding
import com.example.contactlistandroid.modules.newcontact.NewContactActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //    private var contactListInstance: MutableList<Contact> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
//        setupData()
//        binding.recycleForContacts.adapter = MainRecycleAdapter(contactListInstance)
        setupClickListeners()
    }

//    private fun setupData() {
//        val listOfContacts = listOf(
//            Contact(1, "lucas", "123"),
//            Contact(2, "pedro", "321"),
//            Contact(3, "carlos", "444")
//        )
//        contactListInstance.addAll(listOfContacts)
//    }

    private fun setupClickListeners() {
        binding.fabNewContact.setOnClickListener {
            startActivity(Intent(it.context, NewContactActivity::class.java))
        }

    }

}