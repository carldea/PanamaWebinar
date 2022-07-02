import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class Utils {
    /**
     * Returns a native symbol (MemorySegment) from memory.
     * @param symbolName
     * @return MemorySegment Returns a Symbol in memory.
     */
    public final static MemorySegment lookup(String symbolName) {
        Linker linker = Linker.nativeLinker();
        return linker.defaultLookup()
                .lookup(symbolName)
                .or(() -> SymbolLookup.loaderLookup().lookup(symbolName))
                .orElseThrow(() -> new RuntimeException("The symbol %s is not found".formatted(symbolName)));
    }

    /**
     * Creates a Method Handle that will reference a native function.
     * @param symbolName The native symbol name
     * @param functionDescriptor FunctionDescriptor using Value and Memory layouts to describe call site of a C function.
     * @return MethodHandle
     */
    public final static MethodHandle create(String symbolName, FunctionDescriptor functionDescriptor) {
        Linker linker = Linker.nativeLinker();
        return linker.downcallHandle(lookup(symbolName), functionDescriptor);
    }
}
