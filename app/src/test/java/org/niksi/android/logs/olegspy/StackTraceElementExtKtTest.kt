package org.niksi.android.logs.olegspy

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StackTraceElementExtKtTest {

    private lateinit var stackTraceElement: StackTraceElement

    @Before
    fun before() {
        stackTraceElement = StackTraceElement(
            "com.declaring.class",
            "methodName",
            "TestFile",
            15
        )
    }

    private fun String.skipClassPackage() = this.substring("com.declaring.".length)

    @Test
    fun shortClassName_whenNormalClass_returnsClassName() {
        assertEquals("class", stackTraceElement.shortClassName)
    }

    @Test
    fun shortClassName_whenAnonymousClass_returnsClassName() {
        stackTraceElement = StackTraceElement("com.declaring.class\$1"
        , "", "", 0)
        assertEquals("class$1", stackTraceElement.shortClassName)
    }

    @Test
    fun composeMethodName_whenNormalMethod_returnsClassMethodFileAndLine() {
        assertEquals("class.methodName(TestFile:15)", stackTraceElement.composeMethodName())
        assertEquals(stackTraceElement.toString().skipClassPackage(), stackTraceElement.composeMethodName())
    }

    @Test
    fun composeMethodName_whenNativeClass_returnsClassMethodNative() {
        stackTraceElement = StackTraceElement(
            "com.declaring.class",
            "methodName",
            "TestFile",
            -2
        )
        assertEquals("class.methodName(Native Method)", stackTraceElement.composeMethodName())
        assertEquals(stackTraceElement.toString().skipClassPackage(), stackTraceElement.composeMethodName())
    }

    @Test
    fun composeMethodName_whenFileIsNull_returnsUnknownSource() {
        stackTraceElement = StackTraceElement(
            "com.declaring.class",
            "methodName",
            null,
            -1
        )
        assertEquals("class.methodName(Unknown Source)", stackTraceElement.composeMethodName())
        assertEquals(stackTraceElement.toString().skipClassPackage(), stackTraceElement.composeMethodName())
    }

    @Test
    fun composeMethodName_whenFileIsNullWithLineNumber_returnsUnknownSourceLineNumber() {
        stackTraceElement = StackTraceElement(
            "com.declaring.class",
            "methodName",
            null,
            10
        )
        assertEquals("class.methodName(Unknown Source:10)", stackTraceElement.composeMethodName())
    }

    @Test
    fun composeMethodName_whenFileIsEmpty_returnsLineNumber() {
        stackTraceElement = StackTraceElement(
            "com.declaring.class",
            "methodName",
            "",
            10
        )
        assertEquals("class.methodName(:10)", stackTraceElement.composeMethodName())
        assertEquals(stackTraceElement.toString().skipClassPackage(), stackTraceElement.composeMethodName())
    }
}