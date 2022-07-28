import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static java.lang.foreign.SegmentAllocator.implicitAllocator;
import static org.mylib.mylib_h.*;

public class PanamaCallback {

    /**
     * C code will call this static method.
     */
    public static void callMePlease() {
        MemorySegment cString = implicitAllocator()
                .allocateUtf8String("[JAVA] Inside callMePlease() method - I'm being called from C.\n");
        printf(cString);
    }

    /**
     * C code will call this static method.
     * @param value C will pass in a number. Your code will double it.
     */
    public static void doubleMe(int value) {
        MemorySegment cString = implicitAllocator()
                .allocateUtf8String("[JAVA] Inside doubleMe() method, %d times 2 = %d.\n".formatted(value, value*2));
        printf(cString);
    }

    /**
     * Main entry point.
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        try (var memorySession = MemorySession.openConfined()) {
            // MemorySegment C's printf using a C string
            MemorySegment cString = implicitAllocator()
                    .allocateUtf8String("[Java] Callbacks! Panama style\n");

            printf(cString);
            fflush(NULL());
            Linker linker = Linker.nativeLinker();

            // my_callback_function C function receives a callback (pointer to a function)
            MemorySegment callback1 = SymbolLookup.loaderLookup().lookup("my_callback_function")
                    .or(() -> linker.defaultLookup().lookup("my_callback_function"))
                    .orElseThrow(() -> new RuntimeException("cant find symbol"));
            var my_callback_functionMethodHandle = linker.downcallHandle(
                    callback1,
                    FunctionDescriptor.ofVoid(C_POINTER)
            );

            // my_callback_function2 C function receives a callback (pointer to a function)
            MemorySegment callback2 = SymbolLookup.loaderLookup().lookup("my_callback_function2")
                    .or(() -> linker.defaultLookup().lookup("my_callback_function2"))
                    .orElseThrow(() -> new RuntimeException("cant find symbol"));
            var my_callback_function2MethodHandle = linker.downcallHandle(
                    callback2,
                    FunctionDescriptor.ofVoid(C_POINTER, C_INT)
            );

            // Create a method handle to the Java function as a callback
            MethodHandle onCallMePlease = MethodHandles.lookup()
                    .findStatic(PanamaCallback.class,
                            "callMePlease",
                            MethodType.methodType(void.class));

            // Create a stub as a native symbol to be passed into native function.
            // void (*ptr)()
            MemorySegment callMePleaseNativeSymbol = linker.upcallStub(
                    onCallMePlease,
                    FunctionDescriptor.ofVoid(),
                    memorySession);

            // Invoke C function receiving a callback
            // void my_callback_function(void (*ptr)())
            my_callback_functionMethodHandle.invoke(callMePleaseNativeSymbol);

            // Create a method handle to the Java function as a callback
            MethodHandle onDoubleMe = MethodHandles.lookup()
                    .findStatic(PanamaCallback.class,
                            "doubleMe",
                            MethodType.methodType(void.class, int.class));

            // Create a stub as a native symbol to be passed into native function.
            // void (*ptr)(int)
            MemorySegment doubleMeNativeSymbol = Linker.nativeLinker().upcallStub(
                    onDoubleMe,
                    FunctionDescriptor.ofVoid(C_INT),
                    memorySession);

            // Invoke C function receiving a callback
            // void my_callback_function2(void (*ptr)(int))
            my_callback_function2(doubleMeNativeSymbol);
        }
    }
}
