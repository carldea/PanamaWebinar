// Generated by jextract

package org.unix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$18 {

    static final FunctionDescriptor ctime$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle ctime$MH = RuntimeHelper.downcallHandle(
        "ctime",
        constants$18.ctime$FUNC
    );
    static final FunctionDescriptor difftime$FUNC = FunctionDescriptor.of(Constants$root.C_DOUBLE$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle difftime$MH = RuntimeHelper.downcallHandle(
        "difftime",
        constants$18.difftime$FUNC
    );
    static final FunctionDescriptor getdate$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle getdate$MH = RuntimeHelper.downcallHandle(
        "getdate",
        constants$18.getdate$FUNC
    );
    static final FunctionDescriptor gmtime$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle gmtime$MH = RuntimeHelper.downcallHandle(
        "gmtime",
        constants$18.gmtime$FUNC
    );
    static final FunctionDescriptor localtime$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle localtime$MH = RuntimeHelper.downcallHandle(
        "localtime",
        constants$18.localtime$FUNC
    );
    static final FunctionDescriptor mktime$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle mktime$MH = RuntimeHelper.downcallHandle(
        "mktime",
        constants$18.mktime$FUNC
    );
}

