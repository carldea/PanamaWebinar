// Generated by jextract

package org.unix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$20 {

    static final FunctionDescriptor gmtime_r$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle gmtime_r$MH = RuntimeHelper.downcallHandle(
        "gmtime_r",
        constants$20.gmtime_r$FUNC
    );
    static final FunctionDescriptor localtime_r$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle localtime_r$MH = RuntimeHelper.downcallHandle(
        "localtime_r",
        constants$20.localtime_r$FUNC
    );
    static final FunctionDescriptor posix2time$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle posix2time$MH = RuntimeHelper.downcallHandle(
        "posix2time",
        constants$20.posix2time$FUNC
    );
    static final FunctionDescriptor tzsetwall$FUNC = FunctionDescriptor.ofVoid();
    static final MethodHandle tzsetwall$MH = RuntimeHelper.downcallHandle(
        "tzsetwall",
        constants$20.tzsetwall$FUNC
    );
    static final FunctionDescriptor time2posix$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle time2posix$MH = RuntimeHelper.downcallHandle(
        "time2posix",
        constants$20.time2posix$FUNC
    );
    static final FunctionDescriptor timelocal$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle timelocal$MH = RuntimeHelper.downcallHandle(
        "timelocal",
        constants$20.timelocal$FUNC
    );
}


