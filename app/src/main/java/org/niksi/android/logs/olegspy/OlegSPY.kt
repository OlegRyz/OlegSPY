package org.niksi.android.logs.olegspy

const val OlegSPYTag = "OlegSPY"

class OlegSPY(
    private var logger: OlegSPYLogger = DefaultLogger,
    private val filesFilter: FilesFilter = FilesFilter(),
) {

    class SpyData(
        private var logger: OlegSPYLogger,
        private val filesFilter: FilesFilter,
    ) {
        private var methodName = ""
        private var className = ""
        fun methodName() = if (methodName.isBlank()) {
            this.also { methodName = caller().composeMethodName() }
        } else {
            this
        }

        override fun toString() = "$className$methodName"

        fun log(message: String) = if (filesFilter.isAllowed(caller().fileName)) {
            logger.logD(OlegSPYTag, "$this${if (message.isNotBlank()) " " else ""}$message")
        } else {
            -1
        }

        fun log() =
            log("***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****")
    }

    fun spy() = SpyData(logger, filesFilter)

    fun switchOffLogsInThisFile() {
        filesFilter.exclude(caller().fileName)
    }

    companion object {
        val INSTANCE = OlegSPY()
        fun caller(): StackTraceElement =
            Exception().stackTrace.first { !it.className.contains("Oleg") }
    }
}
