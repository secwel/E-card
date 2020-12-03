package com.project.e_card.Retrofit

data class EmployeeJSON(
        val unique_id: Int,
        val first_name: String,
        val last_name: String,
        val employee_id: Int,
        val email: String,
        val password: String,
        val rfid_tag: String,
        val access_level: Int
)
