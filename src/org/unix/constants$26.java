// Generated by jextract

package org.unix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$26 {

    static final FunctionDescriptor dup$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle dup$MH = RuntimeHelper.downcallHandle(
        "dup",
        constants$26.dup$FUNC
    );
    static final FunctionDescriptor dup2$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle dup2$MH = RuntimeHelper.downcallHandle(
        "dup2",
        constants$26.dup2$FUNC
    );
    static final FunctionDescriptor execl$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle execl$MH = RuntimeHelper.downcallHandleVariadic(
        "execl",
        constants$26.execl$FUNC
    );
    static final FunctionDescriptor execle$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle execle$MH = RuntimeHelper.downcallHandleVariadic(
        "execle",
        constants$26.execle$FUNC
    );
    static final FunctionDescriptor execlp$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle execlp$MH = RuntimeHelper.downcallHandleVariadic(
        "execlp",
        constants$26.execlp$FUNC
    );
    static final FunctionDescriptor execv$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle execv$MH = RuntimeHelper.downcallHandle(
        "execv",
        constants$26.execv$FUNC
    );
}


