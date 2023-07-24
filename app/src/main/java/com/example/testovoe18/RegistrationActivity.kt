package com.example.testovoe18

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextBudget: EditText
    private lateinit var buttonContinue: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        editTextName = findViewById(R.id.editTextName)
        editTextBudget = findViewById(R.id.editTextValue1)
        buttonContinue = findViewById(R.id.buttonContinue)

        var isGood = true

        buttonContinue.setOnClickListener {
            if (TextUtils.isEmpty(editTextName.text) || TextUtils.isEmpty(editTextBudget.text)) {
                Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show()
            }
            if (!TextUtils.isEmpty(editTextName.text) && !TextUtils.isEmpty(
                    editTextBudget.text)
            ) {
                val editor = getSharedPreferences("my_preferences", Context.MODE_PRIVATE).edit()
                editor.putBoolean("is_first_start", false)
                editor.putString("name", editTextName.text.toString())
                editor.putInt("budget", editTextBudget.text.toString().toInt())
                editor.apply()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}