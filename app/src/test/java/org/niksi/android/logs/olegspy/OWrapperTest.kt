package org.niksi.android.logs.olegspy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class OWrapperTest {

    private var capturedTag: String = "Not overwritten tag"
    private var capturedMessage: String = "Not overwritten message"

    @Before
    fun before() {
        //Use a new instance to avoid side effects between tests
        Oleg.OlegSPYInstance = OlegSPY(object : OlegSPYLogger {
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
    fun spy_logsOlegSPYtag() {
        Oleg.spy()

        assertEquals("OlegSPY", capturedTag)
    }

    @Test
    fun spyMethodnameLog_withEmptyString_logsCallerMethod() {
        val expectedCaller = caller(); Oleg.spy()

        assertEquals(expectedCaller, capturedMessage)
    }

    @Test
    fun spyMethodnameLog_logsCallerMethod() {
        val expectedCaller = caller(); Oleg.spy()

        assertTrue(
            "Log should start from $expectedCaller but it is $capturedMessage",
            capturedMessage.startsWith(expectedCaller)
        )
    }

    @Test
    fun spyMethodnameLog_logs75Stars() {
        Oleg.separator()

        assertTrue("Log should contain 75 stars but it has ${capturedMessage.count { it == '*' }} and it is $capturedMessage",
            capturedMessage.count { it == '*' } == 75)
    }

    @Test
    fun spyLog_whenFileIsIgnored_doesNotLog() {
        Oleg.ignoreThisFile()
        Oleg.spy()

        assertEquals(
            "Not overwritten message", capturedMessage
        )
    }
}