package com.project.e_card

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // this if checks if the dark mode is on or off
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme)
        } else {
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(findViewById<View>(R.id.my_toolbar) as Toolbar?)

        name = findViewById(R.id.name)
        lastName = findViewById(R.id.lastName)
        employeeNumber = findViewById(R.id.employeeNumber)

    }
    /**
     * Method used to set the options menu in the tool bar
     * @param menu
     * @return
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    /**
     * Method listening for user input regarding the options menu
     * @param item
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.LightTheme -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                restartApp()
            }
            R.id.DarkTheme -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                restartApp()
            }
            R.id.SignOut -> {
                val intent = Intent(this,LoginScreen::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Restart application to the current screen
     */
    private fun restartApp() {
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    /**
     * This method allows the user to leave the application once the back button is clicked.
     */
    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    companion object {
        @JvmField
        var name: TextView? = null
        var lastName: TextView? = null
        var employeeNumber: TextView? = null
        var toolBar: Toolbar? = null
    }
}