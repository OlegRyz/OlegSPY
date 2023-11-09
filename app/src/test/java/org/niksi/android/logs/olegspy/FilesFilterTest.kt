package org.niksi.android.logs.olegspy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FilesFilterTest {

    @Test
    fun isAllowed_whenFileIsExcluded_returnsFalse() = with(FilesFilter()) {
        exclude("FileToExclude.java")

        assertFalse(isAllowed("FileToExclude.java"))
    }

    @Test
    fun isAllowed_whenOtherFileIsExcluded_returnsFalse() = with(FilesFilter()) {
        exclude("FileToExclude2.java")

        assertTrue(isAllowed("FileToExclude.java"))
    }

    @Test
    fun isAllowed_whenNoFileIsExcluded_returnsFalse() = with(FilesFilter()) {
        assertTrue(isAllowed("FileToExclude.java"))
    }
}