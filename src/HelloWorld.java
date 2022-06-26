import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_INT;

/**
 * Panama Hello World calling C functions.
 */
public class HelloWorld {
    public static void main(String[] args) {
        try (var memorySession = MemorySession.openConfined()) {
            // MemorySegment C's printf using a C string
            MemorySegment cString = memorySession.allocateUtf8String("Hello World! Panama style\n");

           // C function printf()
           // int printf(const char *format, ...);  a variadic function
           Linker linker = Linker.nativeLinker();
           var symbolName = "printf";
           MemorySegment symbol = linker.defaultLookup()
                   .lookup(symbolName)
                   .or(() -> SymbolLookup.loaderLookup().lookup(symbolName))
                   .orElseThrow(() -> new RuntimeException("The symbol %s is not found".formatted(symbolName)));

            MethodHandle printfMH = linker.downcallHandle(
            symbol,
            FunctionDescriptor.of(JAVA_INT, ADDRESS));

            // invoke() requires a check exception
            printfMH.invoke(cString);

            // requires casting return and arguments passed.
            //int charCount = (int) printfMH.invokeExact((Addressable) cString);

            // converting a C string (MemorySegment) into a Java String
            String jString = cString.getUtf8String(0);
            System.out.println("Convert to Java String: " + jString);

            // Hello World! Panama style
       } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
