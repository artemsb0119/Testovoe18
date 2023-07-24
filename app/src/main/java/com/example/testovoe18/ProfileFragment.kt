package com.example.testovoe18

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var textViewProfile: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var buttonExit: AppCompatButton

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
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        textViewProfile = view.findViewById(R.id.textViewProfile)
        buttonExit = view.findViewById(R.id.buttonExit)
        sharedPreferences = requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        textViewProfile.text = sharedPreferences.getString("name", "User")
        buttonExit.setOnClickListener {

            sharedViewModel.removeAllElem()

            val editor = requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE).edit()
            editor.putBoolean("is_first_start", true)
            editor.putInt("budget", 0)
            editor.putInt("dohod", 0)
            editor.putInt("rashod", 0)
            editor.putString("name","")
            editor.apply()

            val intent = Intent(requireActivity(), RegistrationActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}