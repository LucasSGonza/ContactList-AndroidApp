package com.example.contactlistandroid.modules.editcontact

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactlistandroid.databinding.ActivityEditContactBinding

class EditContactActivity: AppCompatActivity() {

    private lateinit var binding: ActivityEditContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

}