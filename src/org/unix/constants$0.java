// Generated by jextract

package org.unix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$0 {

    static final FunctionDescriptor renameat$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle renameat$MH = RuntimeHelper.downcallHandle(
        "renameat",
        constants$0.renameat$FUNC
    );
    static final FunctionDescriptor renamex_np$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle renamex_np$MH = RuntimeHelper.downcallHandle(
        "renamex_np",
        constants$0.renamex_np$FUNC
    );
    static final FunctionDescriptor renameatx_np$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle renameatx_np$MH = RuntimeHelper.downcallHandle(
        "renameatx_np",
        constants$0.renameatx_np$FUNC
    );
    static final  OfAddress __stdinp$LAYOUT = Constants$root.C_POINTER$LAYOUT;
    static final VarHandle __stdinp$VH = constants$0.__stdinp$LAYOUT.varHandle();
    static final MemorySegment __stdinp$SEGMENT = RuntimeHelper.lookupGlobalVariable("__stdinp", constants$0.__stdinp$LAYOUT);
    static final  OfAddress __stdoutp$LAYOUT = Constants$root.C_POINTER$LAYOUT;
    static final VarHandle __stdoutp$VH = constants$0.__stdoutp$LAYOUT.varHandle();
    static final MemorySegment __stdoutp$SEGMENT = RuntimeHelper.lookupGlobalVariable("__stdoutp", constants$0.__stdoutp$LAYOUT);
    static final  OfAddress __stderrp$LAYOUT = Constants$root.C_POINTER$LAYOUT;
    static final VarHandle __stderrp$VH = constants$0.__stderrp$LAYOUT.varHandle();
    static final MemorySegment __stderrp$SEGMENT = RuntimeHelper.lookupGlobalVariable("__stderrp", constants$0.__stderrp$LAYOUT);
}


