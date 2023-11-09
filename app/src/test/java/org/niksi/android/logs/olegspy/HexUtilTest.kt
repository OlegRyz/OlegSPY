package org.niksi.android.logs.olegspy

import org.junit.Assert
import org.junit.Test


class HexUtilTest {

    @Test
    fun convert255ToHexString() {
        val elements = byteArrayOf(255.toByte())
        Assert.assertEquals("FF", HexUtil.convertToHexString(elements, ""))
    }


    @Test
    fun convert1ToHexString() {
        val elements = byteArrayOf(1.toByte())
        Assert.assertEquals("01", HexUtil.convertToHexString(elements, ""))
    }

    @Test
    fun convert10ToHexString() {
        val elements = byteArrayOf(10.toByte())
        Assert.assertEquals("0A", HexUtil.convertToHexString(elements, ""))
    }

    @Test
    fun convertSequenceToHexString() {
        val elements = byteArrayOf(1.toByte(), 2.toByte(), 200.toByte())
        Assert.assertEquals("0102C8", HexUtil.convertToHexString(elements, ""))
    }

    @Test
    fun convertSequenceWithSeparatorToHexString() {
        val elements = byteArrayOf(1.toByte(), 2.toByte(), 200.toByte())
        Assert.assertEquals("01 - 02 - C8 - ", HexUtil.convertToHexString(elements, " - "))
    }

    @Test
    fun convertBigArrayToHexString() {
        val initVals = byteArrayOf(1,2,3,4,5,6,7,10,34,50,60,1,-4,5)
        val elements = ByteArray(10_000_000) {
            it.mod(initVals.size).toByte()
        }
        Assert.assertEquals(10_000_000 * 2, HexUtil.convertToHexString(elements, "").length)
    }
}