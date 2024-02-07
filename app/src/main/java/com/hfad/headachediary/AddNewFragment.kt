package com.hfad.headachediary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hfad.headachediary.Entity.CharacterEntity
import com.hfad.headachediary.Entity.HeadacheEntity
import com.hfad.headachediary.Entity.HeadacheTuple
import com.hfad.headachediary.Entity.LocalizationEntity
import com.hfad.headachediary.Entity.MedicinesEntity
import com.hfad.headachediary.VM.HeadacheViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddNewFragment : Fragment() {
    private val gson = Gson()
    private val headacheViewModel: HeadacheViewModel by activityViewModels<HeadacheViewModel>()
//    <HeadacheViewModel>{
//        HeadacheViewModelFactory((activity?.application as HeadacheApplication).repository)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new, container, false)
        val bundle = arguments
        val message = bundle!!.getString("param1")
        val message2 = bundle.getString("param2")
        var deseriz: HeadacheTuple? = null
        val itemTypeDeseriz = object : TypeToken<HeadacheTuple>() {}.type
        deseriz = if (message.toString() == "new record") {
            null
        } else {
            gson.fromJson<HeadacheTuple>(message, itemTypeDeseriz)
        }
        //  var deseriz = gson.fromJson(message, HeadacheTuple::class.java)
        val itemType = object : TypeToken<List<MedicinesEntity>>() {}.type
        var deseriz2 = gson.fromJson<List<MedicinesEntity>>(message2, itemType)
        Log.d("value_message", deseriz2.toString())


        val calendar: CalendarView = view.findViewById(R.id.calendar_headache)
        var dateHeadache: Long = Calendar.getInstance().time.time
        calendar.setOnDateChangeListener { _, year, month, dayOfMonth -> //not input only save in db
            val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                .parse("$dayOfMonth-${month + 1}-$year")
            if (date != null) {
                dateHeadache = date.time
            }
        }

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
        duration.min = 1
        duration.progress = 1
        durationValue.text = getString(R.string.duration_value, duration.progress)

        duration.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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
        val medicineName: NoDefaultSpinner = child.findViewById(R.id.medicine_name)
        val medicineNewName: EditText = child.findViewById(R.id.new_medicine_name)
        val medicineCount: Spinner = child.findViewById(R.id.medicine_count)
        val medicineCountValue: TextView = child.findViewById(R.id.medicine_count_value)
        val medicinesNameArray: MutableList<String> = mutableListOf()
        if (deseriz2.isNotEmpty()) {
            for (i in deseriz2.indices) {
                medicinesNameArray.add(deseriz2[i].medicinesName.toString())
            }
        }
        medicinesNameArray.add(getString(R.string.add_new_medicines))
        val spinnerAdapter = activity?.applicationContext?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                medicinesNameArray.distinct()
            )
        }
        medicineName.adapter = spinnerAdapter
        addMedicinesBtn.setOnClickListener {

            medicineName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (medicineName.selectedItem.toString() == getString(R.string.add_new_medicines)) {
                        medicineNewName.visibility = EditText.VISIBLE
                    } else {
                        medicineNewName.visibility = EditText.GONE
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

            medicineCount.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    medicineCountValue.text = medicineCount.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    medicineCountValue.text = "1"
                }

            }
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
            if (deseriz == null) {
                var newId: Long
                headacheViewModel.insertItem(HeadacheEntity(dateHeadache, duration.progress))
                    .observe(viewLifecycleOwner, Observer {
                        newId = it
                        for (i in localizationId.indices) {
                            val localizationCheckBox: CheckBox =
                                localization.getChildAt(i) as CheckBox
                            if (localizationCheckBox.isChecked) {
                                headacheViewModel.insertLocalization(
                                    LocalizationEntity(
                                        newId,
                                        localizationCheckBox.text.toString()
                                    )
                                )
                            }
                        }

                        for (i in characterId.indices) {
                            val characterCheckBox: CheckBox =
                                character.getChildAt(i) as CheckBox
                            if (characterCheckBox.isChecked) {
                                headacheViewModel.insertCharacter(
                                    CharacterEntity(
                                        newId,
                                        characterCheckBox.text.toString()
                                    )
                                )
                            }
                        }

                        val name =
                            if (medicineName.selectedItem.toString() == getString(R.string.add_new_medicines)) {
                                medicineNewName.text.toString()
                            } else {
                                medicineName.selectedItem.toString()
                            }

                        val countMedicines =
                            if (medicineCountValue.text.toString().isEmpty()) {
                                null
                            } else {
                                medicineCountValue.text.toString().toInt()
                            }
                            headacheViewModel.insertMedicines(MedicinesEntity(newId, name, countMedicines))
                    })
            } else {
                val updateId: Long = deseriz.item.id
                headacheViewModel.updateItem(
                    HeadacheEntity(
                        updateId,
                        dateHeadache,
                        duration.progress
                    )
                )

                val localId: Long = deseriz.localizationList[0].id

                //   Log.d("change", localId.toString())
                //update обновляет один, сделать так чтобы каждую запись
                for (i in localizationId.indices) {
                    val localizationCheckBox: CheckBox = localization.getChildAt(i) as CheckBox
                    if (localizationCheckBox.isChecked) {
                        headacheViewModel.updateLocalization(
                            LocalizationEntity(
                                localId, updateId,
                                localizationCheckBox.text.toString()
                            )
                        )
                    }
                }
                val charactId = deseriz.characterList[0].id
                for (i in characterId.indices) {
                    val characterCheckBox: CheckBox = character.getChildAt(i) as CheckBox
                    if (characterCheckBox.isChecked) {
                        headacheViewModel.updateCharacter(
                            CharacterEntity(
                                charactId, updateId,
                                characterCheckBox.text.toString()
                            )
                        )
                    }
                }

                val name =
                    if (medicineName.selectedItem.toString() == getString(R.string.add_new_medicines)) {
                        "-"
                    } else {
                        medicineName.selectedItem.toString()
                    }
                val countMedicines = if (medicineCountValue.text.toString().isEmpty()) {
                    null
                } else {
                    medicineCountValue.text.toString().toInt()
                }
                val updateMedicinesId: Long = deseriz.medicinesList[0].id
                headacheViewModel.updateMedicines(
                    MedicinesEntity(
                        updateId,
                        name,
                        countMedicines
                    )
                )
            }

            startActivity(intent)
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            AddNewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    //    putString(ARG_PARAM2, param2)
                }
            }
    }
}