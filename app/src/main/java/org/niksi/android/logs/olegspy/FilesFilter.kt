package org.niksi.android.logs.olegspy

class FilesFilter {
    private val excludedNames = mutableSetOf<String?>()
    fun exclude(name: String) {
        excludedNames.add(name)
    }
    fun isAllowed(name: String?) = !excludedNames.contains(name)
}
