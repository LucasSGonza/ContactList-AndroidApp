package com.example.contactlistandroid.modules.main

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistandroid.R
import com.example.contactlistandroid.databinding.ActivityMainBinding
import com.example.contactlistandroid.model.Contact
import com.example.contactlistandroid.modules.editcontact.EditContactActivity
import com.example.contactlistandroid.modules.newcontact.NewContactActivity
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

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

        val callback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Take action for the swiped item
                mainViewModel.deleteContact(viewHolder.absoluteAdapterPosition)
                recyclerAdapter.deleteContact(viewHolder.absoluteAdapterPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftActionIcon(R.drawable.baseline_delete_forever_24)
                    .addSwipeLeftCornerRadius(TypedValue.COMPLEX_UNIT_DIP, 20.0F)
                    .addSwipeLeftBackgroundColor(getColor(R.color.red))
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recycleForContacts)
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
        val editContact = Intent(binding.root.context, EditContactActivity::class.java)
        val bundle = Bundle().apply {
            putString("ID", "${contact.id}")
            putString("NAME", contact.name)
            putString("PHONE", contact.phoneNumber)
        }
        editContact.putExtra("CONTACT", bundle)
        startActivity(editContact)
    }

}