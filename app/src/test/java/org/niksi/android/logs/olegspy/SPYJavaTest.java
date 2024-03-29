package org.niksi.android.logs.olegspy;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SPYJavaTest {

    private String capturedMessage;
    private OlegSPY olegSpyInstance;

    private static String staticCaller() {
        return new Exception().getStackTrace()[1].toString().substring("org.niksi.android.logs.olegspy.".length());
    }

    @Before
    public void before() {
        olegSpyInstance = new OlegSPY((tag, message) -> {
            capturedMessage = message;
            return 0;
        }, new FilesFilter());
    }

    @Test
    public void spyMethodnameLog_showsStaticMethodName() {
        olegSpyInstance.spy().methodName().log("");String expectedCaller = staticCaller();

        assertEquals(expectedCaller, capturedMessage);
    }
}
