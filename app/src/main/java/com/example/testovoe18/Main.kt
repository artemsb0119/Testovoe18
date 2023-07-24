package com.example.testovoe18

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Main : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var textViewResult: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var textViewSchet: TextView
    private lateinit var textViewSchet1: TextView
    private lateinit var editTextCategory: EditText
    private lateinit var editTextCategory1: EditText
    private lateinit var editTextValue1: EditText
    private lateinit var editTextValue2: EditText
    private lateinit var buttonContinue: AppCompatButton
    private lateinit var buttonContinue1: AppCompatButton

    private var budget = 0
    private var dohod = 0
    private var rashod = 0

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
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        textViewResult = view.findViewById(R.id.textViewResult)
        textViewSchet = view.findViewById(R.id.textViewSchet)
        textViewSchet1 = view.findViewById(R.id.textViewSchet1)
        editTextCategory = view.findViewById(R.id.editTextCategory)
        editTextCategory1 = view.findViewById(R.id.editTextCategory1)
        editTextValue1 = view.findViewById(R.id.editTextValue1)
        editTextValue2 = view.findViewById(R.id.editTextValue2)
        buttonContinue = view.findViewById(R.id.buttonContinue)
        buttonContinue1 = view.findViewById(R.id.buttonContinue1)
        sharedPreferences =
            requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        budget = sharedPreferences.getInt("budget", 0)
        dohod = sharedPreferences.getInt("dohod", 0)
        rashod = sharedPreferences.getInt("rashod", 0)
        textViewResult.text = budget.toString()
        textViewSchet.text = dohod.toString()
        textViewSchet1.text = rashod.toString()
        if (budget < 0) {
            textViewResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        } else {
            textViewResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
        buttonContinue.setOnClickListener {
            if (TextUtils.isEmpty(editTextCategory.text) || TextUtils.isEmpty(editTextValue1.text)) {
                Toast.makeText(requireContext(), "Fill in all the fields", Toast.LENGTH_SHORT)
                    .show()
            }
            if (!TextUtils.isEmpty(editTextCategory.text) && !TextUtils.isEmpty(
                    editTextValue1.text
                )
            ) {
                val category = editTextCategory.text.toString()
                val value = editTextValue1.text.toString().toInt()
                val income = true

                val card = Card(value, category, income)
                sharedViewModel.addElem(card)

                budget += editTextValue1.text.toString().toInt()
                dohod += editTextValue1.text.toString().toInt()
                textViewResult.text = budget.toString()
                textViewSchet.text = dohod.toString()

                val editor =
                    requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
                        .edit()
                editor.putInt("budget", budget.toString().toInt())
                editor.putInt("dohod", dohod.toString().toInt())
                editor.apply()
            }
            if (budget < 0) {
                textViewResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            } else {
                textViewResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            editTextCategory.setText("")
            editTextValue1.setText("")
        }
        buttonContinue1.setOnClickListener {
            if (TextUtils.isEmpty(editTextCategory1.text) || TextUtils.isEmpty(editTextValue2.text)) {
                Toast.makeText(requireContext(), "Fill in all the fields", Toast.LENGTH_SHORT)
                    .show()
            }
            if (!TextUtils.isEmpty(editTextCategory1.text) && !TextUtils.isEmpty(
                    editTextValue2.text
                )
            ) {
                val category = editTextCategory1.text.toString()
                val value = editTextValue2.text.toString().toInt()
                val income = false

                val card = Card(value, category, income)
                sharedViewModel.addElem(card)

                budget -= editTextValue2.text.toString().toInt()
                rashod += editTextValue2.text.toString().toInt()
                textViewResult.text = budget.toString()
                textViewSchet1.text = rashod.toString()

                val editor =
                    requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
                        .edit()
                editor.putInt("budget", budget.toString().toInt())
                editor.putInt("rashod", rashod.toString().toInt())
                editor.apply()
            }
            if (budget < 0) {
                textViewResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            } else {
                textViewResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            editTextCategory1.setText("")
            editTextValue2.setText("")
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Main().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}