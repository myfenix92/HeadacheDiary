package com.hfad.headachediary

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val listView = findViewById<ListView>(R.id.localization)
//
//        val localizationPain = resources.getStringArray(R.array.localization_pain)
//
//        val adapter: ArrayAdapter<String> = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1, localizationPain
//        )
//
//        listView.adapter = adapter
        val fabBtn = findViewById<FloatingActionButton>(R.id.fab_add)
        fabBtn.setOnClickListener {
            addNewRecord()
        }

    }

    fun addNewRecord() {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, AddNewFragment()).commit()
    }
}