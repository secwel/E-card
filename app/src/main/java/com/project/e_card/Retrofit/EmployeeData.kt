package com.project.e_card.Retrofit

class EmployeeData {

    //Passes the current employees data into the EmployeeObject
    fun makeObject(fName: String, lName: String, eNumber: Int, passwrd: String, mail: String, uid: String, aLevel: Int): EmployeeObj {
        name = fName
        last_name = lName
        employee_number = eNumber
        password = passwrd
        email = mail
        UID = uid
        access_level = aLevel

        return EmployeeObj
    }

    // To be able to pass the object with the data around
    companion object EmployeeObj{
        var name: String? = null
        var last_name: String? = null
        var employee_number: Int? = null
        var password: String? = null
        var email: String? = null
        var UID: String? = null
        var access_level: Int? = null

        fun printData() {
            println("$name $last_name $employee_number $password $email $UID $access_level")
        }
    }

    // Instead of having a class with this alone
    data class EmployeeJSON(
            val name: String,
            val last_name: String,
            val employee_number: Int,
            val password: String,
            val email: String,
            val UID: String,
            val access_level: Int
    )
}