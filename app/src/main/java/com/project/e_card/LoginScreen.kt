package com.project.e_card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.project.e_card.Retrofit.ApiInterface
import com.project.e_card.Retrofit.RetrofitInstance
import com.project.e_card.Retrofit.SignInBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val firstName = findViewById<EditText>(R.id.employeeName)
        val employeeNumber = findViewById<EditText>(R.id.employeeNumberInput)
        /* val employeePassword = findViewById<EditText>(R.id.employeePassword) */

        loginButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)

            when {
                firstName.text.toString().trim().isEmpty() -> {
                    firstName.error = "You must provide a name!"
                    Toast.makeText(this, "Name is required!", Toast.LENGTH_SHORT).show()
                }
                employeeNumber.text.toString().trim().isEmpty() -> {
                    employeeNumber.error = "You must provide employee number!"
                    Toast.makeText(this, "Employee number is required!", Toast.LENGTH_SHORT).show()
                }
                /* employeePassword.text.toString().trim().isEmpty() -> {
                    employeePassword.error = "You must provide a password!"
                    Toast.makeText(this, "Password is required!", Toast.LENGTH_SHORT).show()
                } */
                else -> {
                     fun retLogin(first_name: String, employee_id: Int){
                        val apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
                        val signInInfo = SignInBody(first_name, employee_id)
                        apiInterface.signIn(signInInfo).enqueue(object: Callback<ResponseBody> {
                             override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Toast.makeText(
                                        this@LoginScreen,
                                        t.message,
                                        Toast.LENGTH_SHORT
                                ).show()
                            }

                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                if (response.code() == 200) {
                                    Toast.makeText(this@LoginScreen, "Login success!", Toast.LENGTH_SHORT).show()
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(this@LoginScreen, "Login failed!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                    }
                }
            }
        }
    }
}


