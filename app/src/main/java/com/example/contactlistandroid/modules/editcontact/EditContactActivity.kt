package com.example.contactlistandroid.modules.editcontact

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.contactlistandroid.R
import com.example.contactlistandroid.databinding.ActivityEditContactBinding
import com.example.contactlistandroid.model.Contact
import com.example.contactlistandroid.modules.main.MainViewModel
import java.lang.NumberFormatException

class EditContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditContactBinding
    private lateinit var editContactViewModel: EditContactViewModel
    private var isNameValid: Boolean = true
    private var isPhoneValid: Boolean = true

    private var contactId: Int = 0
    private var contactPhoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        editContactViewModel = ViewModelProvider(this)[EditContactViewModel::class.java]
        setContentView(binding.root)
        supportActionBar?.hide()
        getContactInfo()
        setupClickListeners()
        setupFieldsValidation()
    }

    private fun getContactInfo() {
        val intent = intent
        intent.getBundleExtra("CONTACT")?.let {
            it.getString("ID")?.let { id ->
                try {
                    this.contactId = id.toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        this,
                        getString(R.string.error_validate_contact_info),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            it.getString("NAME")?.let { name -> binding.nameTextInputEditText.setText(name) }
            it.getString("PHONE")?.let { phone ->
                binding.phoneTextInputEditText.setText(phone)
                this.contactPhoneNumber = phone
            }
        }
    }

    private fun setupClickListeners() {
        with(binding) {
            this.bttReturn.setOnClickListener { returnToDashboard() }
            this.bttSaveContact.setOnClickListener { saveContactInfoEdition() }
        }
    }

    private fun saveContactInfoEdition() {
        val name = binding.nameTextInputEditText.text.toString()
        val phone = binding.phoneTextInputEditText.text.toString()

        val contact = Contact(contactId, name, phone)
        editContactViewModel.editContact(contact)?.let {
            Log.i("test", "name: ${it.name}")
            Toast.makeText(
                this,
                getString(R.string.success_edit_contact),
                Toast.LENGTH_SHORT
            ).show()
            this.finish()
        } ?: {
            Toast.makeText(
                this,
                getString(R.string.error_validate_contact_info),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun returnToDashboard() {
        this.finish()
    }

    private fun setupFieldsValidation() {
        with(binding) {

            this.nameTextInputEditText.doOnTextChanged { text, _, _, _ ->
                text?.let {
                    Log.i("test", "$text")
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

                        editContactViewModel.validateIfContactAlreadyExist(text.toString()) &&
                                text.toString() != contactPhoneNumber ->
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