package com.project.e_card.NFC

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log

class HostCardEmulatorService : HostApduService() {

    companion object {
        val TAG = "Host Card Emulator"
        val STATUS_SUCCES = "9000"
        val STATUS_FAILED= "6F00"
        val CLA_NOT_SUPPORTED = "6E00"
        val INS_NOT_SUPPORTED = "6D00"
        val AID = "A0000002471001" //need to figure out correct AID
        val SELECT_INS = "A4"
        val DEFAULT_CLA = "00"
        val MIN_APDU_LENGTH = 12
    }

    override fun onDeactivated(reason: Int) {
        Log.d(TAG, "Deactivated: " + reason)
    }



    //    Where we process the data(APDU) from the reader (look at the APDU command structure to understand these parameters)
    //    commandApdu is the Apdu command received from the Reader

    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        if (commandApdu == null){
            return Utils.hexStringToByteArray(STATUS_FAILED)
        }
        val hexCommandApdu = Utils.toHex(commandApdu)
        if (hexCommandApdu.length < MIN_APDU_LENGTH){
            return Utils.hexStringToByteArray(STATUS_FAILED)
        }
        if (hexCommandApdu.substring(0,2) != DEFAULT_CLA){
            return Utils.hexStringToByteArray(CLA_NOT_SUPPORTED)
        }
        if (hexCommandApdu.substring(2,4) != SELECT_INS) {
            return Utils.hexStringToByteArray(INS_NOT_SUPPORTED)
        }
        if (hexCommandApdu.substring(10,24) == AID){
            return Utils.hexStringToByteArray(STATUS_SUCCES)
        }
        else{
            return Utils.hexStringToByteArray(STATUS_FAILED)
        }

        //here we should implement the Response message









    }



}