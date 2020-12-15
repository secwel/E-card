package com.project.e_card.NFC

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.project.e_card.R

class NFCDialogue(var context: Context) {
    var dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)

    fun showNFCDisabled() {
        dialogBuilder.setMessage(R.string.nfc_disabled)
                .setCancelable(false)

                // positive button text and action
                .setPositiveButton(R.string.dialogue_yes) { dialog, _ ->
                    dialog.dismiss()
                    val intent = Intent(Settings.ACTION_NFC_SETTINGS)
                    context.startActivity(intent)
                }

                // negative button text and action
                .setNegativeButton(R.string.dialogue_no) { dialog, _ ->
                    dialog.cancel()
                }
        show()
    }


    fun showNFCUnsupported() {
        dialogBuilder.setMessage(R.string.nfc_unsupported)
                .setCancelable(false)

                // positive button text and action
                .setPositiveButton(R.string.dialogue_ok) { dialog, _ ->
                    dialog.dismiss()
                }
        show()
    }

    private fun show() {
        val alert = dialogBuilder.create()
        alert.setTitle("NFC")
        alert.show()
    }
}