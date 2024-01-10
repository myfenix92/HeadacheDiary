package com.hfad.headachediary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.NumberPicker
import android.widget.TextView

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
        val localization: CheckBox = view.findViewById(R.id.localization)
        val medicineCount: NumberPicker = view.findViewById(R.id.medicine_count)
        val medicineCountValue: TextView = view.findViewById(R.id.medicine_count_value)
        medicineCount.minValue = 1
        medicineCount.maxValue = 10
        medicineCount.wrapSelectorWheel = true
        medicineCountValue.text = "1"

        medicineCount.setOnValueChangedListener { picker, oldVal, newVal ->
            medicineCountValue.text = "$newVal"
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