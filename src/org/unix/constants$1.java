// Generated by jextract

package org.unix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$1 {

    static final FunctionDescriptor clearerr$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle clearerr$MH = RuntimeHelper.downcallHandle(
        "clearerr",
        constants$1.clearerr$FUNC
    );
    static final FunctionDescriptor fclose$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle fclose$MH = RuntimeHelper.downcallHandle(
        "fclose",
        constants$1.fclose$FUNC
    );
    static final FunctionDescriptor feof$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle feof$MH = RuntimeHelper.downcallHandle(
        "feof",
        constants$1.feof$FUNC
    );
    static final FunctionDescriptor ferror$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle ferror$MH = RuntimeHelper.downcallHandle(
        "ferror",
        constants$1.ferror$FUNC
    );
    static final FunctionDescriptor fflush$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle fflush$MH = RuntimeHelper.downcallHandle(
        "fflush",
        constants$1.fflush$FUNC
    );
    static final FunctionDescriptor fgetc$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle fgetc$MH = RuntimeHelper.downcallHandle(
        "fgetc",
        constants$1.fgetc$FUNC
    );
}


