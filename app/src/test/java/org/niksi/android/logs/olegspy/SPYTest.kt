package org.niksi.android.logs.olegspy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SPYTest {

    private lateinit var olegSPYInstance: OlegSPY
    private var capturedTag: String = "Not overwritten tag"
    private var capturedMessage: String = "Not overwritten message"

    @Before
    fun before() {
        olegSPYInstance = OlegSPY(object : OlegSPYLogger {
            override fun logD(tag: String, message: String): Int {
                capturedTag = tag
                capturedMessage = message
                return 0
            }
        }, filesFilter = FilesFilter())
    }

    private fun caller() =
        Exception().stackTrace[1].toString().substring("org.niksi.android.logs.olegspy.".length)

    @Test
    fun spyMethodnameLog_logsOlegSPYtag() {
        olegSPYInstance.spy().methodName().log("")

        assertEquals("OlegSPY", capturedTag)
    }

    @Test
    fun spyMethodnameLog_withEmptyString_logsCallerMethod() {
        val expectedCaller = caller(); olegSPYInstance.spy().methodName().log("")

        assertEquals(expectedCaller, capturedMessage)
    }

    @Test
    fun spyMethodnameLog_logsCallerMethod() {
        val expectedCaller = caller(); olegSPYInstance.spy().methodName().log()

        assertTrue(
            "Log should start from $expectedCaller but it is $capturedMessage",
            capturedMessage.startsWith(expectedCaller)
        )
    }

    @Test
    fun spyMethodnameLog_logs75Stars() {
        olegSPYInstance.spy().methodName().log()

        assertTrue("Log should contain 75 stars but it has ${capturedMessage.count { it == '*' }} and it is $capturedMessage",
            capturedMessage.count { it == '*' } == 75)
    }

    @Test
    fun spyLog_whenFileIsIgnored_doesNotLog() {
        olegSPYInstance.switchOffLogsInThisFile()
        olegSPYInstance.spy().log()

        assertEquals(
            "Not overwritten message", capturedMessage
        )
    }
}