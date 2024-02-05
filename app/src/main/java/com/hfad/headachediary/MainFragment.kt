package com.hfad.headachediary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.headachediary.VM.HeadacheViewModel
import com.hfad.headachediary.VM.HeadacheViewModelFactory

//import com.hfad.headachediary.VM.HeadacheViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val headacheViewModel: HeadacheViewModel by activityViewModels()
    {
        HeadacheViewModelFactory((activity?.application as HeadacheApplication).repository)
    }
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
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.list_item)
     //   val adapter = activity?.applicationContext?.let { ItemAdapter(it) }
    //    recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
//        headacheViewModel.allItems.observe(viewLifecycleOwner, Observer { items ->
//            items?.let { adapter?.setItems(it) }
//        })

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