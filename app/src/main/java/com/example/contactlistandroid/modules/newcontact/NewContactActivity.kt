package com.example.contactlistandroid.modules.newcontact

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.contactlistandroid.R
import com.example.contactlistandroid.databinding.ActivityNewContactBinding

class NewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContactBinding
    private lateinit var newContactViewModel: NewContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        newContactViewModel = ViewModelProvider(this)[NewContactViewModel::class.java]
        setContentView(binding.root)
        supportActionBar?.hide()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.bttSaveContact.setOnClickListener {
            createContact()
            Toast.makeText(
                this,
                getString(R.string.toast_create_contact),
                Toast.LENGTH_SHORT
            ).show()
            this.finish()
        }

        binding.bttReturn.setOnClickListener {
            this.finish()
        }
    }

    private fun createContact() {
        val name = binding.nameTextInputEditText.text.toString()
        val phone = binding.phoneTextInputEditText.text.toString()
        newContactViewModel.createContact(name, phone)
    }

}