import org.mylib.Point;

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

    public final static MemorySegment lookup(String symbolName) {
        Linker linker = Linker.nativeLinker();
        return linker.defaultLookup()
                .lookup(symbolName)
                .or(() -> SymbolLookup.loaderLookup().lookup(symbolName))
                .orElseThrow(() -> new RuntimeException("The symbol %s is not found".formatted(symbolName)));
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

            // Step 1: Create method handle for the static method
            MethodHandle onCallMePlease = MethodHandles.lookup()
                    .findStatic(PanamaCallback.class,
                            "callMePlease",
                            MethodType.methodType(void.class));

            // Step 2: Create a stub as a native symbol to be passed into native function.
            // void (*ptr)()
            MemorySegment callMePleaseNativeSymbol = linker.upcallStub(
                    onCallMePlease,
                    FunctionDescriptor.ofVoid(),
                    memorySession);

            // Step 3: Create method handle to native callback function (my_callback_function)
            MemorySegment callback1 = lookup("my_callback_function");
            var my_callback_functionMethodHandle = linker.downcallHandle(
                    callback1,
                    FunctionDescriptor.ofVoid(C_POINTER)
            );

            // Step 4: Invoke native callback function receiving a function pointer.
            // void my_callback_function(void (*ptr)())
            my_callback_functionMethodHandle.invoke(callMePleaseNativeSymbol);


            // Another example with a different function signiture.
            // Step 1: Create a method handle to the Java function as a callback
            MethodHandle onDoubleMe = MethodHandles.lookup()
                    .findStatic(PanamaCallback.class,
                            "doubleMe",
                            MethodType.methodType(void.class, int.class));

            //  Step 2: Create a stub as a native symbol to be passed into native function.
            // void (*ptr)(int)
            MemorySegment doubleMeNativeSymbol = Linker.nativeLinker().upcallStub(
                    onDoubleMe,
                    FunctionDescriptor.ofVoid(C_INT),
                    memorySession);

            //  Step 3: Invoke C function receiving a callback from jextract method.
            // void my_callback_function2(void (*ptr)(int))
            my_callback_function2(doubleMeNativeSymbol);

            ///////////////////
            // Another example to mimic a function pointer that returns an int and passes in a struct
            MethodHandle addCoordinatesMH = MethodHandles.lookup()
                    .findStatic(PanamaCallback.class,
                            "addCoordinates",
                            MethodType.methodType(int.class, MemorySegment.class));
            MemorySegment addCoordinatesNativeSymbol = Linker.nativeLinker().upcallStub(
                    addCoordinatesMH,
                    FunctionDescriptor.of(C_INT, Point.$LAYOUT()),
                    memorySession);

            my_callback_function3(addCoordinatesNativeSymbol);
        }
    }

    public static int addCoordinates(MemorySegment ptStruct) {
        try (var memorySession= MemorySession.openConfined()) {
            int x = Point.x$get(ptStruct);
            int y = Point.y$get(ptStruct);
            printf(memorySession.allocateUtf8String(">>> pt.x + pt.y = %d \n"), (x+y));
            return (x+y);
        }
    }
}
