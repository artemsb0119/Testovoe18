package com.example.testovoe18

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class History : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerViewHistory: RecyclerView
    private lateinit var adapter: HistoryAdapter

    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
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
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerViewHistory = view.findViewById(R.id.recyclerViewHistory)
        val verticalLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewHistory.layoutManager = verticalLayoutManager

        val selectedElem = sharedViewModel.getElem().value


        adapter = if (selectedElem != null && selectedElem.isNotEmpty()) {
            HistoryAdapter(requireContext(), selectedElem)
        } else {
            HistoryAdapter(requireContext(), emptyList())
        }
        recyclerViewHistory.adapter = adapter
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            History().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}