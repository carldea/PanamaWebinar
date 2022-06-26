import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.util.Arrays;
import java.util.List;

import static java.lang.foreign.ValueLayout.*;

public class Utils {
//    public static final MethodHandle printfMH = create("printf",
//            FunctionDescriptor.of(JAVA_INT, ADDRESS, JAVA_DOUBLE));

    public static MemorySegment lookup(String symbolName) {
        Linker linker = Linker.nativeLinker();
        return linker.defaultLookup()
                .lookup(symbolName)
                .or(() -> SymbolLookup.loaderLookup().lookup(symbolName))
                .orElseThrow(() -> new RuntimeException("The symbol %s is not found".formatted(symbolName)));
    }

    public static MethodHandle create(String symbolName, FunctionDescriptor functionDescriptor) {
        Linker linker = Linker.nativeLinker();
        return linker.downcallHandle(lookup(symbolName), functionDescriptor);
    }
}
