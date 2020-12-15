package com.project.e_card.NFC

class ByteArrayHexUtils {

//   These methods are here to convert between byte arrays and Hexadecimal Strings.

    companion object{
        private const val HEX_CHARS = "0123456789ABCDEF"
        fun hexStringToByteArray(data: String) : ByteArray {

            val result = ByteArray(data.length / 2)

            for (i in data.indices step 2) {
                val firstIndex = HEX_CHARS.indexOf(data[i])
                val secondIndex = HEX_CHARS.indexOf(data[i + 1])

                val octet = firstIndex.shl(4).or(secondIndex)
                result[i.shr(1)] = octet.toByte()
            }
            return result
        }

        private val HEX_CHARS_ARRAY = "0123456789ABCDEF".toCharArray()
        fun toHex(byteArray: ByteArray) : String {
            val result = StringBuffer()

            byteArray.forEach {
                val octet = it.toInt()
                val firstIndex = (octet and 0xF0).ushr(4)
                val secondIndex = octet and 0x0F
                result.append(HEX_CHARS_ARRAY[firstIndex])
                result.append(HEX_CHARS_ARRAY[secondIndex])
            }
            return result.toString()
        }

    }

}