import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_INT;
import static org.unix.foo_h.printf;

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
           Addressable symbol = linker.defaultLookup()
                   .lookup(symbolName)
                   .or(() -> SymbolLookup.loaderLookup().lookup(symbolName))
                   .orElseThrow(() -> new RuntimeException("The symbol %s is not found".formatted(symbolName)));

            MethodHandle printfMH = linker.downcallHandle(
            symbol,
            FunctionDescriptor.of(JAVA_INT, ADDRESS));

            // invoke() requires a check exception
            printfMH.invoke(cString);

            // Hello World! Panama style

            // requires casting return and arguments passed.
            //int charCount = (int) printfMH.invokeExact((Addressable) cString);

            // converting a C string (MemorySegment) into a Java String
            String jString = cString.getUtf8String(0);
            System.out.println("Convert to Java String: " + jString);
            // Convert to Java String: Hello World! Panama style

            // Calling Variadic C functions such as printf("text %s %d", "text", 123);
            System.out.println(Linker
                    .downcallType(FunctionDescriptor.of(JAVA_INT) /* return type */
                    .appendArgumentLayouts()
                    .asVariadic(ADDRESS, ADDRESS, JAVA_INT))); /* arg1, arg2, arg3 */

            var output = "Life, the %s and Everything is %d \n";

            MethodHandle printfVaridicMH = linker.downcallHandle(
                    symbol,
                    FunctionDescriptor.of(JAVA_INT)
                            .appendArgumentLayouts()
                            .asVariadic(ADDRESS, ADDRESS, JAVA_INT));

            Addressable arg1 = memorySession.allocateUtf8String(output);
            Addressable arg2 = memorySession.allocateUtf8String("Universe");
            int arg3 = 42;

            printfVaridicMH.invoke(arg1, arg2, arg3);

            // Output
            // Life, the Universe and Everything is 42

            // Using jextract
            Addressable jeArg1 = memorySession.allocateUtf8String("%s?…It's the ship that made the %s in less than %d parsecs.\n");
            Addressable jeArg2 = memorySession.allocateUtf8String("Millennium Falcon");
            Addressable jeArg3 = memorySession.allocateUtf8String("Kessel Run");
            int jeArg4 = 12;

            printf(jeArg1, jeArg2.address(), jeArg3.address(), jeArg4);
            // Output
            // Millennium Falcon?…It's the ship that made the Kessel Run in less than 12 parsecs.
       } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
