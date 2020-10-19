package com.project.e_card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.reflect.KClass

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val employeeEmail = findViewById<EditText>(R.id.employeeEmail)
        val employeeNumber = findViewById<EditText>(R.id.employeeNumberInput)
        val employeePassword = findViewById<EditText>(R.id.employeePassword)



        loginButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)

            when {
                employeeEmail.text.toString().trim().isEmpty() -> {
                    employeeEmail.error = "You must provide an email!"
                    Toast.makeText(this, "Email is required!", Toast.LENGTH_SHORT).show()
                }
                employeeNumber.text.toString().trim().isEmpty() -> {
                    employeeNumber.error = "You must provide employee number!"
                    Toast.makeText(this, "Employee number is required!", Toast.LENGTH_SHORT).show()
                }
                employeePassword.text.toString().trim().isEmpty() -> {
                    employeePassword.error = "You must provide a password!"
                    Toast.makeText(this, "Password is required!", Toast.LENGTH_SHORT).show()
                }
                else -> startActivity(intent)
            }
        }
    }


}

