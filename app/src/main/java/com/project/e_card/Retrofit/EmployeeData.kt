package com.project.e_card.Retrofit

class EmployeeData {

    //Makes sure the data is passed into the EmployeeObject
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
}