// Generated by jextract

package org.unix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$49 {

    static final FunctionDescriptor setwgroups_np$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle setwgroups_np$MH = RuntimeHelper.downcallHandle(
        "setwgroups_np",
        constants$49.setwgroups_np$FUNC
    );
    static final FunctionDescriptor strtofflags$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle strtofflags$MH = RuntimeHelper.downcallHandle(
        "strtofflags",
        constants$49.strtofflags$FUNC
    );
    static final FunctionDescriptor swapon$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle swapon$MH = RuntimeHelper.downcallHandle(
        "swapon",
        constants$49.swapon$FUNC
    );
    static final FunctionDescriptor ttyslot$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle ttyslot$MH = RuntimeHelper.downcallHandle(
        "ttyslot",
        constants$49.ttyslot$FUNC
    );
    static final FunctionDescriptor undelete$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle undelete$MH = RuntimeHelper.downcallHandle(
        "undelete",
        constants$49.undelete$FUNC
    );
    static final FunctionDescriptor unwhiteout$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle unwhiteout$MH = RuntimeHelper.downcallHandle(
        "unwhiteout",
        constants$49.unwhiteout$FUNC
    );
}

