// Generated by jextract

package org.unix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$35 {

    static final FunctionDescriptor getdtablesize$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle getdtablesize$MH = RuntimeHelper.downcallHandle(
        "getdtablesize",
        constants$35.getdtablesize$FUNC
    );
    static final FunctionDescriptor getpagesize$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle getpagesize$MH = RuntimeHelper.downcallHandle(
        "getpagesize",
        constants$35.getpagesize$FUNC
    );
    static final FunctionDescriptor getpass$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle getpass$MH = RuntimeHelper.downcallHandle(
        "getpass",
        constants$35.getpass$FUNC
    );
    static final FunctionDescriptor getwd$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle getwd$MH = RuntimeHelper.downcallHandle(
        "getwd",
        constants$35.getwd$FUNC
    );
    static final FunctionDescriptor lchown$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle lchown$MH = RuntimeHelper.downcallHandle(
        "lchown",
        constants$35.lchown$FUNC
    );
    static final FunctionDescriptor lockf$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle lockf$MH = RuntimeHelper.downcallHandle(
        "lockf",
        constants$35.lockf$FUNC
    );
}


