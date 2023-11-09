package org.niksi.android.logs.olegspy

object HexUtil {
    private val digits = arrayOf("0", '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    private fun byteValues(separator: String): Array<String> {
        return Array(256) {
            "${digits[it.floorDiv(16)]}${digits[it.mod( 16)]}${separator}"
        }
    }
    fun convertToHexString(bytes: ByteArray, separator: String): String {
        val alphabet = byteValues(separator)
        return buildString {
            bytes.forEach { append(alphabet[it.toInt().and(0xFF)]) }
        }
    }
}