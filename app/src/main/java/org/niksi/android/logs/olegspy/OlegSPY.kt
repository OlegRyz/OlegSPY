package org.niksi.android.logs.olegspy

const val OlegSPYTag = "OlegSPY"

object OlegSPY {
    private var logger: OlegSPYLogger = DefaultLogger
    private val filesFilter = FilesFilter()

    class SpyData {
        private var methodName = ""
        private var className = ""
        fun methodName() = if (methodName.isBlank()) {
            this.also { methodName = caller().composeMethodName() }
        } else {
            this
        }

        override fun toString() =
            "$className$methodName"

        fun log(message: String) = if (filesFilter.isAllowed(caller().fileName)) {
            logger.logD(OlegSPYTag, "$this${if (message.isNotBlank()) " " else ""}$message")
        } else {
            -1
        }
        fun log() = log("***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****")
    }

    private fun caller(): StackTraceElement =
        Exception().stackTrace.first { !it.className.contains("Oleg") }

    fun spy() = SpyData()

    fun switchOffLogsInThisFile() {
        filesFilter.exclude(caller().fileName)
    }
    fun injectLogger(logger: OlegSPYLogger) {
        this.logger = logger
    }
}
