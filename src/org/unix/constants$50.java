// Generated by jextract

package org.unix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$50 {

    static final FunctionDescriptor valloc$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle valloc$MH = RuntimeHelper.downcallHandle(
        "valloc",
        constants$50.valloc$FUNC
    );
    static final FunctionDescriptor syscall$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle syscall$MH = RuntimeHelper.downcallHandleVariadic(
        "syscall",
        constants$50.syscall$FUNC
    );
    static final  OfAddress suboptarg$LAYOUT = Constants$root.C_POINTER$LAYOUT;
    static final VarHandle suboptarg$VH = constants$50.suboptarg$LAYOUT.varHandle();
    static final MemorySegment suboptarg$SEGMENT = RuntimeHelper.lookupGlobalVariable("suboptarg", constants$50.suboptarg$LAYOUT);
    static final FunctionDescriptor getsubopt$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle getsubopt$MH = RuntimeHelper.downcallHandle(
        "getsubopt",
        constants$50.getsubopt$FUNC
    );
    static final FunctionDescriptor fgetattrlist$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle fgetattrlist$MH = RuntimeHelper.downcallHandle(
        "fgetattrlist",
        constants$50.fgetattrlist$FUNC
    );
    static final FunctionDescriptor fsetattrlist$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle fsetattrlist$MH = RuntimeHelper.downcallHandle(
        "fsetattrlist",
        constants$50.fsetattrlist$FUNC
    );
}

