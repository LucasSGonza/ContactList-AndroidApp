package com.example.contactlistandroid.modules.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.contactlistandroid.databinding.ActivityMainBinding
import com.example.contactlistandroid.model.Contact
import com.example.contactlistandroid.modules.editcontact.EditContactActivity
import com.example.contactlistandroid.modules.newcontact.NewContactActivity

class MainActivity : AppCompatActivity(), RecyclerViewListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerAdapter: MainRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)
        supportActionBar?.hide()
        setupRecyclerView()
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        recyclerAdapter.updateContactList(mainViewModel.getCopyOfList())
    }

    private fun setupRecyclerView() {
        recyclerAdapter = MainRecycleAdapter(this)
        binding.recycleForContacts.adapter = recyclerAdapter
    }

    private fun setupClickListeners() {
        binding.fabNewContact.setOnClickListener {
            startActivity(Intent(it.context, NewContactActivity::class.java))
        }
    }

    override fun onContactClicked(contact: Contact) {
        startActivity(Intent(binding.root.context, EditContactActivity::class.java))
    }

}