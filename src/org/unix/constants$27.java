// Generated by jextract

package org.unix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$27 {

    static final FunctionDescriptor execve$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle execve$MH = RuntimeHelper.downcallHandle(
        "execve",
        constants$27.execve$FUNC
    );
    static final FunctionDescriptor execvp$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle execvp$MH = RuntimeHelper.downcallHandle(
        "execvp",
        constants$27.execvp$FUNC
    );
    static final FunctionDescriptor fork$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle fork$MH = RuntimeHelper.downcallHandle(
        "fork",
        constants$27.fork$FUNC
    );
    static final FunctionDescriptor fpathconf$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle fpathconf$MH = RuntimeHelper.downcallHandle(
        "fpathconf",
        constants$27.fpathconf$FUNC
    );
    static final FunctionDescriptor getcwd$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle getcwd$MH = RuntimeHelper.downcallHandle(
        "getcwd",
        constants$27.getcwd$FUNC
    );
    static final FunctionDescriptor getegid$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle getegid$MH = RuntimeHelper.downcallHandle(
        "getegid",
        constants$27.getegid$FUNC
    );
}


