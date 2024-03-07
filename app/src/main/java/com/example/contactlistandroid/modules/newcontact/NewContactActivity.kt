package com.example.contactlistandroid.modules.newcontact

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.contactlistandroid.R
import com.example.contactlistandroid.databinding.ActivityNewContactBinding

class NewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContactBinding
    private lateinit var newContactViewModel: NewContactViewModel
    private var isNameValid: Boolean = false
    private var isPhoneValid: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        newContactViewModel = ViewModelProvider(this)[NewContactViewModel::class.java]
        setContentView(binding.root)
        supportActionBar?.hide()
        setupClickListeners()
        setupFieldsValidation()
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

    private fun setupFieldsValidation() {
        with(binding) {

            this.nameTextInputEditText.doOnTextChanged { text, _, _, _ ->
                text?.let {
                    this.nameTextInputLayout.error =
                        if (text.isEmpty()) getString(R.string.error_empty_field) else null
                    isNameValid = binding.nameTextInputLayout.error == null
                    handleSaveContactBtn()
                }
            }

            this.phoneTextInputEditText.doOnTextChanged { text, _, _, _ ->
                text?.let {
                    this.phoneTextInputLayout.error = when {
                        text.isEmpty() -> getString(R.string.error_empty_field)

                        newContactViewModel.validateIfContactAlreadyExist(text.toString()) ->
                            getString(R.string.error_contact_already_exist)

                        else -> null
                    }
                    isPhoneValid = binding.phoneTextInputLayout.error == null
                    handleSaveContactBtn()
                }
            }

        }
    }

    private fun handleSaveContactBtn() {
        binding.bttSaveContact.isEnabled = isPhoneValid && isNameValid
    }

}