package com.project.e_card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.project.e_card.Retrofit.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

private var TAG = "LoginActivity"
const val BASE_URL = "http://34.107.71.133:8000"

class LoginScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val firstNameField = findViewById<EditText>(R.id.employeeName)
        val employeeNumberField = findViewById<EditText>(R.id.employeeNumberInput)
        val employeePasswordField = findViewById<EditText>(R.id.employeePassword)

        loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            when {
                firstNameField.text.toString().trim().isEmpty() -> {
                    firstNameField.error = "You must provide a name!"
                    Toast.makeText(this, "Name is required!", Toast.LENGTH_SHORT).show()
                }
                employeeNumberField.text.toString().trim().isEmpty() -> {
                    employeeNumberField.error = "You must provide employee number!"
                    Toast.makeText(this, "Employee number is required!", Toast.LENGTH_SHORT).show()
                }
                employeePasswordField.text.toString().trim().isEmpty() -> {
                    employeePasswordField.error = "You must provide a password!"
                    Toast.makeText(this, "Password is required!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    retLogin(intent, firstNameField.text.toString(), employeeNumberField.text.toString().toInt(), employeePasswordField.text.toString())
                }
            }
        }
    }
    fun retLogin(intent: Intent, firstName: String, employeeNumber: Int, employeePassword: String) {
        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCredentials().awaitResponse()
                Log.d(TAG, response.code().toString())
                if (response.isSuccessful) {
                    val firstName = response.body()!!.name
                    val employeeNumber = response.body()!!.employee_number
                    val password = response.body()!!.password
                    if (firstName.equals(firstName) && employeeNumber.equals(employeeNumber) && password.equals(employeePassword)) {
                        startActivity(intent)
                    } else {
                        Log.d(TAG, "error in your credentials!")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d(TAG, e.toString())
                }
            }
        }
    }
}

