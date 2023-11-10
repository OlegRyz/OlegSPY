package org.niksi.android.logs.olegspy;

public final class Oleg {
    /*package*/ static OlegSPY OlegSPYInstance = OlegSPY.Companion.getINSTANCE();

    private Oleg() {/* No Instance*/}

    public static Integer spy() {
        return OlegSPYInstance.spy().methodName().log("");
    }

    public static void ignoreThisFile() {
        OlegSPYInstance.switchOffLogsInThisFile();
    }

    public static void separator() {
        OlegSPYInstance.spy().log();
    }
}
