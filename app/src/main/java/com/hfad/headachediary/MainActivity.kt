package com.hfad.headachediary

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.hfad.headachediary.Entity.HeadacheTuple
import com.hfad.headachediary.VM.HeadacheViewModel
import com.hfad.headachediary.VM.HeadacheViewModelFactory


class MainActivity : AppCompatActivity() {
    private val gson = Gson()
    private val headacheViewModel: HeadacheViewModel by viewModels {
        HeadacheViewModelFactory((application as HeadacheApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.list_item)
        val listener: ItemAdapter.Listener = object : ItemAdapter.Listener {
            override fun onClick(data: HeadacheTuple, position: Int) {
                //Log.d("click", data.toString())
                changeRecord(data)
            }

        }
        val adapter = ItemAdapter(this, listener)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)
        headacheViewModel.allItems.observe(this, Observer {item ->
            val list = item.sortedBy { it.item.dateItem }
            list.let { adapter.setItems(it) }
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

    private fun changeRecord(data: HeadacheTuple) {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = AddNewFragment()

        val mBundle = Bundle()
        mBundle.putString("param1", gson.toJson(data))
        mFragment.arguments = mBundle
        mFragmentTransaction.add(R.id.main_container, mFragment).commit()
       // AddNewFragment.newInstance(gson.toJson(data))
       // supportFragmentManager.beginTransaction().add(R.id.main_container, AddNewFragment()).commit()
        val listItem: RecyclerView = findViewById(R.id.list_item)
        listItem.visibility = RecyclerView.INVISIBLE
    }
}