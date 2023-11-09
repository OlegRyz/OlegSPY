package org.niksi.android.logs.olegspy

import android.util.Log

interface OlegSPYLogger {
    fun logD(tag: String, message: String): Int
}

object DefaultLogger: OlegSPYLogger {
    override fun logD(tag: String, message: String): Int = Log.d(tag, message)

}