package com.hfad.headachediary

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddNewFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new, container, false)

        val calendar: CalendarView = view.findViewById(R.id.calendar_headache)
        val dateHeadache: TextView = view.findViewById(R.id.date_headache)
        calendar.setOnDateChangeListener(object : CalendarView.OnDateChangeListener{
            override fun onSelectedDayChange(
                view: CalendarView,
                year: Int,
                month: Int,
                dayOfMonth: Int
            ) {
                //not input only save in db
                val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                    .parse("$dayOfMonth-${month + 1}-$year")
                if (date != null) {
                    dateHeadache.text = date.time.toString()
                }
            }

        })

        val localization: LinearLayout = view.findViewById(R.id.localization)
        val localizationValue = resources.getStringArray(R.array.localization_pain)
        val localizationId = resources.getStringArray(R.array.localization_id)
        for ((i, _) in (localizationValue.indices).withIndex()) {
            val checkBoxValue = CheckBox(context)
            checkBoxValue.text = localizationValue[i].toString()
            checkBoxValue.tag = localizationId[i]
            localization.addView(checkBoxValue)
        }

        val character: LinearLayout = view.findViewById(R.id.character)
        val characterValue = resources.getStringArray(R.array.character_pain)
        val characterId = resources.getStringArray(R.array.character_id)
        for ((i, _) in (characterValue.indices).withIndex()) {
            val checkBoxValue = CheckBox(context)
            checkBoxValue.text = characterValue[i].toString()
            checkBoxValue.tag = characterId[i]
            character.addView(checkBoxValue)
        }

        val duration: SeekBar = view.findViewById(R.id.duration)
        val durationValue: TextView = view.findViewById(R.id.duration_value)

        duration.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                durationValue.text = getString(R.string.duration_value, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })




        val addMedicinesBtn: ImageButton = view.findViewById(R.id.add_medicines)
        val newMedicines: LinearLayout = view.findViewById(R.id.medicines)
        val child: View = layoutInflater.inflate(R.layout.medicines_layout, null)

        addMedicinesBtn.setOnClickListener {
            val medicineCount: SeekBar = child.findViewById(R.id.medicine_count)
            val medicineCountValue: TextView = child.findViewById(R.id.medicine_count_value)
            medicineCountValue.text = "1"
            medicineCount.progress = 1
            medicineCount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    medicineCountValue.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })
            newMedicines.addView(child)
        }

        val cancelBtn: Button = view.findViewById(R.id.cancel_btn)
        cancelBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            val listItem: RecyclerView? = activity?.findViewById(R.id.list_item)
            listItem?.visibility = RecyclerView.VISIBLE
        }

        val okBtn: Button = view.findViewById(R.id.add_btn)
        okBtn.setOnClickListener {
            val intent = Intent(activity?.applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddNewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}