package org.niksi.android.logs.olegspy;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class OWrapperJavaTest {

    private String capturedMessage;

    private static String staticCaller() {
        return new Exception().getStackTrace()[1].toString().substring("org.niksi.android.logs.olegspy.".length());
    }

    @Before
    public void before() {
        //Use a new instance to avoid side effects between tests
        Oleg.OlegSPYInstance = new OlegSPY((tag, message) -> {
            capturedMessage = message;
            return 0;
        }, new FilesFilter());
    }

    @Test
    public void spyMethodnameLog_showsStaticMethodName() {
        Oleg.spy();String expectedCaller = staticCaller();

        assertEquals(expectedCaller, capturedMessage);
    }
}
