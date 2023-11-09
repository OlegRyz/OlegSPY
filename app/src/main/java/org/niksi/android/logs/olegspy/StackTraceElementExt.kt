package org.niksi.android.logs.olegspy


val StackTraceElement.shortClassName: String
    get() = this.className.takeLastWhile { it != '.' }


fun StackTraceElement.composeMethodName() = buildString {
    append(shortClassName)
    append(".")
    append(methodName)
    append("(")
    if (isNativeMethod) {
        append("Native Method")
    } else {
        append(fileName?:"Unknown Source")
    }
    if (lineNumber >= 0) {
        append(":")
        append(lineNumber)
    }
    append(")")
}