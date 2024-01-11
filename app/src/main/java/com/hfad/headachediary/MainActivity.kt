package com.hfad.headachediary

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabBtn = findViewById<FloatingActionButton>(R.id.fab_add)
        fabBtn.setOnClickListener {
            addNewRecord()
        }

        supportFragmentManager.beginTransaction().add(R.id.main_container, MainFragment()).commit()

    }

    private fun addNewRecord() {
        supportFragmentManager.beginTransaction().add(R.id.main_container, AddNewFragment()).commit()
    }
}