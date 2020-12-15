package com.project.e_card.NFC

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log

class HostCardEmulatorService : HostApduService() {

    companion object {
        const val TAG = "Host Card Emulator"
        const val STATUS_SUCCES = "9000"
        const val STATUS_FAILED= "6F00"
        const val CLA_NOT_SUPPORTED = "6E00"
        const val INS_NOT_SUPPORTED = "6D00"
        const val AID = "A0000042071001" //need to figure out correct AID
        const val SELECT_INS = "A4"
        const val DEFAULT_CLA = "00"
        const val MIN_APDU_LENGTH = 12
    }

    override fun onDeactivated(reason: Int) {
        Log.d(TAG, "Deactivated: $reason")
    }

    //    Where we process the data(APDU) from the reader (look at the APDU command structure to understand these parameters)
    //    commandApdu is the Apdu command received from the Reader

    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        Log.d(TAG, "APDU process command")
        if (commandApdu == null){
            return ByteArrayHexUtils.hexStringToByteArray(STATUS_FAILED)
        }
        val hexCommandApdu = ByteArrayHexUtils.toHex(commandApdu)
        if (hexCommandApdu.length < MIN_APDU_LENGTH){
            return ByteArrayHexUtils.hexStringToByteArray(STATUS_FAILED)
        }
        if (hexCommandApdu.substring(0,2) != DEFAULT_CLA){
            return ByteArrayHexUtils.hexStringToByteArray(CLA_NOT_SUPPORTED)
        }
        if (hexCommandApdu.substring(2,4) != SELECT_INS) {
            return ByteArrayHexUtils.hexStringToByteArray(INS_NOT_SUPPORTED)
        }

        return if (hexCommandApdu.substring(10,24) == AID){
            // we wont return success 90 00, we respond with our uid
            // return ByteArrayHexUtil.hexStringToByteArray(STATUS_SUCCESS)

            val dataStore = DataStoreUtils(this)
            val uid = dataStore.getID()
            ByteArrayHexUtils.hexStringToByteArray(uid)
        } else {
            return ByteArrayHexUtils.hexStringToByteArray(STATUS_FAILED)
        }
        //here we should implement the Response message
    }
}