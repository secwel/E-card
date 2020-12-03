package com.project.e_card.Retrofit

import android.provider.ContactsContract
import java.util.*

data class EmployeeJSON(
        val name: String,
        val last_name: String,
        val employee_number: Int,
        val password: String,
        val email: String,
        val UID: String,
        val access_level: Int
)
