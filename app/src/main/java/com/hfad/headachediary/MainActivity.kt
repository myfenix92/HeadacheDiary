package com.hfad.headachediary

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hfad.headachediary.VM.HeadacheViewModel
import com.hfad.headachediary.VM.HeadacheViewModelFactory


class MainActivity : AppCompatActivity() {
    private val headacheViewModel: HeadacheViewModel by viewModels {
        HeadacheViewModelFactory((application as HeadacheApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.list_item)
        val adapter = ItemAdapter(this)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)
        headacheViewModel.allItems.observe(this, Observer {item ->
            item?.let { adapter.setItems(it) }
        })

        val fabBtn = findViewById<FloatingActionButton>(R.id.fab_add)
        fabBtn.setOnClickListener {
            addNewRecord()
        }

    }

    private fun addNewRecord() {
        supportFragmentManager.beginTransaction().add(R.id.main_container, AddNewFragment()).commit()
        val listItem: RecyclerView = findViewById(R.id.list_item)
            listItem.visibility = RecyclerView.INVISIBLE
    }
}