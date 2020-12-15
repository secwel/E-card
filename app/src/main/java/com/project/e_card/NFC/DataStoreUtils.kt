package com.project.e_card.NFC

import android.content.Context
import android.content.SharedPreferences
import com.project.e_card.Retrofit.EmployeeData
import java.util.*

class DataStoreUtils(context: Context) {

    private val ALLOWED_CHARACTERS = "0123456789ABCDEF"
    private val PREFS_FILENAME = "com.ecard.hostcardemulation.prefs"
    private var prefs: SharedPreferences? = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    // Gets the current devices unique AID
    fun getID(): String {
        var uid = prefs!!.getString("uid", null)
        if (uid == null) {
            uid = EmployeeData.UID!!
            //uid = this.generateID()
            this.saveID(uid)
        }
        return uid
    }

    private fun saveID(uid: String) {
        val editor = prefs!!.edit()
        editor.putString("uid", uid)
        editor.apply()
    }

    // Generates a random Hex string consisting of 12 characters
    private fun generateID(sizeOfRandomHexString: Int = 12): String {
        val random = Random()
        val sb = StringBuilder(sizeOfRandomHexString)
        for (i in 0 until sizeOfRandomHexString)
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return sb.toString()
    }

}